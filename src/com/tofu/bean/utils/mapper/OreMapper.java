package com.tofu.bean.utils.mapper;

import org.bukkit.Material;

public class BlockMapper {
    public double blockType(Material material) {

        return switch (material) {
            case COAL_ORE -> 1;
            case NETHER_QUARTZ_ORE -> 1.5;
            case DEEPSLATE_COAL_ORE, COPPER_ORE -> 2;
            case IRON_ORE, REDSTONE_ORE, DEEPSLATE_COPPER_ORE -> 3;
            case DEEPSLATE_IRON_ORE, DEEPSLATE_REDSTONE_ORE -> 4;
            case GOLD_ORE, LAPIS_ORE -> 7;
            case DEEPSLATE_GOLD_ORE, DEEPSLATE_LAPIS_ORE -> 8;
            case DIAMOND_ORE -> 30;
            case DEEPSLATE_DIAMOND_ORE -> 31;
            case EMERALD_ORE -> 100;
            case DEEPSLATE_EMERALD_ORE -> 101;
            default -> 0;
        };
    }
}
