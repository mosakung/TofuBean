package com.tofu.bean.utils.mapper;

import org.bukkit.Material;

public class SignMapper {

    public static Boolean isSignMaterial(Material material) {
        return switch (material) {
            case SPRUCE_SIGN, SPRUCE_WALL_SIGN,
                    ACACIA_SIGN, ACACIA_WALL_SIGN,
                    BIRCH_SIGN, BIRCH_WALL_SIGN,
                    CRIMSON_SIGN, CRIMSON_WALL_SIGN,
                    JUNGLE_SIGN, JUNGLE_WALL_SIGN,
                    OAK_SIGN, OAK_WALL_SIGN,
                    DARK_OAK_SIGN, DARK_OAK_WALL_SIGN,
                    WARPED_SIGN, WARPED_WALL_SIGN -> true;
            default -> false;
        };
    }

}
