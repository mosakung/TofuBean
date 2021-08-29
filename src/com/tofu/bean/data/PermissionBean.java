package com.tofu.bean.data;

public enum PermissionBean {
    DEVELOPER("BeanHello.developer", "developer");

    private String permission;
    private String value;

    PermissionBean(
            String permission,
            String value
    ) {
        this.permission = permission;
        this.value = value;
    }

    public String getPermission() {
        return permission;
    }

    public String getValue() { return value; }
}
