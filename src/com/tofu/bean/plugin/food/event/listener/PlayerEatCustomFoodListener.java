package com.tofu.bean.plugin.food.event.listener;

import com.tofu.bean.data.methods.item.CustomFoodItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerEatCustomFoodListener {

    public void call(PlayerItemConsumeEvent event) {
        ItemStack itemStack = event.getItem();

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            return;
        }

        String displayName = itemMeta.getDisplayName();
        Material material = itemStack.getType();

        CustomFoodItem customFood = CustomFoodItem.isCustomFood(displayName,material);

        if (customFood == null) {
            return;
        }

        Player player = event.getPlayer();
        customFood.activeFood(player);
    }
}
