package com.tofu.bean.plugin.jetbean.action.impl;

import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class JetBeanActionImpl implements JetBeanAction {

    @Override
    public void forceJetBeanLocation(Player player, Location location) {
        player.teleport(location);
    }
}
