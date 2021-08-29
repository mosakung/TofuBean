package com.tofu.bean.plugin.event;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.event.fram.HarvestCropEvent;
import com.tofu.bean.plugin.event.mob.OnMobDeadEvent;
import com.tofu.bean.plugin.event.mob.OnPlayerDeadEvent;
import com.tofu.bean.plugin.event.ore.OnBreakOreEvent;
import com.tofu.bean.plugin.event.permission.OnDeveloperLoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class TaskModuleEvent implements Listener {
    private final OnMobDeadEvent onMobDeadEvent;
    private final OnPlayerDeadEvent onPlayerDeadEvent;
    private final OnBreakOreEvent onBreakOreEvent;
    private final HarvestCropEvent harvestCropEvent;
    private final OnDeveloperLoginEvent onDeveloperLoginEvent;

    public TaskModuleEvent(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.onMobDeadEvent = new OnMobDeadEvent(playerBeansInteractor);
        this.onPlayerDeadEvent =  new OnPlayerDeadEvent(playerBeansInteractor);
        this.onBreakOreEvent = new OnBreakOreEvent(playerBeansInteractor);
        this.harvestCropEvent = new HarvestCropEvent(playerBeansInteractor);
        this.onDeveloperLoginEvent = new OnDeveloperLoginEvent();
    }

    @EventHandler
    public void onPlayerKillModGetBean(EntityDeathEvent event) {
        onMobDeadEvent.call(event);
    }

    @EventHandler
    public void OnPlayerDeadLostBean(PlayerDeathEvent event) {
        onPlayerDeadEvent.call(event);
    }

    @EventHandler
    public void onOreBreakGetBean(BlockBreakEvent event){
        onBreakOreEvent.call(event);
    }

    @EventHandler
    public void harvestCropAutomate(BlockBreakEvent event) { harvestCropEvent.call(event); }

    @EventHandler
    public void onDeveloperLogin(PlayerJoinEvent event) { onDeveloperLoginEvent.call(event); }
}
