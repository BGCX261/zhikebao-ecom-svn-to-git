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

import com.xyz.acl.model.AclClass;
import com.xyz.framework.data.IDataObject;

/**
 * 提供与 <code>AclClass</code> 实体有关的DAO接口。
 * 
 */
public interface IAclClassDAO extends IDataObject<AclClass, Long>{

    /**
     * 根据class name获得AclClass对象。
     * 
     * @see org.gaixie.micrite.beans.AclClass
     * @param cls class name 例如 org.gaixie.micrite.beans.Role
     * @return AclClass对象
     */   
    public AclClass findByClass(String cls);
}
