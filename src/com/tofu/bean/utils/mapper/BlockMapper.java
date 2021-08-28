package com.tofu.bean.utils.mapper;

import org.bukkit.Material;

public class BlockMapper {
    public double blockType(Material material){
        switch (material){
            case DIAMOND_ORE:
                return 30;
            case DEEPSLATE_DIAMOND_ORE:
                return 31;
            case COAL_ORE:
                return 1;
            case DEEPSLATE_COAL_ORE:
                return 2;
            case GOLD_ORE:
            case LAPIS_ORE:
                return 7;
            case DEEPSLATE_GOLD_ORE:
            case DEEPSLATE_LAPIS_ORE:
                return 8;
            case IRON_ORE:
            case REDSTONE_ORE:
                return 3;
            case DEEPSLATE_IRON_ORE:
            case DEEPSLATE_REDSTONE_ORE:
                return 4;
            case COPPER_ORE:
                return 2;
            case DEEPSLATE_COPPER_ORE:
                return 3;
            case EMERALD_ORE:
                return 100;
            case DEEPSLATE_EMERALD_ORE:
                return 101;
            case NETHER_QUARTZ_ORE:
                return 1.5;
            default:
                return 0;
        }
    }
}
