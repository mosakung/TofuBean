package com.tofu.bean.plugin.casino.gacha.impl.general.pool;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import com.tofu.bean.data.generate.buy.impl.casino.GenCasinoCoupon;
import com.tofu.bean.data.generate.buy.impl.normal.GenNormalItem;
import com.tofu.bean.plugin.casino.gacha.contact.GachaCouponPool;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum GachaGeneralEpic implements GachaCouponPool {
    ELYTRA(new GenNormalItem(Material.ELYTRA), 1, 100.0),
    ZOMBIE_HORSE_SPAWN_EGG(new GenNormalItem(Material.ZOMBIE_HORSE_SPAWN_EGG), 1, 100.0),
    TRIDENT(new GenNormalItem(Material.TRIDENT), 1, 100.0),
    ENCHANTED_GOLDEN_APPLE(new GenNormalItem(Material.ENCHANTED_GOLDEN_APPLE), 8, 100.0),
    ENCHANTED_GOLDEN_APPLE_16(new GenNormalItem(Material.ENCHANTED_GOLDEN_APPLE), 16, 55.0),
    ENCHANTED_GOLDEN_APPLE_24(new GenNormalItem(Material.ENCHANTED_GOLDEN_APPLE), 24, 25.0),
    CASINO_COUPON_25(new GenCasinoCoupon(), 25, 100.0),
    CASINO_COUPON_30(new GenCasinoCoupon(), 30, 70.0),
    CASINO_COUPON_40(new GenCasinoCoupon(), 40, 50.0),
    CASINO_COUPON_50(new GenCasinoCoupon(), 50, 15.0),
    NETHERITE_SCRAP(new GenNormalItem(Material.NETHERITE_SCRAP), 32, 100.0),
    NETHERITE_INGOT(new GenNormalItem(Material.NETHERITE_INGOT), 8, 95.0),
    NETHERITE_BLOCK(new GenNormalItem(Material.NETHERITE_BLOCK), 4, 12.0),
    NETHER_STAR(new GenNormalItem(Material.NETHER_STAR), 3, 100.0),
    NETHER_STAR_6(new GenNormalItem(Material.NETHER_STAR), 6, 50.0),
    BEACON(new GenNormalItem(Material.BEACON), 3, 95.0),
    TOTEM_OF_UNDYING(new GenNormalItem(Material.TOTEM_OF_UNDYING), 1, 100.0),
    HEART_OF_THE_SEA(new GenNormalItem(Material.HEART_OF_THE_SEA), 1, 100.0),
    CRYING_OBSIDIAN(new GenNormalItem(Material.CRYING_OBSIDIAN), 64, 100.0),
    EMERALD_BLOCK(new GenNormalItem(Material.EMERALD_BLOCK), 64, 100.0),
    DIAMOND_BLOCK(new GenNormalItem(Material.DIAMOND_BLOCK), 64, 100.0),
    ;

    private final GenerateItemStackFunc generateItemStackFunc;
    private final int amount;
    private final Double weight;

    GachaGeneralEpic(
            GenerateItemStackFunc generateItemStackFunc,
            int amount,
            Double weight
    ) {
        this.generateItemStackFunc = generateItemStackFunc;
        this.amount = amount;
        this.weight = weight;
    }

    @Override
    public GenerateItemStackFunc getGenerateItemStackFunc() {
        return generateItemStackFunc;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public Double getWeight() {
        return weight;
    }

    @Override
    public ItemStack generate() {
        return generateItemStackFunc.generate(this.amount);
    }
}
