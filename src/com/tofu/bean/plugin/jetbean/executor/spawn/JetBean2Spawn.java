package com.tofu.bean.plugin.jetbean.executor.spawn;

import com.tofu.bean.data.RateBeanVillage;
import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.domain.contract.SpawnJetBeanInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.contract.JetBeanUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.utils.parser.LocationParser.string2Location;

public class JetBean2Spawn extends JetBeanUtils {

    private final SpawnJetBeanInteractor spawnJetBeanInteractor;

    public JetBean2Spawn(
            JetBeanAction jetBeanAction,
            PlayerBeansInteractor playerBeansInteractor,
            SpawnJetBeanInteractor spawnJetBeanInteractor
    ) {
        super(jetBeanAction, playerBeansInteractor);
        this.spawnJetBeanInteractor = spawnJetBeanInteractor;
    }

    public void execute(Player player, String spawnName) {

        String rawLocation = spawnJetBeanInteractor.getSpawnLocation(spawnName);

        if(rawLocation == null) {
            player.sendMessage(ChatColor.DARK_RED + "Something Error (JetBean2Spawn) tell BearSouL : rawLocation == null");
            return;
        }

        Location location = string2Location(rawLocation);

        jetBean(player, location, RateBeanVillage.RATE_TELEPORT_TO_SPAWN.getRate());
    }
}
