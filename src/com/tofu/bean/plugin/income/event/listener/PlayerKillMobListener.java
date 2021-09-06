package com.tofu.bean.plugin.income.event.listener;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

import static com.tofu.bean.utils.mapper.EntityMapper.mapMobMonster2Value;

public class PlayerKillMobListener {

    private PlayerBeansInteractor playerBeansInteractor;

    public PlayerKillMobListener(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void call(EntityDeathEvent e) {
        Entity mob = e.getEntity();
        Player killer = e.getEntity().getKiller();

        if (killer != null) {
            Double value = mapMobMonster2Value(mob.getType());

            if (value != 0) {
                playerBeansInteractor.increasedValue(killer.getName(), value);
                killer.sendMessage(ChatColor.AQUA + "+ " + ChatColor.GOLD + value.toString() + ChatColor.AQUA + " Beans");
            }
        }
    }
}
