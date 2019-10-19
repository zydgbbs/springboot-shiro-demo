package com.example.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionTestController {

    @RequestMapping("/permissionUserList")
    @RequiresPermissions("permission:user:list")
    public String permissionUserList() {
        return "permission:user:list";
    }

    @RequestMapping("/permissionUserDelete")
    @RequiresPermissions("permission:user:delete")
    public String permissionUserDelete() {
        return "permission:user:delete";
    }

    @RequestMapping("/permissionUserShow")
    @RequiresPermissions("permission:user:show")
    public String permissionUserShow() {
        return "permission:user:show";
    }

    @RequestMapping("/permissionUserNone")
    @RequiresPermissions("permission:user:none")
    public String permissionUserNone() {
        return "permission:user:none";
    }

}
