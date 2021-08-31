package com.tofu.bean.plugin.jetbean.executor.dead;

import com.tofu.bean.domain.contract.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.data.CostRateBeansMethod.RATE_TELEPORT_TO_DEAD_LOCATION;

public class CostJetBeanDeadLocationExecutorExecutor extends DefaultBeanDeadLocationExecutor {

    public CostJetBeanDeadLocationExecutorExecutor(
            JetBeanAction jetBeanAction,
            PlayerBeansInteractor playerBeansInteractor,
            DeadJetBeanInteractor deadJetBeanInteractor
    ) {
        super(jetBeanAction, playerBeansInteractor, deadJetBeanInteractor);
    }

    public void executor(Player player) {

        Location playerLocation = player.getLocation();
        Location deadLocation = getDeadLocation(player);

        if(deadLocation == null) {
            return;
        }

        Double cost = jetCost(playerLocation, deadLocation, RATE_TELEPORT_TO_DEAD_LOCATION.getRate());

        messageJetCost(player, cost);
    }
}
