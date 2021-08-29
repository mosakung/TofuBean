package com.tofu.bean.utils.mapper;

import org.bukkit.Material;

import java.util.Random;

import static com.tofu.bean.utils.UtilsBean.doublerFormatP2;

public class CropMapper {

    private static Random rand = new Random();

    public static Boolean mapAvailableCrop(Material type) {

        return switch (type) {
            case WHEAT, BEETROOTS, CARROTS, POTATOES -> true;
            default -> false;
        };
    }

    public static Double mapCrop2Value(Material crop) {

        Double parameter = rand.nextDouble();
        Double value = 0.1 + (2.4 * parameter);

        return switch (crop) {
            case WHEAT, BEETROOTS, CARROTS, POTATOES -> doublerFormatP2(value);
            default -> 0.0;
        };
    }

    public static Material mapCrop2Seed(Material crop) {
        return switch (crop) {
            case WHEAT -> Material.WHEAT_SEEDS;
            case BEETROOTS -> Material.BEETROOT_SEEDS;
            case CARROTS -> Material.CARROT;
            case POTATOES -> Material.POTATO;
            default -> null;
        };
    }
}
