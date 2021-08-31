package com.tofu.bean.data;

public enum PermissionMethod {
    DEVELOPER("BeanHello.developer", "developer");

    private String permission;
    private String value;

    PermissionMethod(
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
