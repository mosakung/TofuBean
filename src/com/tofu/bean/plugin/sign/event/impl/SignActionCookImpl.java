package com.tofu.bean.plugin.sign.event.impl;

import com.tofu.bean.data.enums.sign.branch.CookSignBranch;
import com.tofu.bean.data.enums.item.CustomMaterialItem;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.sign.event.contract.SignActionCook;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SignActionCookImpl extends SignActionDefault implements SignActionCook {

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
                if(pullItemPlayer(player, exchangeItem, amount)) {
                    String itemName = cookItemMethod.getItemName();
                    CustomMaterialItem materialType = cookItemMethod.getMaterialType();

                    playerBeansInteractor.decreasedValue(playerName, cost);

                    sendItem2Player(player, materialType, amount);
                    player.sendMessage(messageOnPlayerExchangeSuccess(itemName, amount));
                } else {
                    player.sendMessage(messagePlayerShouldMerge(exchangeItem.name(), amountExchange));
                }
            } else {
                player.sendMessage(messageOnPlayerExchangeNoItem(exchangeItem.name(), amountExchange));
            }
        } else {
            player.sendMessage(messageOnPlayerExchangeNoCost);
        }
    }

    private String messageOnPlayerExchangeSuccess(String itemName, int amount) {
        return ChatColor.AQUA + "exchange " + ChatColor.WHITE + amount + ChatColor.AQUA + " " + itemName;
    }

    private String messageOnPlayerExchangeNoCost = ChatColor.YELLOW + "can't cook this, check you beans!!";

    private String messagePlayerShouldMerge(String itemName, int amount) {
        return ChatColor.YELLOW + "can't cook this, merge you item to " + ChatColor.WHITE + amount + " " + itemName;
    }

    private String messageOnPlayerExchangeNoItem(String itemExchangeName, int amount) {
        return ChatColor.YELLOW + "can't cook this, it require " + ChatColor.WHITE + amount + " " + itemExchangeName;
    }
}
