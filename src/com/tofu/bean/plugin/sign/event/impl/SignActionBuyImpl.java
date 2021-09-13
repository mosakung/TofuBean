package com.tofu.bean.plugin.sign.event.impl;

import com.tofu.bean.data.enums.sign.branch.BuySignBranch;
import com.tofu.bean.data.enums.item.CustomMaterialItem;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.sign.event.contract.SignActionBuy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public record SignActionBuyImpl(PlayerBeansInteractor playerBeansInteractor) implements SignActionBuy {

    public void action(Player player, BuySignBranch buySignBranch) {

        String playerName = player.getName();
        Double pocketValue = playerBeansInteractor.getValue(playerName);

        if (pocketValue == null) {
            player.sendMessage(ChatColor.DARK_RED + "Something Error (PlayerUseSignShop (purchaseItem)) tell BearSouL : pocketValue == null");
            return;
        }

        Double cost = buySignBranch.getBeansValue();
        int amount = buySignBranch.getAmountValue();

        if (cost == null || amount == -1) {
            return;
        }

        if (pocketValue >= cost) {
            playerBeansInteractor.decreasedValue(playerName, cost);

            String itemName = buySignBranch.getItemName();
            messageOnPlayerBuySuccess(player, itemName, amount);

            CustomMaterialItem materialType = buySignBranch.getMaterialType();
            sendItem2Player(player, materialType, amount);
        } else {
            messageOnPlayerBuyFail(player);
        }
    }

    private void sendItem2Player(Player player, CustomMaterialItem materialType, int amount) {

        ItemStack itemStack = materialType.generateItemStack(amount);
        player.getInventory().addItem(itemStack);
    }


    private void messageOnPlayerBuySuccess(Player player, String itemName, int amount) {
        player.sendMessage(ChatColor.AQUA + "buy " + ChatColor.WHITE + amount + ChatColor.AQUA + " " + itemName);
    }

    private void messageOnPlayerBuyFail(Player player) {
        player.sendMessage(ChatColor.YELLOW + "can't buy this, check you beans!!");
    }
}
