package com.tofu.bean.plugin.sign.event.impl;

import com.tofu.bean.data.enums.item.CustomMaterialItem;
import com.tofu.bean.data.enums.sign.branch.ExchangeSignBranch;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.sign.event.contract.SignActionExchange;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SignActionExchangeImpl extends SignActionDefault implements SignActionExchange  {

    private final PlayerBeansInteractor playerBeansInteractor;

    public SignActionExchangeImpl(PlayerBeansInteractor playerBeansInteractor) {
        this.playerBeansInteractor = playerBeansInteractor;
    }

    @Override
    public void action(Player player, ExchangeSignBranch exchangeSignBranch) {

        String playerName = player.getName();
        Double pocketValue = playerBeansInteractor.getValue(playerName);

        if (pocketValue == null) {
            player.sendMessage(ChatColor.DARK_RED + "Something Error (SignActionExchangeImpl (action)) tell BearSouL : pocketValue == null");
            return;
        }

        Double cost = exchangeSignBranch.getBeans();

        if(pocketValue >= cost) {
            Material material = exchangeSignBranch.getRequire();
            int amountRequire = exchangeSignBranch.getAmountRequire();

            if(hasItem2Exchange(player, material, amountRequire)) {

                if(pullItemPlayer(player, material, amountRequire)) {
                    CustomMaterialItem customMaterialItem = exchangeSignBranch.getReward();
                    int amountReward = exchangeSignBranch.getAmountReward();

                    playerBeansInteractor.decreasedValue(playerName, cost);
                    sendItem2Player(player, customMaterialItem, amountReward);

                    player.sendMessage(messageOnPlayerExchangeSuccess(customMaterialItem.getItemName(), amountReward));
                } else {
                    player.sendMessage(messagePlayerShouldMerge(exchangeSignBranch.getRequireLabel(), amountRequire));
                }
            } else {
                player.sendMessage(messageOnPlayerExchangeNoItem(exchangeSignBranch.getRequireLabel(), amountRequire));
            }
        } else {
            player.sendMessage(messageOnNoBeans);
        }
    }

    private final String messageOnNoBeans = ChatColor.YELLOW + "can't exchange this, check you beans!!";

    private String messageOnPlayerExchangeSuccess(String labelName, int amount) {
        return ChatColor.AQUA + "exchange " + ChatColor.WHITE + amount + ChatColor.AQUA + " " + labelName;
    }

    private String messagePlayerShouldMerge(String labelName, int amount) {
        return ChatColor.YELLOW + "can't exchange this, merge you item to " + ChatColor.WHITE + amount + " " + labelName;
    }

    private String messageOnPlayerExchangeNoItem(String labelName, int amount) {
        return ChatColor.YELLOW + "can't exchange this, it require " + ChatColor.WHITE + amount + " " + labelName;
    }
}
