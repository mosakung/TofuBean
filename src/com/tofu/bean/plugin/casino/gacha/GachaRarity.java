package com.tofu.bean.plugin.casino.gacha;

public enum GachaRarity {
    NORMAL("Normal"),
    RARE("Rare"),
    EPIC("Epic"),
    LEGENDARY("Legendary");

    private final String rarity;

    GachaRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity;
    }
}
