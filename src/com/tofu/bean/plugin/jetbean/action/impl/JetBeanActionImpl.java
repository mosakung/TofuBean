package com.tofu.bean.plugin.jetbean.action.impl;

import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class JetBeanActionImpl implements JetBeanAction {

    @Override
    public void forceJetBeanLocation(Player player, Location location) {
        player.teleport(location);
    }

    @Override
    public void forceJetBeanPlayer(Player player, Player targetPlayer) {
        player.teleport(targetPlayer.getLocation());
    }

    @Override
    public void accessJetBeanPlayer(Player player, Player targetPlayer) {
//        targetPlayer.sendMessage(ChatColor.AQUA + player.getName() + " would to teleport to you");
    }
}
