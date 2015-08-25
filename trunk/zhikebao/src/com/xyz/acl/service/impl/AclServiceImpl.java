/* ===========================================================
 * $Id$
 * This file is part of Micrite
 * ===========================================================
 *
 * (C) Copyright 2009, by Gaixie.org and Contributors.
 * 
 * Project Info:  http://micrite.gaixie.org/
 *
 * Micrite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Micrite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Micrite.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.xyz.acl.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.AccessControlEntryImpl;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.util.FieldUtils;

import com.xyz.acl.dao.IAclClassDAO;
import com.xyz.acl.dao.IAclEntryDAO;
import com.xyz.acl.dao.IAclObjectIdentityDAO;
import com.xyz.acl.dao.IAclSidDAO;
import com.xyz.acl.model.AclClass;
import com.xyz.acl.model.AclEntry;
import com.xyz.acl.model.AclObjectIdentity;
import com.xyz.acl.model.AclSid;

/**
 * 接口 <code>AclService</code> 的实现类。
 * 
 */
public class AclServiceImpl implements AclService {

    @Autowired
    private IAclObjectIdentityDAO aclObjectIdentityDAO;
    
    @Autowired
    private AclAuthorizationStrategy aclAuthorizationStrategy;
    
    @Autowired
    private AuditLogger auditLogger;
    
    @Autowired
    private IAclEntryDAO aclEntryDAO;
    
    @Autowired
    private IAclClassDAO aclClassDAO; 
    
    @Autowired
    private IAclSidDAO aclSidDAO; 
    
    private PermissionFactory permissionFactory = new DefaultPermissionFactory();
   

    /* (non-Javadoc)
     * @see org.springframework.security.acls.AclService#readAclById(org.springframework.security.acls.objectidentity.ObjectIdentity)
     */
    public Acl readAclById(ObjectIdentity object) throws NotFoundException {
        return readAclById(object, null);
    }

    /* (non-Javadoc)
     * @see org.springframework.security.acls.AclService#readAclById(org.springframework.security.acls.objectidentity.ObjectIdentity, org.springframework.security.acls.sid.Sid[])
     */
    @SuppressWarnings("unchecked")
    public Acl readAclById(ObjectIdentity object, List<Sid> sids)
            throws NotFoundException {
    	List<ObjectIdentity> objects = new ArrayList<ObjectIdentity>();
    	objects.add(object);
        Map map = readAclsById(objects, sids);
        return (Acl) map.get(object);
    }
	
    /* (non-Javadoc)
     * @see org.springframework.security.acls.AclService#readAclsById(org.springframework.security.acls.objectidentity.ObjectIdentity[])
     */
    @SuppressWarnings("unchecked")
    public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> objects) throws NotFoundException {
        return readAclsById(objects, null);
    }
	
    /* (non-Javadoc)
     * @see org.springframework.security.acls.AclService#readAclsById(org.springframework.security.acls.objectidentity.ObjectIdentity[], org.springframework.security.acls.sid.Sid[])
     */
    @SuppressWarnings("unchecked")
    public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> objects,
			List<Sid> sids) throws NotFoundException {
        final Map acls = new HashMap(); 
        for (ObjectIdentity object :objects){
            // 根据访问的Object取的相应的acl
            // 取得访问的Object的className和id
            String javaType = object.getClass().getName();
            AclClass aclClass = aclClassDAO.findByClass(javaType);
            // No need to check for nulls, as guaranteed non-null by ObjectIdentity.getIdentifier() interface contract
            String identifier = object.getIdentifier().toString();
            long id = (Long.valueOf(identifier)).longValue();
            AclObjectIdentity aclObjectIdentity =  aclObjectIdentityDAO.findByObjectId(aclClass.getId(), id);
            // 如果访问的对象没有初始化acl，需要创建一个零时的acl，但aces为空，
            // spring security似乎没有对acl为空做判断
            if(aclObjectIdentity==null){
                throw new NotFoundException("Could not found specified aclObjectIdentity.");
//                AclImpl acl = new AclImpl(object, 0, 
//                        aclAuthorizationStrategy, auditLogger, 
//                        null, null, false, new GrantedAuthoritySid("ROLE_ADMIN"));
//                acls.put(object, acl); 
//                continue;
            }
            AclSid aclOwnerSid = aclSidDAO.findBySid(aclObjectIdentity.getOwnerSid().toString(), '1'); 
            Sid owner;

            if (aclOwnerSid.getPrincipal()=='1') {
                owner = new PrincipalSid(aclOwnerSid.getSid());
            } else {
                owner = new GrantedAuthoritySid(aclOwnerSid.getSid());
            }
            AclImpl acl = new AclImpl(object, aclObjectIdentity.getId(), 
                                        aclAuthorizationStrategy, auditLogger, 
                                        null, null, false, owner);
            acls.put(object, acl);  
            
            Field acesField = FieldUtils.getField(AclImpl.class, "aces");
            List aces;

            try {
                acesField.setAccessible(true);
                aces = (List) acesField.get(acl);
            } catch (IllegalAccessException ex) {
                throw new IllegalStateException("Could not obtain AclImpl.ace field: cause[" + ex.getMessage() + "]");
            }
            
            List<AclEntry> aclEntrys = aclEntryDAO.findByIdentityId(aclObjectIdentity.getId());
            
            for(AclEntry aclEntry:aclEntrys){
                AclSid aclSid = aclSidDAO.get(aclEntry.getSid());
                Sid recipient;
                if (aclOwnerSid.getPrincipal()=='1') {
                    recipient = new PrincipalSid(aclSid.getSid());
                } else {
                    recipient = new GrantedAuthoritySid(aclSid.getSid());
                }  
                
                int mask = aclEntry.getMask();
                Permission permission = permissionFactory.buildFromMask(mask);
                boolean granting = aclEntry.getGranting();
                boolean auditSuccess = aclEntry.getAuditSuccess();
                boolean auditFailure = aclEntry.getAuditFailure();    
                
                AccessControlEntryImpl ace = new AccessControlEntryImpl(aclEntry.getId(), acl, recipient, permission, granting,
                        auditSuccess, auditFailure);       
                
                // Add the ACE if it doesn't already exist in the ACL.aces field
                 if (!aces.contains(ace)) {
                     aces.add(ace);
                 }                   
            }
       
        }
        return acls;
    }
    private static final String DEFAULT_SELECT_ACL_WITH_PARENT_SQL = "select obj.object_id_identity as identifier, class.class as type "
        + "from acl_object_identity obj, acl_object_identity parent, acl_class class "
        + "where obj.parent_object = parent.id and obj.object_id_class = class.id "
        + "and parent.object_id_identity = :identity and parent.object_id_class = ("
        + "select id FROM acl_class where acl_class.class = :clz)";
    
    public List<ObjectIdentity> findChildren(ObjectIdentity parentIdentity) {
        Query query = aclSidDAO.getEntityManager().createNativeQuery(DEFAULT_SELECT_ACL_WITH_PARENT_SQL, ObjectIdentityImpl.class);
        query.setParameter("identity", parentIdentity.getIdentifier());
        query.setParameter("clz", parentIdentity.getType());
        List<ObjectIdentity> objects = query.getResultList();
        if (objects.size() == 0) {
            return null;
        }
        
        return objects;
    }
   
}