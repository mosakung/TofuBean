package com.tofu.bean.data.enums.item;

import com.tofu.bean.data.generate.food.impl.ActiveBreadSmallSpeed;
import com.tofu.bean.data.generate.food.ActiveCustomFood;
import com.tofu.bean.data.generate.food.impl.ActiveLuckyApple;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum CustomFoodItem {
    BREAD_SMALL_FAST("Speed Bread", Material.BREAD, new ActiveBreadSmallSpeed()),
    LUCKY_APPLE("Lucky Apple",Material.APPLE, new ActiveLuckyApple());

    private final String name;
    private final Material material;
    private final ActiveCustomFood activeCustomFood;

    CustomFoodItem(
            String name,
            Material material,
            ActiveCustomFood activeCustomFood
    ) {
        this.name = ChatColor.ITALIC + name;
        this.material = material;
        this.activeCustomFood = activeCustomFood;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public void activeFood(Player player) {
        this.activeCustomFood.active(player);
    }

    public Boolean equal(String name, Material material) {
        return this.name.equals(name) && this.material == material;
    }

    public static CustomFoodItem isCustomFood(String name, Material material) {
        AtomicReference<CustomFoodItem> customFood = new AtomicReference<>(null);

        Arrays.stream(CustomFoodItem.values()).forEach(customFoodItem -> {

            if (customFoodItem.equal(name, material)) {
                customFood.set(customFoodItem);
            }
        });

        return customFood.get();
    }
}
