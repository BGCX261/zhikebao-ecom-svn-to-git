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

package com.xyz.acl.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import com.xyz.acl.model.AclSid;
import com.xyz.framework.data.impl.JpaDao;
import com.xyz.util.PropertyFilter;

/**
 * 接口<code>IAclSid</code> 的JPA实现。
 *
 */
public class AclSidDAOImpl extends JpaDao<AclSid, Long>  implements IAclSidDAO {

    /* (non-Javadoc)
     * @see org.gaixie.micrite.security.dao.IAclSidDAO#findBySid(java.lang.String, boolean)
     */
    @SuppressWarnings("unchecked")
    public AclSid findBySid(String sid, char principal) {
        List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
        filters.add(new PropertyFilter("EQ_sid",sid));
        filters.add(new PropertyFilter("EQ_principal",principal));
        List<AclSid> list = find(filters);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }    
}
