package com.coolron.permission.domain;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/24 22:25
 * @Description:  修改角色-权限的时候使用
 */
@Data
public class RolePermissionVo {
    private Integer roleId;
    List<RolePermission> rolePermissionList = Lists.newArrayList();
}
