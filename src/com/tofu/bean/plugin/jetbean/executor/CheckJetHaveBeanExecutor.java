package com.tofu.bean.plugin.teleport.executor;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public record CheckJetHaveBeanExecutor() {
    public void executor(Player player, Player target) {
        Location pl = player.getLocation();
        Location plt = target.getLocation();

        Double diffP2X = pow(pl.getBlockX() - plt.getBlockX(), 2);
        Double diffP2Y = pow(pl.getBlockY() - plt.getBlockY(), 2);
        Double diffP2Z = pow(pl.getBlockZ() - plt.getBlockZ(), 2);

        Double diffDistance = sqrt(diffP2X + diffP2Y + diffP2Z);

        Double cost = diffDistance * 2.5;

        player.sendMessage(ChatColor.AQUA + "jet cost " + ChatColor.GOLD + cost.toString() + ChatColor.AQUA + " beans" );
    }
}
