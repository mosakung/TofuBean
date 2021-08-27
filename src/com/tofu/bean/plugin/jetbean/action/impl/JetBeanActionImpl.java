package com.tofu.bean.plugin.teleport.action.impl;

import com.tofu.bean.plugin.teleport.action.contract.TeleportAction;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class TeleportActionImpl implements TeleportAction {

    @Override
    public void forceTeleport(Player player, Location location) {
        player.teleport(location);
    }

    @Override
    public void forceTeleportPlayer(Player player, Player targetPlayer) {
        player.teleport(targetPlayer.getLocation());
    }

    @Override
    public void accessTeleportPlayer(Player player, Player targetPlayer) {
//        targetPlayer.sendMessage(ChatColor.AQUA + player.getName() + " would to teleport to you");
    }
}
