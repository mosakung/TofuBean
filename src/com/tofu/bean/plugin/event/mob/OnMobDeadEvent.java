package com.tofu.bean.plugin.jobs.event;

import com.tofu.bean.plugin.beans.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.utils.mapper.EntityMapper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnMobDeadEvent implements Listener {

    private PlayerBeansInteractor playerBeansInteractor;
    private EntityMapper mapper;

    public OnMobDeadEvent(PlayerBeansInteractor playerBeansInteractor) {
        this.mapper = new EntityMapper();
        this.playerBeansInteractor = playerBeansInteractor;
    }

    @EventHandler
    public void onPlayerKillMod(EntityDeathEvent e) {
        Entity mob = e.getEntity();
        Player killer = e.getEntity().getKiller();

        if(killer != null) {
            Double value = mapper.mapper2Value(mob.getType());

            if(value != 0) {
                playerBeansInteractor.increasedValue(killer.getName(), value);
                killer.sendMessage(ChatColor.AQUA + "+ " + ChatColor.GOLD + value.toString() + ChatColor.AQUA + " Beans");
            }
        }
    }
}
