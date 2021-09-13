package com.tofu.bean.data.generate.buy.impl.food;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

import static com.tofu.bean.data.enums.item.CustomFoodItem.LUCKY_APPLE;

public class GenLuckyApple implements GenerateItemStackFunc {

    @Override
    public ItemStack generate(int amount) {
        ItemStack itemStack = new ItemStack(Material.APPLE, amount);
        ItemMeta itemStackMeta = itemStack.getItemMeta();

        Objects.requireNonNull(itemStackMeta).setDisplayName(LUCKY_APPLE.getName());

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Lucky Apple when you eat buff your lucky");
        itemStackMeta.setLore(lore);

        itemStack.setItemMeta(itemStackMeta);

        return itemStack;
    }
}
