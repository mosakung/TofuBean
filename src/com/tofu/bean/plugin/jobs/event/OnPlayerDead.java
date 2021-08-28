package com.tofu.bean.plugin.jobs.event;

import com.tofu.bean.data.PermissionBean;
import com.tofu.bean.plugin.beans.domain.contract.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class OnPlayerDead implements Listener {

    private final PlayerBeansInteractor playerBeansInteractor;
    private final Random rand = new Random();

    public OnPlayerDead(PlayerBeansInteractor playerBeansInteractor) {
        this.playerBeansInteractor = playerBeansInteractor;
    }

    @EventHandler
    public void OnPlayerDead(PlayerDeathEvent event) {
        Player player = event.getEntity();
        String name = player.getName();

        int percentLost = rand.nextInt(10);

        Double pocket =  playerBeansInteractor.getValue(name);

        Double preLostPocket = (pocket / 100 * percentLost);

        playerBeansInteractor.decreasedValue(name, preLostPocket);

        player.sendMessage(ChatColor.DARK_RED + "Oops you die");
        player.sendMessage(ChatColor.DARK_RED + "Lost " + ChatColor.GOLD + preLostPocket + ChatColor.DARK_RED + " beans");
    }
}
