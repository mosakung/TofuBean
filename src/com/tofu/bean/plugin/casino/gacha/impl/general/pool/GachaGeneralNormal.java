package com.tofu.bean.plugin.casino.gacha.impl.general.pool;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import com.tofu.bean.data.generate.buy.impl.casino.GenCasinoCoupon;
import com.tofu.bean.data.generate.buy.impl.food.GenBreadSmallFast;
import com.tofu.bean.data.generate.buy.impl.food.GenLuckyApple;
import com.tofu.bean.data.generate.buy.impl.normal.GenNormalItem;
import com.tofu.bean.data.generate.buy.impl.tipped.GenHarmingArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenHealingArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenSlownessArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenWeaknessArrow;
import com.tofu.bean.plugin.casino.gacha.contact.GachaCouponPool;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum GachaGeneralNormal implements GachaCouponPool {
    IRON_INGOT(new GenNormalItem(Material.IRON_INGOT), 16, 100.0),
    GOLD_INGOT(new GenNormalItem(Material.GOLD_INGOT), 16, 100.0),
    LAPIS_LAZULI(new GenNormalItem(Material.LAPIS_LAZULI), 24, 100.0),
    REDSTONE(new GenNormalItem(Material.REDSTONE), 24, 100.0),
    QUARTZ(new GenNormalItem(Material.QUARTZ), 24, 100.0),
    COAL(new GenNormalItem(Material.COAL), 64, 100.0),
    EMERALD(new GenNormalItem(Material.EMERALD), 16, 100.0),
    DIAMOND(new GenNormalItem(Material.DIAMOND), 8, 100.0),
    GUNPOWDER(new GenNormalItem(Material.GUNPOWDER), 24, 100.0),
    IRON_BLOCK(new GenNormalItem(Material.IRON_BLOCK), 4, 25.0),
    GOLD_BLOCK(new GenNormalItem(Material.GOLD_BLOCK), 4, 25.0),
    LAPIS_BLOCK(new GenNormalItem(Material.LAPIS_BLOCK), 6, 25.0),
    REDSTONE_BLOCK(new GenNormalItem(Material.REDSTONE_BLOCK), 6, 25.0),
    QUARTZ_RATE_LOW(new GenNormalItem(Material.QUARTZ), 64, 25.0),
    COAL_BLOCK(new GenNormalItem(Material.COAL_BLOCK), 16, 25.0),
    EMERALD_BLOCK(new GenNormalItem(Material.EMERALD_BLOCK), 4, 25.0),
    DIAMOND_BLOCK(new GenNormalItem(Material.DIAMOND_BLOCK), 2, 25.0),
    SHULKER_SHELL(new GenNormalItem(Material.SHULKER_SHELL), 2, 25.0),
    FAST_BREAD(new GenBreadSmallFast(), 32, 100.0),
    NAME_TAG(new GenNormalItem(Material.NAME_TAG), 1, 75.0),
    SADDLE(new GenNormalItem(Material.SADDLE), 1, 75.0),
    IRON_HORSE_ARMOR(new GenNormalItem(Material.IRON_HORSE_ARMOR), 1, 75.0),
    GOLDEN_HORSE_ARMOR(new GenNormalItem(Material.GOLDEN_HORSE_ARMOR), 1, 50.0),
    DIAMOND_HORSE_ARMOR(new GenNormalItem(Material.DIAMOND_HORSE_ARMOR), 1, 35.0),
    GLOWSTONE_DUST(new GenNormalItem(Material.GLOWSTONE_DUST), 24, 100.0),
    NETHERITE_SCRAP(new GenNormalItem(Material.NETHERITE_SCRAP), 1, 10.0),
    GOLDEN_CARROT(new GenNormalItem(Material.GOLDEN_CARROT), 32, 100.0),
    GOLDEN_APPLE(new GenNormalItem(Material.GOLDEN_APPLE), 8, 90.0),
    ARROW_HEALING(new GenHealingArrow(), 16, 100.0),
    ARROW_HARMING(new GenHarmingArrow(), 16, 100.0),
    ARROW_WEAKNESS(new GenWeaknessArrow(), 16, 100.0),
    ARROW_SLOWNESS(new GenSlownessArrow(), 16, 100.0),
    LUCKY_APPLE_FOOD(new GenLuckyApple(), 16, 80.0),
    COOKED_BEEF(new GenNormalItem(Material.COOKED_BEEF), 64, 100.0),
    PUMPKIN_PIE(new GenNormalItem(Material.PUMPKIN_PIE), 64, 100.0),
    EXPERIENCE_BOTTLE(new GenNormalItem(Material.EXPERIENCE_BOTTLE), 16, 50.0),
    CALCITE(new GenNormalItem(Material.CALCITE), 64, 100.0),
    CASINO_COUPON_5(new GenCasinoCoupon(), 5, 100.0),
    CASINO_COUPON_10(new GenCasinoCoupon(), 10, 50.0),
    CASINO_COUPON_15(new GenCasinoCoupon(), 15, 25.0),
    CASINO_COUPON_20(new GenCasinoCoupon(), 20, 12.5),
    ;

    private final GenerateItemStackFunc generateItemStackFunc;
    private final int amount;
    private final Double weight;

    GachaGeneralNormal(
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
