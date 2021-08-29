package com.tofu.bean.plugin.event.fram;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.presentation.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static com.tofu.bean.utils.mapper.CropMapper.*;

public class HarvestCropEvent {

    private PlayerBeansInteractor playerBeansInteractor;

    public HarvestCropEvent(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void call(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        Material itemHolding = player.getInventory().getItemInMainHand().getType();
        Block crop = event.getBlock();
        Material cropType = crop.getType();

        if (mapAvailableCrop(cropType)) {

            BlockData data = crop.getBlockData();
            Ageable ageable = (Ageable) data;

            if (isFullyGrow(ageable)) {
                Double money = mapCrop2Value(cropType);

                playerBeansInteractor.increasedValue(playerName, money);
                player.sendMessage(ChatColor.AQUA + "+ " + ChatColor.GOLD + money.toString() + ChatColor.AQUA + " Beans");

                switch (itemHolding) {
                    case WOODEN_HOE, STONE_HOE, IRON_HOE, GOLDEN_HOE, DIAMOND_HOE, NETHERITE_HOE: {
                        if (isSeedInInventory(player, cropType)) {
                            Material seed = mapCrop2Seed(cropType);
                            removeSeedInInventory(player, seed);
                            replantCrop(crop.getLocation(), cropType);
                        }
                    }
                    default:
                }
            }
        }


    }

    private void replantCrop(Location location, Material cropBlockType) {
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            location.getBlock().setType(cropBlockType);
        }, 20L);
    }

    private Boolean isSeedInInventory(Player player, Material crop) {
        PlayerInventory inventory = player.getInventory();

        if (mapAvailableCrop(crop)) {
            return inventory.contains(mapCrop2Seed(crop));
        }
        return false;
    }

    private void removeSeedInInventory(Player player, Material seed) {
        int seedIndexLocation = -1;
        ItemStack currentItems;
        PlayerInventory inventory = player.getInventory();

        for (int index = 0; index < inventory.getSize(); index++) {
            currentItems = inventory.getItem(index);

            if (currentItems != null && currentItems.getType() == seed) {
                seedIndexLocation = index;

                index = inventory.getSize();
            }
        }

        if (seedIndexLocation != -1) {
            ItemStack seedItemStack = inventory.getItem(seedIndexLocation);
            if (seedItemStack != null) {
                int seedAmount = seedItemStack.getAmount();
                seedItemStack.setAmount(seedAmount - 1);
            }
        }
    }

    private Boolean isFullyGrow(Ageable ageable) {
        return ageable.getAge() == ageable.getMaximumAge();
    }
}
