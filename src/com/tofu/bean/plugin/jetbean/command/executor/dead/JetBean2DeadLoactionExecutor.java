package com.tofu.bean.plugin.jetbean.command.executor.dead;

import com.tofu.bean.domain.contract.jetbean.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.data.enums.CostRateBeansMethod.RATE_TELEPORT_TO_DEAD_LOCATION;

public class JetBean2DeadLoactionExecutor extends DefaultBeanDeadLocationExecutor {

    private final DeadJetBeanInteractor deadJetBeanInteractor;

    public JetBean2DeadLoactionExecutor(
            JetBeanAction jetBeanAction,
            PlayerBeansInteractor playerBeansInteractor,
            DeadJetBeanInteractor deadJetBeanInteractor
    ) {

        super(jetBeanAction, playerBeansInteractor, deadJetBeanInteractor);
        this.deadJetBeanInteractor =deadJetBeanInteractor ;
    }

    public void executor(Player player) {
        Location deadLocation = getDeadLocation(player);

        if(deadLocation == null) {
            return;
        }

        if(jetBean(player, deadLocation, RATE_TELEPORT_TO_DEAD_LOCATION.getRate())) {
            deadJetBeanInteractor.useDeadLocation(player.getName());
        }
    }
}
