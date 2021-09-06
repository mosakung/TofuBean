package com.tofu.bean.data.methods.item;

import com.tofu.bean.data.generate.buy.contract.GenerateItemStackFunc;
import com.tofu.bean.data.generate.buy.impl.GenBreadSmallFast;
import com.tofu.bean.data.generate.buy.impl.normal.GenNormalItem;
import com.tofu.bean.data.generate.buy.impl.tipped.GenHarmingArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenHealingArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenSlownessArrow;
import com.tofu.bean.data.generate.buy.impl.tipped.GenWeaknessArrow;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomMaterialItem {
    NAME_TAG("Name Tag", new GenNormalItem(Material.NAME_TAG)),
    SADDLE("Saddle", new GenNormalItem(Material.SADDLE)),
    ELYTRA("Elytra", new GenNormalItem(Material.ELYTRA)),
    ARROW_HEALING("Healing Arrow", new GenHealingArrow()),
    ARROW_HARMING("Harming Arrow", new GenHarmingArrow()),
    ARROW_WEAKNESS("Weakness Arrow", new GenWeaknessArrow()),
    ARROW_SLOWNESS("Slowness Arrow", new GenSlownessArrow()),
    FIREWORK_ROCKET("Firework Rocket", new GenNormalItem(Material.FIREWORK_ROCKET)),
    BREAD_SMALL_FAST_FOOD("Fast Bread", new GenBreadSmallFast());

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
}


