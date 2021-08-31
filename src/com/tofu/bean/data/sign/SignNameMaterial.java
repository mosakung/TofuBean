package com.tofu.bean.data.sign;

import org.bukkit.Material;

public enum SignNameMaterial {
    NAME_TAG("Name Tag");

    private final String itemName;

    SignNameMaterial(
            String itemName
    ) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public Material getMaterial() {
        return signItemName2Material(itemName);
    }

    public static Material signItemName2Material(String itemName) {
        return switch (itemName) {
            case "Name Tag" -> Material.NAME_TAG;
            default -> null;
        };
    }
}


