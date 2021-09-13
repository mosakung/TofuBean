package com.tofu.bean.plugin.permission.event.listener;

import com.tofu.bean.data.enums.PermissionMethod;
import com.tofu.bean.presentation.PresentationPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

public class InitializePermissionLoginListener {

    public void call(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String playerName = player.getName();

        if (playerName.equals("BearSouL")) {
            PermissionAttachment targetAttachment = player.addAttachment(PresentationPlugin.getInstance());
            targetAttachment.setPermission(PermissionMethod.DEVELOPER.getPermission(), true);
            player.sendMessage(ChatColor.AQUA + "initialize permission developer to " + player.getName());
        }
    }
}
