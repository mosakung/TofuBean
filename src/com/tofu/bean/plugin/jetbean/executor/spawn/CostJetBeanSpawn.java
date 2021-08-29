package com.tofu.bean.plugin.jetbean.executor.spawn;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.contract.JetBeanUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.data.RateBeanVillage.RATE_TELEPORT_TO_PLAYER;

public class CostJetBeanSpawn extends JetBeanUtils {

    public CostJetBeanSpawn(
            JetBeanAction jetBeanAction,
            PlayerBeansInteractor playerBeansInteractor) {
        super(jetBeanAction, playerBeansInteractor);
    }

    public void executor(Player player, Location targetLocation) {

        Location playerLocation = player.getLocation();

        Double cost = jetCost(playerLocation, targetLocation, RATE_TELEPORT_TO_PLAYER.getRate());

        messageJetCost(player, cost);
    }
}
