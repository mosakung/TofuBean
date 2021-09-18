package com.tofu.bean.plugin.rule.event;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.rule.event.listener.DispenseBucketLavaCauldronListener;
import com.tofu.bean.plugin.rule.event.listener.PlayerDeadCostListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerRuleModuleEvent implements Listener {

    private final PlayerDeadCostListener playerDeadCostListener;
    private final DispenseBucketLavaCauldronListener dispenseBucketLavaCauldronListener;

    public PlayerRuleModuleEvent(PlayerBeansInteractor playerBeansInteractor) {
        this.playerDeadCostListener = new PlayerDeadCostListener(playerBeansInteractor);
        this.dispenseBucketLavaCauldronListener = new DispenseBucketLavaCauldronListener();
    }

    @EventHandler
    public void providePlayerDeadCostListener(PlayerDeathEvent event) {
        playerDeadCostListener.call(event);
    }

    @EventHandler
    public void provideDispenseBucketLavaCauldronListener(BlockDispenseEvent event) {
        dispenseBucketLavaCauldronListener.call(event);
    }

}
