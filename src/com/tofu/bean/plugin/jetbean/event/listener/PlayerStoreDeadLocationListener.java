package com.tofu.bean.plugin.jetbean.event.listener;

import com.tofu.bean.domain.contract.jetbean.DeadJetBeanInteractor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import static com.tofu.bean.utils.parser.LocationParser.location2String;

public record PlayerStoreDeadLocationListener(DeadJetBeanInteractor deadJetBeanInteractor) {

    public void call(PlayerDeathEvent event) {

        Player player = (Player) event.getEntity();
        String playerName = player.getName();

        Location deadLocation = player.getLocation();

        String rawDeadLocation = location2String(deadLocation);

        deadJetBeanInteractor.setDeadLoaction(playerName, rawDeadLocation);

        player.sendMessage(ChatColor.DARK_RED + "[Dead Location]"
                + ChatColor.AQUA + " X : " + ChatColor.WHITE + deadLocation.getBlockX()
                + ChatColor.AQUA + " Y : " + ChatColor.WHITE + deadLocation.getBlockY()
                + ChatColor.AQUA + " Z : " + ChatColor.WHITE + deadLocation.getBlockZ());

        player.sendMessage(ChatColor.AQUA + "you can come back dead location " + ChatColor.WHITE + "/jetbean dead <cost | --yes>");
    }
}
