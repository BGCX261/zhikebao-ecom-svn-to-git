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

import java.util.List;

import com.xyz.acl.model.AclClass;
import com.xyz.framework.data.impl.JpaDao;

/**
 * 接口<code>IAclClass</code> 的JPA实现。
 *
 */
public class AclClassDAOImpl extends JpaDao<AclClass, Long>  implements IAclClassDAO {

    @SuppressWarnings("unchecked")
    public AclClass findByClass(String cls) {
        List<AclClass> list =  findBy("class_", cls);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
