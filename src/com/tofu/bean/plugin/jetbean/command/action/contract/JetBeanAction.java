package com.tofu.bean.plugin.jetbean.command.action.contract;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface JetBeanAction {

    void forceJetBeanLocation(Player player, Location location);
}
