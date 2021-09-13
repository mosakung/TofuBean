package com.tofu.bean.plugin.jetbean.command.executor.spawn;

import com.tofu.bean.data.enums.CostRateBeansMethod;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.domain.contract.jetbean.SpawnJetBeanInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.command.executor.JetBeanDefault;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.utils.parser.LocationParser.string2Location;

public class JetBean2SpawnExecutor extends JetBeanDefault {

    private final SpawnJetBeanInteractor spawnJetBeanInteractor;

    public JetBean2SpawnExecutor(
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

        jetBean(player, location, CostRateBeansMethod.RATE_TELEPORT_TO_SPAWN.getRate());
    }
}
