package com.tofu.bean.data;

public enum PermissionBean {
    DEVELOPER("BeanHello.developer");

    private String permission;

    PermissionBean(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
