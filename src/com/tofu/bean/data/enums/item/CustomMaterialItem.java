package com.tofu.bean.data.enums.item;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import com.tofu.bean.data.generate.buy.impl.casino.GenCasinoCoupon;
import com.tofu.bean.data.generate.buy.impl.food.GenBreadSmallFast;
import com.tofu.bean.data.generate.buy.impl.food.GenLuckyApple;
import com.tofu.bean.data.generate.buy.impl.normal.GenNormalItem;
import com.tofu.bean.data.generate.buy.impl.tipped.GenHarmingArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenHealingArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenSlownessArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenWeaknessArrow;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum CustomMaterialItem {
    NAME_TAG("Name Tag", new GenNormalItem(Material.NAME_TAG)),
    SADDLE("Saddle", new GenNormalItem(Material.SADDLE)),
    ELYTRA("Elytra", new GenNormalItem(Material.ELYTRA)),
    ARROW_HEALING("Healing Arrow", new GenHealingArrow()),
    ARROW_HARMING("Harming Arrow", new GenHarmingArrow()),
    ARROW_WEAKNESS("Weakness Arrow", new GenWeaknessArrow()),
    ARROW_SLOWNESS("Slowness Arrow", new GenSlownessArrow()),
    FIREWORK_ROCKET("Firework Rocket", new GenNormalItem(Material.FIREWORK_ROCKET)),
    BREAD_SMALL_FAST_FOOD("Fast Bread", new GenBreadSmallFast()),
    APPLE("Apple", new GenNormalItem(Material.APPLE)),
    LUCKY_APPLE_FOOD("Lucky Apple", new GenLuckyApple()),
    STRING("String", new GenNormalItem(Material.STRING)),
    BUCKET_OF_TROPICAL_FISH("Bucket Tropical", new GenNormalItem(Material.TROPICAL_FISH_BUCKET)),
    CLAY_BALL("clay ball", new GenNormalItem(Material.CLAY_BALL)),
    SPONGE("Sponge", new GenNormalItem(Material.SPONGE)),
    CASINO_COUPON("Casino Coupon", new GenCasinoCoupon()),
    DRAGON_BREATH("Dragon Breath", new GenNormalItem(Material.DRAGON_BREATH));

    private final String itemName;
    private final GenerateItemStackFunc generateItemStackFunc;

    CustomMaterialItem(
            String itemName,
            GenerateItemStackFunc generateItemStackFunc
    ) {
        this.itemName = itemName;
        this.generateItemStackFunc = generateItemStackFunc;
    }

    public String getItemName() {
        return itemName;
    }

    public ItemStack generateItemStack(int amount) {
        return this.generateItemStackFunc.generate(amount);
    }

    public Boolean equal(String itemName) {
        return this.itemName.equals(itemName);
    }

    public static CustomMaterialItem getCustomMaterialItem(String itemName) {
        AtomicReference<CustomMaterialItem> _customMaterialItem = new AtomicReference<>(null);

        Arrays.stream(CustomMaterialItem.values()).forEach(customMaterialItem -> {
            if(customMaterialItem.equal(itemName)) {
                _customMaterialItem.set(customMaterialItem);
            }
        });

        return _customMaterialItem.get();
    }
}


