package com.tofu.bean.plugin.jetbean.executor.original;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.contract.JetBeanUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.data.RateBeanVillage.RATE_TELEPORT_TO_PLAYER;

public class JetBean2Player extends JetBeanUtils {

    public JetBean2Player(JetBeanAction jetBeanAction, PlayerBeansInteractor playerBeansInteractor) {

        super(jetBeanAction, playerBeansInteractor);
    }

    public void executor(Player player, Player target) {

        Location targetLocation = target.getLocation();

        jetBean(player, targetLocation, RATE_TELEPORT_TO_PLAYER.getRate());
    }
}
