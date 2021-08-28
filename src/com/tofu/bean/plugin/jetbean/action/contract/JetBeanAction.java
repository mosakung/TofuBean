package com.tofu.bean.plugin.jetbean.action.contract;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface JetBeanAction {
    void forceJetBeanLocation(Player player, Location location);

    void forceJetBeanPlayer(Player player, Player targetPlayer);
    void accessJetBeanPlayer(Player player, Player targetPlayer);
}
