package com.tofu.bean.plugin.event.permission;

import com.tofu.bean.data.PermissionBean;
import com.tofu.bean.presentation.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

public class PlayerDeveloperLoginEvent {

    public void call(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String playerName = player.getName();

        if (playerName.equals("BearSouL")) {
            PermissionAttachment targetAttachment = player.addAttachment(Main.getInstance());
            targetAttachment.setPermission(PermissionBean.DEVELOPER.getPermission(), true);
            player.sendMessage(ChatColor.AQUA + "initialize permission developer to " + player.getName());
        }
    }
}
