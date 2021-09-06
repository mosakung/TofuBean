package com.tofu.bean.utils.mapper;

import org.bukkit.Material;

public class FishingMapper {

    public static Double mapCaughtFishing2Value(Material type) {
        return switch (type) {
            case BOWL, LEATHER_BOOTS, ROTTEN_FLESH, STICK -> 3.0;
            case STRING, GLASS_BOTTLE, BONE, INK_SAC -> 7.0;
            case LILY_PAD, BAMBOO, COCOA_BEANS, LEATHER -> 9.0;
            case COD  -> 12.0;
            case SALMON -> 15.0;
            case BOW, FISHING_ROD, TRIPWIRE_HOOK -> 23.0;
            case PUFFERFISH -> 27.0;
            case TROPICAL_FISH, NAUTILUS_SHELL -> 50.0;
            case ENCHANTED_BOOK, NAME_TAG, SADDLE -> 150.0;
            default -> 0.0;
        };
    }
}
