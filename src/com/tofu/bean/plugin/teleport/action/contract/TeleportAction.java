package com.tofu.bean.plugin.teleport.action.contract;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface TeleportAction {
    void forceTeleport(Player player, Location location);

    void forceTeleportPlayer(Player player, Player targetPlayer);
    void accessTeleportPlayer(Player player, Player targetPlayer);

    void forceTeleportPlayerCalDistance(Player player, Player targetPlayer);
}
