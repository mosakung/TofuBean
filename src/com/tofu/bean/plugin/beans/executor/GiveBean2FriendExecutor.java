package com.tofu.bean.plugin.beans.executor;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public record GiveBean2FriendExecutor(PlayerBeansInteractor playerBeansInteractor) {

    public void executor(Player player, Player target, Double beansCost) {
        String playerName = player.getName();
        String targetName = target.getName();

        Double pocketValue = playerBeansInteractor.getValue(playerName);

        if (pocketValue == null) {
            player.sendMessage(ChatColor.DARK_RED + "Something Error (GiveBean2FriendExecutor) tell BearSouL : pocketValue == null");
            return;
        }

        if(beansCost > pocketValue) {
            player.sendMessage(ChatColor.AQUA + "enough beans for give");
            return;
        }

        playerBeansInteractor.decreasedValue(playerName, beansCost);
        player.sendMessage(ChatColor.AQUA + "you given to " + targetName + " " +  ChatColor.GOLD + beansCost.toString() + ChatColor.AQUA + " beans");

        playerBeansInteractor.increasedValue(targetName, beansCost);
        player.sendMessage(ChatColor.AQUA + "you got from " + playerName + " " + ChatColor.GOLD + beansCost.toString() + ChatColor.AQUA + " beans");
    }
}
