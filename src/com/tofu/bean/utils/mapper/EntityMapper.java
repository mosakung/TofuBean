package com.tofu.bean.utils.mapper;

import org.bukkit.entity.EntityType;

public class EntityMapper {

    public Double mapper2Value(EntityType type) {

        if (type == EntityType.ZOMBIE) return 12.0;
        if (type == EntityType.SKELETON) return 15.0;
        if (type == EntityType.SPIDER) return 8.0;
        if (type == EntityType.CREEPER) return 17.0;

        if (type == EntityType.CAVE_SPIDER) return 9.0;
        if (type == EntityType.SLIME) return 4.0;
        if (type == EntityType.ENDERMAN) return 20.0;

        if (type == EntityType.WITCH) return 45.0;
        if (type == EntityType.ZOMBIE_VILLAGER) return 25.0;
        if (type == EntityType.RAVAGER) return 45.0;
        if (type == EntityType.VINDICATOR) return 15.0;

        if (type == EntityType.DROWNED) return 12.0;
        if (type == EntityType.GUARDIAN) return 20.0;

        if (type == EntityType.HUSK) return 12.0;

        if (type == EntityType.PHANTOM) return 25.0;

        if (type == EntityType.BLAZE) return 15.0;
        if (type == EntityType.ZOMBIFIED_PIGLIN) return 13.0;
        if (type == EntityType.GHAST) return 25.0;
        if (type == EntityType.MAGMA_CUBE) return 5.0;
        if (type == EntityType.WITHER_SKELETON) return 20.0;
        if (type == EntityType.PIGLIN_BRUTE) return 22.0;
        if (type == EntityType.HOGLIN) return 18.0;

        if (type == EntityType.SILVERFISH) return 15.0;

        if (type == EntityType.STRAY) return 16.0;
        if (type == EntityType.VEX) return 25.0;

        if (type == EntityType.ZOGLIN) return 20.0;
        if (type == EntityType.ZOMBIE_HORSE) return 5.0;
        if (type == EntityType.SKELETON_HORSE) return 5.0;

        if (type == EntityType.SHULKER) return 0.0;

        if (type == EntityType.ELDER_GUARDIAN) return 5000.0;
        if (type == EntityType.ENDER_DRAGON) return 1500.0;
        if (type == EntityType.WITHER) return 1500.0;

        return 0.0;
    }
}
