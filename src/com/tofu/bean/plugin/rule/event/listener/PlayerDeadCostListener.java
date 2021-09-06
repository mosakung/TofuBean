package com.tofu.bean.plugin.rule.event.listener;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class PlayerDeadCostListener {

    private final PlayerBeansInteractor playerBeansInteractor;
    private final Random rand = new Random();

    public PlayerDeadCostListener(PlayerBeansInteractor playerBeansInteractor) {
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void call(PlayerDeathEvent event) {
        Player player = event.getEntity();
        String name = player.getName();

        int percentLost = rand.nextInt(10);

        Double pocketValue =  playerBeansInteractor.getValue(name);

        if (pocketValue == null) {
            player.sendMessage(ChatColor.DARK_RED + "Something Error (PlayerDeadCostEvent) tell BearSouL : pocketValue == null");
            return;
        }

        Double preLostPocket = (pocketValue / 100 * percentLost);

        playerBeansInteractor.decreasedValue(name, preLostPocket);

        player.sendMessage(ChatColor.DARK_RED + "[Oops you dead] Lost " + ChatColor.GOLD + preLostPocket + ChatColor.DARK_RED + " beans");
    }
}
