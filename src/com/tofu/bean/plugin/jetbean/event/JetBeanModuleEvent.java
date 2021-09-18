package com.tofu.bean.plugin.jetbean.event;

import com.tofu.bean.domain.contract.jetbean.DeadJetBeanInteractor;
import com.tofu.bean.plugin.rule.event.listener.DispenseBucketLavaCauldronListener;
import com.tofu.bean.plugin.jetbean.event.listener.PlayerStoreDeadLocationListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class JetBeanModuleEvent implements Listener {

    private final PlayerStoreDeadLocationListener playerStoreDeadLocationListener;


    public JetBeanModuleEvent(DeadJetBeanInteractor deadJetBeanInteractor) {
        this.playerStoreDeadLocationListener = new PlayerStoreDeadLocationListener(deadJetBeanInteractor);

    }

    @EventHandler
    public void providePlayerStoreDeadLocationListener(PlayerDeathEvent event) {
        playerStoreDeadLocationListener.call(event);
    }
}
