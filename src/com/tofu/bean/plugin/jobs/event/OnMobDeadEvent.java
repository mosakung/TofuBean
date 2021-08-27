package com.tofu.bean.plugin.jobs.event;

import com.tofu.bean.plugin.money.domain.contract.PlayerMoneyInteractor;
import com.tofu.bean.utils.mapper.EntityMapper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnMobDead implements Listener {

    private final PlayerMoneyInteractor playerMoneyInteractor;
    private final EntityMapper mapper;

    public OnMobDead(PlayerMoneyInteractor playerMoneyInteractor) {
        this.mapper = new EntityMapper();
        this.playerMoneyInteractor = playerMoneyInteractor;
    }

    @EventHandler
    public void onPlayerKillMod(EntityDeathEvent e) {
        Entity mob = e.getEntity();
        Player killer = e.getEntity().getKiller();

        Double value = mapper.mapper2Value(mob.getType());

        playerMoneyInteractor.increasedValue(killer.getName(), value);
    }
}
