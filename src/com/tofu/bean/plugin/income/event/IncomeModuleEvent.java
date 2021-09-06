package com.tofu.bean.plugin.income.event;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.income.event.listener.PlayerFishingListener;
import com.tofu.bean.plugin.income.event.listener.PlayerHarvestListener;
import com.tofu.bean.plugin.income.event.listener.PlayerKillMobListener;
import com.tofu.bean.plugin.income.event.listener.PlayerPickOreListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;

public class IncomeModuleEvent implements Listener {

    private final PlayerKillMobListener playerKillMobListener;
    private final PlayerPickOreListener playerPickOreListener;
    private final PlayerHarvestListener playerHarvestListener;
    private final PlayerFishingListener playerFishingListener;

    public IncomeModuleEvent(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.playerKillMobListener = new PlayerKillMobListener(playerBeansInteractor);
        this.playerPickOreListener = new PlayerPickOreListener(playerBeansInteractor);
        this.playerHarvestListener = new PlayerHarvestListener(playerBeansInteractor);
        this.playerFishingListener = new PlayerFishingListener(playerBeansInteractor);
    }

    @EventHandler
    public void providePlayerKillMobListener(EntityDeathEvent event) {
        playerKillMobListener.call(event);
    }

    @EventHandler
    public void providePlayerPickOreListener(BlockBreakEvent event) {
        playerPickOreListener.call(event);
    }

    @EventHandler
    public void providePlayerHarvestListener(BlockBreakEvent event) {
        playerHarvestListener.call(event);
    }

    @EventHandler
    public void providePlayerFishingListener(PlayerFishEvent event) { playerFishingListener.call(event); }
}
