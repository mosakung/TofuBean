package com.tofu.bean.plugin.beans.executor;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public record GiveBean2FriendExecutor(PlayerBeansInteractor playerBeansInteractor) {

    public void executor(Player player, Player target, Double beansCost) {
        String pName = player.getName();
        String tName = target.getName();

        if(beansCost > playerBeansInteractor.getValue(pName)) {
            player.sendMessage(ChatColor.AQUA + "enough beans for give");
            return;
        }

        playerBeansInteractor.decreasedValue(pName, beansCost);
        player.sendMessage(ChatColor.AQUA + "you given to " + tName + " " +  ChatColor.GOLD + beansCost.toString() + ChatColor.AQUA + " beans");

        playerBeansInteractor.increasedValue(tName, beansCost);
        player.sendMessage(ChatColor.AQUA + "you got from " + pName + " " + ChatColor.GOLD + beansCost.toString() + ChatColor.AQUA + " beans");
    }
}
