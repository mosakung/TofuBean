package com.tofu.bean.plugin.sign.event.impl;

import com.tofu.bean.data.enums.item.CustomMaterialItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class SignActionDefault {

    public Boolean hasItem2Exchange(Player player, Material exchangeItem, int amount) {
        PlayerInventory inventory = player.getInventory();
        ItemStack requireItem = new ItemStack(exchangeItem);

        return inventory.containsAtLeast(requireItem, amount);
    }

    public void sendItem2Player(Player player, CustomMaterialItem materialType, int amount) {
        ItemStack itemStack = materialType.generateItemStack(amount);
        player.getInventory().addItem(itemStack);
    }

    public Boolean pullItemPlayer(Player player, Material material, int amount) {
        int exchangeItemIndexLocation = -1;
        ItemStack currentItems;
        PlayerInventory inventory = player.getInventory();

        for (int index = 0; index < inventory.getSize(); index++) {
            currentItems = inventory.getItem(index);

            if (currentItems != null && currentItems.getType() == material && currentItems.getAmount() >= amount) {

                ItemMeta itemMeta = currentItems.getItemMeta();

                if(itemMeta != null) {
                    String displayName = itemMeta.getDisplayName();

                    if (displayName.equals("")) {
                        exchangeItemIndexLocation = index;
                        index = inventory.getSize();
                    }
                }
            }
        }

        if (exchangeItemIndexLocation != -1) {
            ItemStack exchangeItemStack = inventory.getItem(exchangeItemIndexLocation);
            if (exchangeItemStack != null) {
                int seedAmount = exchangeItemStack.getAmount();
                exchangeItemStack.setAmount(seedAmount - amount);
                return true;
            }
        }

        return false;
    }
}
