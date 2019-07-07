package com.coolron.pojo.permission;

import lombok.Data;

/**
 * @Auther: xf
 * @Date: 2018/11/24 22:19
 * @Description:  角色权限表
 */
@Data
public class RolePermission {
    private Integer roleId;  // 角色id
    private Integer permissionId;  // 权限id
}
