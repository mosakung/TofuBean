package com.tofu.bean.plugin.sign.event;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.sign.event.listener.CreateActionSignListener;
import com.tofu.bean.plugin.sign.event.listener.PlayerUseActionSignListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignModuleEvent implements Listener {

    private final CreateActionSignListener createActionSignListener;
    private final PlayerUseActionSignListener playerUseActionSignListener;


    public SignModuleEvent(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.createActionSignListener = new CreateActionSignListener();
        this.playerUseActionSignListener = new PlayerUseActionSignListener(playerBeansInteractor);
    }

    @EventHandler
    public void provideCreateActionSignListener(SignChangeEvent event) {
        createActionSignListener.call(event);
    }

    @EventHandler
    public void providePlayerUseActionSignListener(PlayerInteractEvent event) {
        playerUseActionSignListener.call(event);
    }
}
