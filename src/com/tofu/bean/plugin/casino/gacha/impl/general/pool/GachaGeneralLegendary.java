package com.tofu.bean.plugin.casino.gacha.impl.general.pool;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import com.tofu.bean.data.generate.buy.impl.normal.GenNormalItem;
import com.tofu.bean.plugin.casino.gacha.contact.GachaCouponPool;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum GachaGeneralLegendary implements GachaCouponPool {
    DRAGON_EGG(new GenNormalItem(Material.DRAGON_EGG), 1, 100.0);

    private final GenerateItemStackFunc generateItemStackFunc;
    private final int amount;
    private final Double weight;

    GachaGeneralLegendary(
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
