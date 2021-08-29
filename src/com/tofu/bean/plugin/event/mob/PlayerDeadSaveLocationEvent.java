package com.tofu.bean.plugin.event.mob;

import com.tofu.bean.domain.contract.DeadJetBeanInteractor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import static com.tofu.bean.utils.parser.LocationParser.location2String;

public class PlayerDeadSaveLocationEvent {

    private final DeadJetBeanInteractor deadJetBeanInteractor;

    public PlayerDeadSaveLocationEvent(
            DeadJetBeanInteractor deadJetBeanInteractor
    ) {
        this.deadJetBeanInteractor = deadJetBeanInteractor;
    }

    public void call(PlayerDeathEvent event) {

        Player player = (Player) event.getEntity();
        String playerName = player.getName();

        Location deadLocation = player.getLocation();

        String rawDeadLocation = location2String(deadLocation);

        deadJetBeanInteractor.setDeadLoaction(playerName, rawDeadLocation);

        player.sendMessage(ChatColor.AQUA + "you can come back dead location /jetbean dead <cost | --yes>");
    }
}
