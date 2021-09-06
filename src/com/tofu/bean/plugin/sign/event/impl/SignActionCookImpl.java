package com.tofu.bean.plugin.sign.event.impl;

import com.tofu.bean.data.methods.sign.branch.CookSignBranch;
import com.tofu.bean.data.methods.item.CustomMaterialItem;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.sign.event.contract.SignActionCook;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class SignActionCookImpl implements SignActionCook {

    private final PlayerBeansInteractor playerBeansInteractor;

    public SignActionCookImpl(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void action(Player player, CookSignBranch cookItemMethod) {

        String playerName = player.getName();
        Double pocketValue = playerBeansInteractor.getValue(playerName);

        if (pocketValue == null) {
            player.sendMessage(ChatColor.DARK_RED + "Something Error (PlayerUseSignShop (CookItemSign)) tell BearSouL : pocketValue == null");
            return;
        }

        Double cost = cookItemMethod.getBeansValue();
        int amount = cookItemMethod.getAmountValue();

        if (cost == null || amount == -1) {
            return;
        }

        if (pocketValue >= cost) {

            Material exchangeItem = cookItemMethod.getExchangeItem();
            int amountExchange = cookItemMethod.getAmountExchange();

            if(hasItem2Exchange(player, exchangeItem, amountExchange)) {
                playerBeansInteractor.decreasedValue(playerName, cost);

                String itemName = cookItemMethod.getItemName();
                CustomMaterialItem materialType = cookItemMethod.getMaterialType();

                if(pullItemPlayer(player, exchangeItem, amount)) {
                    sendItem2Player(player, materialType, amount);
                    messageOnPlayerExchangeSuccess(player, itemName, amount);
                } else {
                    messagePlayerShouldMerge(player, exchangeItem.name(), amountExchange);
                }
            } else {
                messageOnPlayerExchangeNoItem(player, exchangeItem.name(), amountExchange);
            }
        } else {
            messageOnPlayerExchangeNoCost(player);
        }
    }

    private Boolean pullItemPlayer(Player player, Material material, int amount) {
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

    private void sendItem2Player(Player player, CustomMaterialItem materialType, int amount) {

        ItemStack itemStack = materialType.generateItemStack(amount);
        player.getInventory().addItem(itemStack);
    }

    private void messageOnPlayerExchangeSuccess(Player player, String itemName, int amount) {
        player.sendMessage(ChatColor.AQUA + "exchange " + ChatColor.WHITE + amount + ChatColor.AQUA + " " + itemName);
    }

    private void messageOnPlayerExchangeNoCost(Player player) {
        player.sendMessage(ChatColor.YELLOW + "can't cook this, check you beans!!");
    }

    private void messagePlayerShouldMerge(Player player, String itemName, int amount) {
        player.sendMessage(ChatColor.YELLOW + "can't cook this, merge you item to " + ChatColor.WHITE + amount + " " + itemName);
    }

    private void messageOnPlayerExchangeNoItem(Player player, String itemExchangeName, int amount) {
        player.sendMessage(ChatColor.YELLOW + "can't cook this, it require " + ChatColor.WHITE + amount + " " + itemExchangeName);
    }

    private Boolean hasItem2Exchange(Player player, Material exchangeItem, int amount) {
        PlayerInventory inventory = player.getInventory();
        ItemStack requireItem = new ItemStack(exchangeItem);

        return inventory.containsAtLeast(requireItem, amount);
    }
}
