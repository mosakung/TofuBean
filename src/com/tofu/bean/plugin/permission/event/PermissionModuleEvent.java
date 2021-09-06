package com.tofu.bean.plugin.permission.event;

import com.tofu.bean.plugin.permission.event.listener.InitializePermissionLoginListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PermissionModuleEvent implements Listener {

    private final InitializePermissionLoginListener initializePermissionLoginListener;

    public PermissionModuleEvent() {
        this.initializePermissionLoginListener = new InitializePermissionLoginListener();
    }

    @EventHandler
    public void provideInitializePermissionLoginListener(PlayerJoinEvent event) {
        initializePermissionLoginListener.call(event);
    }

}
