package com.tofu.bean.plugin.beans.executor;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public record ShowBeansValueExecutor(PlayerBeansInteractor playerBeansInteractor) {

    public void executor(Player player) {
        Double money = playerBeansInteractor.getValue(player.getName());

        player.sendMessage(ChatColor.AQUA + "beans : " + ChatColor.GOLD + money.toString());
    }
}
