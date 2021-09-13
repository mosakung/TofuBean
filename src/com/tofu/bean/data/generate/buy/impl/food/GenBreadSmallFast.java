package com.tofu.bean.data.generate.buy.impl.food;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

import static com.tofu.bean.data.enums.item.CustomFoodItem.BREAD_SMALL_FAST;

public class GenBreadSmallFast implements GenerateItemStackFunc {

    @Override
    public ItemStack generate(int amount) {

        ItemStack itemStack = new ItemStack(Material.BREAD, amount);
        ItemMeta itemStackMeta = itemStack.getItemMeta();

        Objects.requireNonNull(itemStackMeta).setDisplayName(BREAD_SMALL_FAST.getName());

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Bread when you eat buff your speed");
        itemStackMeta.setLore(lore);

        itemStack.setItemMeta(itemStackMeta);

        return itemStack;
    }
}
