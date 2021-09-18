package com.tofu.bean.plugin.casino.gacha.impl.general.pool;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import com.tofu.bean.data.generate.buy.impl.casino.GenCasinoCoupon;
import com.tofu.bean.data.generate.buy.impl.normal.GenNormalItem;
import com.tofu.bean.data.generate.buy.impl.tipped.GenHarmingArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenHealingArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenSlownessArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenWeaknessArrow;
import com.tofu.bean.plugin.casino.gacha.contact.GachaCouponPool;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum GachaGeneralRare implements GachaCouponPool {
    MUSIC_DICE_11(new GenNormalItem(Material.MUSIC_DISC_11), 1, 7.7),
    MUSIC_DICE_13(new GenNormalItem(Material.MUSIC_DISC_13), 1, 7.7),
    MUSIC_DICE_BLOCKS(new GenNormalItem(Material.MUSIC_DISC_BLOCKS), 1, 7.7),
    MUSIC_DICE_CAT(new GenNormalItem(Material.MUSIC_DISC_CHIRP), 1 ,7.7),
    MUSIC_DICE_FAR(new GenNormalItem(Material.MUSIC_DISC_FAR), 1, 7.7),
    MUSIC_DICE_MALL(new GenNormalItem(Material.MUSIC_DISC_MALL), 1, 7.7),
    MUSIC_DISC_MELLOHI(new GenNormalItem(Material.MUSIC_DISC_MELLOHI), 1, 7.7),
    MUSIC_DISC_PIGSTEP(new GenNormalItem(Material.MUSIC_DISC_PIGSTEP), 1, 7.7),
    MUSIC_DISC_STAL(new GenNormalItem(Material.MUSIC_DISC_STAL), 1, 7.7),
    MUSIC_DISC_STRAD(new GenNormalItem(Material.MUSIC_DISC_STRAD), 1, 7.7),
    MUSIC_DISC_WAIT(new GenNormalItem(Material.MUSIC_DISC_WAIT), 1, 7.7),
    MUSIC_DISC_WARD(new GenNormalItem(Material.MUSIC_DISC_WARD), 1, 7.7),
    NETHERITE_SCRAP(new GenNormalItem(Material.NETHERITE_SCRAP), 4, 100.0),
    NETHERITE_SCRAP_8(new GenNormalItem(Material.NETHERITE_SCRAP), 8, 55.0),
    NETHERITE_INGOT(new GenNormalItem(Material.NETHERITE_INGOT), 1, 100.0),
    NETHERITE_BLOCK(new GenNormalItem(Material.NETHERITE_BLOCK), 1, 12.0),
    GOLDEN_APPLE(new GenNormalItem(Material.GOLDEN_APPLE), 32, 100.0),
    ENCHANTED_GOLDEN_APPLE(new GenNormalItem(Material.ENCHANTED_GOLDEN_APPLE), 1, 100.0),
    ENCHANTED_GOLDEN_APPLE_4(new GenNormalItem(Material.ENCHANTED_GOLDEN_APPLE), 4, 35.0),
    ARROW_HEALING(new GenHealingArrow(), 64, 100.0),
    ARROW_HARMING(new GenHarmingArrow(), 64, 100.0),
    ARROW_WEAKNESS(new GenWeaknessArrow(), 64, 100.0),
    ARROW_SLOWNESS(new GenSlownessArrow(), 64, 100.0),
    CASINO_COUPON_15(new GenCasinoCoupon(), 15, 100.0),
    CASINO_COUPON_20(new GenCasinoCoupon(), 20, 70.0),
    CASINO_COUPON_30(new GenCasinoCoupon(), 30, 50.0),
    CASINO_COUPON_40(new GenCasinoCoupon(), 40, 15.0),
    WITHER_SKELETON_SKULL(new GenNormalItem(Material.WITHER_SKELETON_SKULL), 3, 100.0),
    WITHER_SKELETON_SKULL_6(new GenNormalItem(Material.WITHER_SKELETON_SKULL), 6, 50.0),
    NETHER_STAR(new GenNormalItem(Material.NETHER_STAR), 1, 80.0),
    END_CRYSTAL(new GenNormalItem(Material.END_CRYSTAL), 4, 100.0),
    SPECTRAL_ARROW(new GenNormalItem(Material.SPECTRAL_ARROW), 64, 100.0),
    TRIDENT(new GenNormalItem(Material.TRIDENT), 1, 15.0),
    AXOLOTL_SPAWN_EGG(new GenNormalItem(Material.AXOLOTL_SPAWN_EGG), 2, 100.0),
    DOLPHIN_SPAWN_EGG(new GenNormalItem(Material.DOLPHIN_SPAWN_EGG), 2, 100.0),
    PANDA_SPAWN_EGG(new GenNormalItem(Material.PANDA_SPAWN_EGG), 2, 100.0),
    POLAR_BEAR_SPAWN_EGG(new GenNormalItem(Material.POLAR_BEAR_SPAWN_EGG), 2, 100.0),
    SHULKER_SHELL(new GenNormalItem(Material.SHULKER_SHELL), 16, 100.0),
    EMERALD_BLOCK(new GenNormalItem(Material.EMERALD_BLOCK), 16, 100.0),
    DIAMOND_BLOCK(new GenNormalItem(Material.DIAMOND_BLOCK), 16, 100.0),
    ZOMBIE_HORSE_SPAWN_EGG(new GenNormalItem(Material.ZOMBIE_HORSE_SPAWN_EGG), 1, 25.0),
    PLAYER_HEAD(new GenNormalItem(Material.PLAYER_HEAD), 1, 100.0),
    CREEPER_HEAD(new GenNormalItem(Material.CREEPER_HEAD), 1, 100.0),
    ZOMBIE_HEAD(new GenNormalItem(Material.ZOMBIE_HEAD), 1, 100.0),
    CRYING_OBSIDIAN(new GenNormalItem(Material.CRYING_OBSIDIAN), 16, 100.0),
    ;

    private final GenerateItemStackFunc generateItemStackFunc;
    private final int amount;
    private final Double weight;

    GachaGeneralRare(
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
