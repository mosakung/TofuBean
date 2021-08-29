package com.tofu.bean.utils.mapper;

import org.bukkit.entity.EntityType;

public class EntityMapper {

    public static Double mapMobMonster2Value(EntityType type) {

        return switch (type) {
            case SLIME -> 4.0;
            case MAGMA_CUBE, ZOMBIE_HORSE, SKELETON_HORSE -> 5.0;
            case SPIDER -> 8.0;
            case CAVE_SPIDER -> 9.0;
            case ZOMBIE, DROWNED, HUSK, BAT -> 12.0;
            case ZOMBIFIED_PIGLIN -> 13.0;
            case SKELETON, VINDICATOR, BLAZE, SILVERFISH -> 15.0;
            case CREEPER -> 17.0;
            case HOGLIN -> 18.0;
            case ENDERMAN, GUARDIAN, WITHER_SKELETON, STRAY, ZOGLIN -> 20.0;
            case PIGLIN_BRUTE -> 22.0;
            case ZOMBIE_VILLAGER, PHANTOM, GHAST, VEX -> 25.0;
            case WITCH, RAVAGER -> 45.0;
            case SHULKER -> 50.0;
            case ELDER_GUARDIAN -> 5000.0;
            case ENDER_DRAGON, WITHER -> 1500.0;
            default -> 0.0;
        };
    }
}
