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

    @Override
    public void forceTeleportPlayerCalDistance(Player player, Player targetPlayer) {
        Location pl = player.getLocation();
        Location plt = targetPlayer.getLocation();

        Double diffP2X = pow(pl.getBlockX() - plt.getBlockX(), 2);
        Double diffP2Y = pow(pl.getBlockY() - plt.getBlockY(), 2);
        Double diffP2Z = pow(pl.getBlockZ() - plt.getBlockZ(), 2);

        Double diffDistance = sqrt(diffP2X + diffP2Y + diffP2Z);

        player.sendMessage(ChatColor.AQUA + "diff distance are " + diffDistance + " block");

        player.teleport(plt);
    }
}
