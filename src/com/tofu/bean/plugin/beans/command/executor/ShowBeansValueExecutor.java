package com.tofu.bean.plugin.beans.command.executor;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public record ShowBeansValueExecutor(PlayerBeansInteractor playerBeansInteractor) {

    public void executor(Player player) {
        Double pocketValue = playerBeansInteractor.getValue(player.getName());

        if (pocketValue == null) {
            player.sendMessage(ChatColor.DARK_RED + "Something Error (ShowBeansValueExecutor) tell BearSouL : pocketValue == null");
            return;
        }

        player.sendMessage(ChatColor.AQUA + "beans : " + ChatColor.GOLD + pocketValue.toString());
    }
}
