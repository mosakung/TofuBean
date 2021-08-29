package com.tofu.bean.plugin.jetbean.executor.spawn;

import com.tofu.bean.data.RateBeanVillage;
import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.contract.JetBeanUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class JetBean2Spawn extends JetBeanUtils {

    public JetBean2Spawn(JetBeanAction jetBeanAction, PlayerBeansInteractor playerBeansInteractor) {
        super(jetBeanAction, playerBeansInteractor);
    }

    public void execute(Player player, Location location) {

        jetBean(player, location, RateBeanVillage.RATE_TELEPORT_TO_SPAWN.getRate());
    }
}
