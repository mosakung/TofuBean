package com.tofu.bean.plugin.casino.enums.blackjack;

import org.bukkit.Material;

public enum BlackJackActionType {
    HIT("Hit", Material.LIME_CANDLE),
    STAND("Stand", Material.WHITE_CANDLE),
    DOUBLE_DOWN("Double Down", Material.ORANGE_CANDLE),
    SPLIT("Split", Material.LIGHT_BLUE_CANDLE),
    SURRENDER("Surrender", Material.RED_CANDLE),
    INSURANCE("Insurance", Material.BLUE_CANDLE);

    private final String displayName;
    private final Material displayIcon;

    BlackJackActionType(
            String displayName,
            Material displayIcon
    ) {
        this.displayName = displayName;
        this.displayIcon = displayIcon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getDisplayIcon() {
        return displayIcon;
    }
}
