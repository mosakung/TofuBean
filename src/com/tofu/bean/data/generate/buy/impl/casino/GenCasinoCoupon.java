package com.tofu.bean.data.generate.buy.impl.casino;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static com.tofu.bean.data.enums.item.CustomMaterialItem.CASINO_COUPON;

public class GenCasinoCoupon implements GenerateItemStackFunc {

    @Override
    public ItemStack generate(int amount) {
        ItemStack itemStack = new ItemStack(Material.LIGHT, amount);
        ItemMeta itemStackMeta = itemStack.getItemMeta();

        if (itemStackMeta != null) {
            itemStackMeta.setDisplayName(CASINO_COUPON.getItemName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add("this coupon can be change to reward in casino place");
            itemStackMeta.setLore(lore);

            itemStack.setItemMeta(itemStackMeta);
        }

        return itemStack;
    }
}
