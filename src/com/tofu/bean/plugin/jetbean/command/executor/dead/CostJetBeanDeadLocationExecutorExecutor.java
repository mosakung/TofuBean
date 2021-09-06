package com.tofu.bean.plugin.jetbean.command.executor.dead;

import com.tofu.bean.domain.contract.jetbean.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.command.action.contract.JetBeanAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.data.methods.CostRateBeansMethod.RATE_TELEPORT_TO_DEAD_LOCATION;

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
