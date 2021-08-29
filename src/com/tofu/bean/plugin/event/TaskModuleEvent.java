package com.tofu.bean.plugin.event;

import com.tofu.bean.domain.contract.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.event.fram.PlayerHarvestCropRewardEvent;
import com.tofu.bean.plugin.event.mob.KillMobRewardEvent;
import com.tofu.bean.plugin.event.mob.PlayerDeadCostEvent;
import com.tofu.bean.plugin.event.mob.PlayerDeadSaveLocationEvent;
import com.tofu.bean.plugin.event.ore.PlayerPickOreRewardEvent;
import com.tofu.bean.plugin.event.permission.PlayerDeveloperLoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class TaskModuleEvent implements Listener {
    private final KillMobRewardEvent killMobRewardEvent;
    private final PlayerDeadCostEvent playerDeadCostEvent;
    private final PlayerPickOreRewardEvent playerPickOreRewardEvent;
    private final PlayerHarvestCropRewardEvent playerHarvestCropRewardEvent;
    private final PlayerDeveloperLoginEvent playerDeveloperLoginEvent;
    private final PlayerDeadSaveLocationEvent playerDeadSaveLocationEvent;

    public TaskModuleEvent(
            PlayerBeansInteractor playerBeansInteractor,
            DeadJetBeanInteractor deadJetBeanInteractor
    ) {
        this.killMobRewardEvent = new KillMobRewardEvent(playerBeansInteractor);
        this.playerDeadCostEvent =  new PlayerDeadCostEvent(playerBeansInteractor);
        this.playerPickOreRewardEvent = new PlayerPickOreRewardEvent(playerBeansInteractor);
        this.playerHarvestCropRewardEvent = new PlayerHarvestCropRewardEvent(playerBeansInteractor);
        this.playerDeveloperLoginEvent = new PlayerDeveloperLoginEvent();
        this.playerDeadSaveLocationEvent = new PlayerDeadSaveLocationEvent(deadJetBeanInteractor);
    }

    @EventHandler
    public void provideKillMobRewardEvent(EntityDeathEvent event) {
        killMobRewardEvent.call(event);
    }

    @EventHandler
    public void providePlayerDeadCostEvent(PlayerDeathEvent event) {
        playerDeadCostEvent.call(event);
    }

    @EventHandler
    public void providePlayerPickOreRewardEvent(BlockBreakEvent event){
        playerPickOreRewardEvent.call(event);
    }

    @EventHandler
    public void providePlayerHarvestCropRewardEvent(BlockBreakEvent event) { playerHarvestCropRewardEvent.call(event); }

    @EventHandler
    public void providePlayerDeveloperLoginEvent(PlayerJoinEvent event) { playerDeveloperLoginEvent.call(event); }

    @EventHandler
    public void providePlayerDeadSaveLocationEvent(PlayerDeathEvent event) { playerDeadSaveLocationEvent.call(event); }
}
