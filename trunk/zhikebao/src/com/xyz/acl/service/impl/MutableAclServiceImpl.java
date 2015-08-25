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

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.acls.domain.AccessControlEntryImpl;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.security.acls.model.ChildrenExistException;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

import com.xyz.acl.dao.IAclClassDAO;
import com.xyz.acl.dao.IAclEntryDAO;
import com.xyz.acl.dao.IAclObjectIdentityDAO;
import com.xyz.acl.dao.IAclSidDAO;
import com.xyz.acl.model.AbstractSecureObject;
import com.xyz.acl.model.AclClass;
import com.xyz.acl.model.AclEntry;
import com.xyz.acl.model.AclObjectIdentity;
import com.xyz.acl.model.AclSid;
import com.xyz.acl.service.ISecurityAclService;
import com.xyz.framework.log.Logger;

/**
 * 接口 <code>MutableAclService</code> 的实现类。
 * 
 */
public class MutableAclServiceImpl extends AclServiceImpl implements ISecurityAclService {

    @Autowired
    private IAclObjectIdentityDAO aclObjectIdentityDAO;
    
    @Autowired
    private IAclSidDAO aclSidDAO;    

    @Autowired
    private IAclClassDAO aclClassDAO;  

    @Autowired
    private IAclEntryDAO aclEntryDAO; 
    
    private AclCache aclCache;
    
    public MutableAclServiceImpl() {
    }
    
    public MutableAclServiceImpl(AclCache aclCache) {
        Assert.notNull(aclCache, "AclCache required");
        this.aclCache = aclCache;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.acls.MutableAclService#createAcl(org.springframework.security.acls.objectidentity.ObjectIdentity)
     */
    public MutableAcl createAcl(ObjectIdentity objectIdentity)
            throws AlreadyExistsException {
       
        AclObjectIdentity aclObjectIdentity = getAclObjectIdentity(objectIdentity);
        // Check this object identity hasn't already been persisted
        if (aclObjectIdentity != null) {
            throw new AlreadyExistsException("Object identity '" + aclObjectIdentity + "' already exists");
        }
        
        AclClass aclClass = aclClassDAO.findByClass(objectIdentity.getType());
       
        // Need to retrieve the current principal, in order to know who "owns" this ACL (can be changed later on)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalSid sid = new PrincipalSid(auth);
        
        
        AclSid aclSid = getOrCreateAclSid(sid,true);
        
        aclObjectIdentity = new AclObjectIdentity();
        aclObjectIdentity.setObjectIdClass(aclClass.getId());
        aclObjectIdentity.setOwnerSid(aclSid.getId());
        aclObjectIdentity.setObjectIdIdentity(new Long(objectIdentity.getIdentifier().toString()));
        aclObjectIdentity.setEntriesInheriting(true);
        aclObjectIdentityDAO.save(aclObjectIdentity);
        
        // Retrieve the ACL via superclass (ensures cache registration, proper retrieval etc)
        Acl acl = readAclById(objectIdentity);
        return (MutableAcl) acl;
    }

   
    protected void createEntries(final MutableAcl acl) {
        int i =1;
        for (AccessControlEntry entry_:acl.getEntries()){
            AccessControlEntryImpl entry = (AccessControlEntryImpl) entry_;
            AclEntry aclEntry = new AclEntry();
            long oid = ((Long) acl.getId()).longValue();
            AclObjectIdentity aoi = aclObjectIdentityDAO.get(oid);
            if(aoi!=null)
            {
               aclEntry.setAclObjectIdentity(aoi.getId());
            }
            aclEntry.setAceOrder(i);
            AclSid aclSid = getOrCreateAclSid(entry.getSid(),true);
            aclEntry.setSid(aclSid.getId());
            aclEntry.setAuditFailure(entry.isAuditFailure());
            aclEntry.setAuditSuccess(entry.isAuditSuccess());
            aclEntry.setGranting(entry.isGranting());
            aclEntry.setMask(entry.getPermission().getMask());
            aclEntryDAO.save(aclEntry);
            i++;
        }
    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.acls.MutableAclService#deleteAcl(org.springframework.security.acls.objectidentity.ObjectIdentity, boolean)
     */
    public void deleteAcl(ObjectIdentity objectIdentity, boolean deleteChildren)
            throws ChildrenExistException {
        // 根据访问的Object取的相应的acl
        // 取得访问的Object的className和id
        String javaType = objectIdentity.getClass().getName();
        AclClass aclClass = aclClassDAO.findByClass(javaType);
        // No need to check for nulls, as guaranteed non-null by ObjectIdentity.getIdentifier() interface contract
        String identifier = objectIdentity.getIdentifier().toString();
        long id = (Long.valueOf(identifier)).longValue();
        AclObjectIdentity aclObjectIdentity =  aclObjectIdentityDAO.findByObjectId(aclClass.getId(), id);
        aclEntryDAO.deleteByIdentityId(aclObjectIdentity.getId());
        aclObjectIdentityDAO.delete(aclObjectIdentity);
        // Clear the cache
        aclCache.evictFromCache(objectIdentity);  
    }

    /* (non-Javadoc)
     * @see org.springframework.security.acls.MutableAclService#updateAcl(org.springframework.security.acls.MutableAcl)
     */
    public MutableAcl updateAcl(MutableAcl acl) throws NotFoundException {
        // Delete this ACL's ACEs in the acl_entry table
//        long oid = ((Long) acl.getId()).longValue();
        AclObjectIdentity aclo = getAclObjectIdentity(acl.getObjectIdentity());
        aclEntryDAO.deleteByIdentityId(aclo.getId());

        // Create this ACL's ACEs in the acl_entry table
        createEntries(acl);
        
        // Change the mutable columns in acl_object_identity
        updateObjectIdentity(acl);

        // Clear the cache, including children
        clearCacheIncludingChildren(acl.getObjectIdentity());

        // Retrieve the ACL via superclass (ensures cache registration, proper retrieval etc)
        return (MutableAcl) super.readAclById(acl.getObjectIdentity());
    }
    
    private void clearCacheIncludingChildren(ObjectIdentity objectIdentity) {
        Assert.notNull(objectIdentity, "ObjectIdentity required");
        List<ObjectIdentity> children = findChildren(objectIdentity);
        if (children != null) {
            for (int i = 0; i < children.size(); i++) {
                clearCacheIncludingChildren(children.get(i));
            }
        }
        aclCache.evictFromCache(objectIdentity);
    }

    public AclObjectIdentity getAclObjectIdentity(ObjectIdentity objectIdentity){
        // 根据访问的Object取的相应的acl
        // 取得访问的Object的className和id
        String javaType = objectIdentity.getType();
        AclClass aclClass = aclClassDAO.findByClass(javaType);
        // if not found aclClass, create it!
        if (aclClass == null){
            aclClass = new AclClass();
            aclClass.setClass_(javaType);
            aclClassDAO.save(aclClass);    
        }
        // No need to check for nulls, as guaranteed non-null by ObjectIdentity.getIdentifier() interface contract
        String identifier = objectIdentity.getIdentifier().toString();
        long id = (Long.valueOf(identifier)).longValue();
        AclObjectIdentity aclObjectIdentity =  aclObjectIdentityDAO.findByObjectId(aclClass.getId(), id);
        return aclObjectIdentity;
        
    }
    
    public AclSid getOrCreateAclSid(Sid sid, boolean allowCreate){
        String sidName = null;
        char principal = '1';

        if (sid instanceof PrincipalSid) {
            sidName = ((PrincipalSid) sid).getPrincipal();
        } else if (sid instanceof GrantedAuthoritySid) {
            sidName = ((GrantedAuthoritySid) sid).getGrantedAuthority();
            principal = '2';
        } else {
            throw new IllegalArgumentException("Unsupported implementation of Sid");
        }
        
        AclSid aclSid = aclSidDAO.findBySid(sidName, principal);
        if(aclSid==null&&allowCreate){
            aclSid = new AclSid();
            aclSid.setSid(sidName);
            aclSid.setPrincipal(principal);
            aclSidDAO.save(aclSid);
        }
        return aclSid;
        
    }
    @Override
    public void addPermission(AbstractSecureObject secureObject, Permission permission, Class clazz) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Sid recipient;
        if (auth.getPrincipal() instanceof UserDetails) {
            recipient =  new PrincipalSid(((UserDetails) auth.getPrincipal()).getUsername());
        } else {
            recipient =  new PrincipalSid(auth.getPrincipal().toString());
        }

        addPermission(secureObject, recipient, permission, clazz);
    }

	@Override
    public void addPermission(AbstractSecureObject securedObject, Sid recipient, Permission permission, Class clazz) {
        MutableAcl acl;

        ObjectIdentity oid = new ObjectIdentityImpl(clazz.getCanonicalName(), securedObject.getId());

        try {
            acl = (MutableAcl) readAclById(oid);
        } catch (NotFoundException nfe) {
            acl = createAcl(oid);
        }        
                                                                  
        acl.insertAce(acl.getEntries().size(), permission, recipient, true);
        updateAcl(acl);
    }
	
    /**
     * Updates an existing acl_object_identity row, with new information presented in the passed MutableAcl
     * object. Also will create an acl_sid entry if needed for the Sid that owns the MutableAcl.
     *
     * @param acl to modify (a row must already exist in acl_object_identity)
     *
     * @throws NotFoundException if the ACL could not be found to update.
     */
    protected void updateObjectIdentity(MutableAcl acl) {
        Long parentId = null;

        if (acl.getParentAcl() != null) {
            Assert.isInstanceOf(ObjectIdentityImpl.class, acl.getParentAcl().getObjectIdentity(),
                "Implementation only supports ObjectIdentityImpl");

            ObjectIdentityImpl oii = (ObjectIdentityImpl) acl.getParentAcl().getObjectIdentity();
            parentId = retrieveObjectIdentityPrimaryKey(oii);
        }

        Assert.notNull(acl.getOwner(), "Owner is required in this implementation");
        
        Long ownerSid = createOrRetrieveSidPrimaryKey(acl.getOwner(), true);
        try {
			AclObjectIdentity aoi = aclObjectIdentityDAO.get(acl.getId()) ;
			aoi.setParentObject(parentId);
			aoi.setOwnerSid(ownerSid);
			aoi.setEntriesInheriting(new Boolean(acl.isEntriesInheriting()));
			aclObjectIdentityDAO.update(aoi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error(getClass(), e.getStackTrace().toString());
			throw new NotFoundException("Unable to locate ACL to update");
		}
        
    }
    
    private String selectObjectIdentityPrimaryKey = "select acl_object_identity.id from acl_object_identity, acl_class "
        + "where acl_object_identity.object_id_class = acl_class.id and acl_class.class= :clz "
        + "and acl_object_identity.object_id_identity = :identity ";
    private String selectSidPrimaryKey = "select id from acl_sid where principal=? and sid=?";
    
    /**
     * Retrieves the primary key from the acl_object_identity table for the passed ObjectIdentity. Unlike some
     * other methods in this implementation, this method will NOT create a row (use {@link
     * #createObjectIdentity(ObjectIdentity, Sid)} instead).
     *
     * @param oid to find
     *
     * @return the object identity or null if not found
     */
    protected Long retrieveObjectIdentityPrimaryKey(ObjectIdentity oid) {
        try {
        	Query query = aclSidDAO.getEntityManager().createNativeQuery(selectObjectIdentityPrimaryKey,Long.class);
        	query.setParameter("identity", oid.getIdentifier());
            query.setParameter("clz", oid.getType());
            Long id = (Long)query.getSingleResult();
            return id;
        } catch (DataAccessException notFound) {
            return null;
        }
    }
    /**
     * Retrieves the primary key from acl_sid, creating a new row if needed and the allowCreate property is
     * true.
     *
     * @param sid to find or create
     * @param allowCreate true if creation is permitted if not found
     *
     * @return the primary key or null if not found
     *
     * @throws IllegalArgumentException if the <tt>Sid</tt> is not a recognized implementation.
     */
    protected Long createOrRetrieveSidPrimaryKey(Sid sid, boolean allowCreate) {
        Assert.notNull(sid, "Sid required");

        String sidName = null;
        char sidIsPrincipal = '1';

        if (sid instanceof PrincipalSid) {
            sidName = ((PrincipalSid) sid).getPrincipal();
        } else if (sid instanceof GrantedAuthoritySid) {
            sidName = ((GrantedAuthoritySid) sid).getGrantedAuthority();
            sidIsPrincipal = '2';
        } else {
            throw new IllegalArgumentException("Unsupported implementation of Sid");
        }
        AclSid aclSid = aclSidDAO.findBySid(sidName, sidIsPrincipal);
       
        if (aclSid!=null) {
            return aclSid.getId();
        }

        if (allowCreate) {
        	aclSid = new AclSid();
        	aclSid.setSid(sidName);
        	aclSid.setPrincipal(sidIsPrincipal);
            Assert.isTrue(TransactionSynchronizationManager.isSynchronizationActive(), "Transaction must be running");
            aclSid = aclSidDAO.update(aclSid);
            return aclSid.getId();
        }

        return null;
    }

}
