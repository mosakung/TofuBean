package com.tofu.bean.plugin.casino.coupon;

import com.tofu.bean.data.enums.item.CustomMaterialItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import static com.tofu.bean.data.enums.item.CustomMaterialItem.CASINO_COUPON;

public class CouponManagement {

    private final CustomMaterialItem coupon = CASINO_COUPON;

    public static void giveCoupon(Player player, int amount) {
        ItemStack itemStack = CASINO_COUPON.generateItemStack(amount);
        player.getInventory().addItem(itemStack);
    }

    public static Boolean retrieveCoupon(Player player, int amount) {
        Inventory inventory = player.getInventory();

        if (hasCoupon(player, amount)) {
            for (int index = 0; index < inventory.getSize(); index++) {
                ItemStack currentItems = inventory.getItem(index);

                if (currentItems != null && currentItems.getType() == Material.LIGHT && currentItems.getAmount() >= amount) {
                    ItemMeta itemMeta = currentItems.getItemMeta();

                    if (itemMeta != null) {
                        String displayName = itemMeta.getDisplayName();

                        if (displayName.equals(CASINO_COUPON.getItemName())) {
                            currentItems.setAmount(currentItems.getAmount() - amount);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static Boolean hasCoupon(Player player, int amount) {
        ItemStack itemStack = CASINO_COUPON.generateItemStack(amount);
        PlayerInventory inventory = player.getInventory();
        return inventory.containsAtLeast(itemStack, amount);
    }
}
