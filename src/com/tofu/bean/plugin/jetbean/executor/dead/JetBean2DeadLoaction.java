package com.tofu.bean.plugin.jetbean.executor.dead;

import com.tofu.bean.domain.contract.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.data.RateBeanVillage.RATE_TELEPORT_TO_DEAD_LOCATION;

public class JetBean2DeadLoaction extends DefaultBeanDeadLocation  {

    private final DeadJetBeanInteractor deadJetBeanInteractor;

    public JetBean2DeadLoaction(
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

        jetBean(player, deadLocation, RATE_TELEPORT_TO_DEAD_LOCATION.getRate());

        deadJetBeanInteractor.useDeadLocation(player.getName());
    }
}
