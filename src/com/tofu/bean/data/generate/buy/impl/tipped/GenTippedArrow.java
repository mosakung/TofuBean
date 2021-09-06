package com.tofu.bean.data.generate.buy.impl.tipped;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

import java.util.Objects;

public class GenTippedArrow {

    public ItemStack createTippedArrow(int amount, PotionData potionData) {
        ItemStack itemStack = new ItemStack(Material.TIPPED_ARROW, amount);

        PotionMeta meta = (PotionMeta) itemStack.getItemMeta();
        Objects.requireNonNull(meta).setBasePotionData(potionData);
        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
