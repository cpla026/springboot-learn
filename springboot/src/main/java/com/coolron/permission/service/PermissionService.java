package com.coolron.permission.service;

import com.coolron.permission.domain.PermissionTree;
import com.coolron.permission.domain.RolePermissionVo;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/24 21:59
 * @Description:
 */
public interface PermissionService {
    List<PermissionTree> getPermissionTree(Integer roleId);
    void updatePermission(RolePermissionVo rolePermissionVo);
}
