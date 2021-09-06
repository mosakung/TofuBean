package com.tofu.bean.plugin.jetbean.command.executor.dead;

import com.tofu.bean.domain.contract.jetbean.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.command.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.command.executor.JetBeanDefault;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.utils.parser.LocationParser.string2Location;

public class DefaultBeanDeadLocationExecutor extends JetBeanDefault {

    private final DeadJetBeanInteractor deadJetBeanInteractor;

    public DefaultBeanDeadLocationExecutor(
            JetBeanAction jetBeanAction,
            PlayerBeansInteractor playerBeansInteractor,
            DeadJetBeanInteractor deadJetBeanInteractor
    ) {

        super(jetBeanAction, playerBeansInteractor);
        this.deadJetBeanInteractor = deadJetBeanInteractor;
    }

    public Location getDeadLocation(Player player) {
        String playerName = player.getName();

        Boolean _hasDeadLocation = deadJetBeanInteractor.hasDeadLocation(playerName);

        if (!_hasDeadLocation) {
            player.sendMessage(ChatColor.YELLOW + "you already jet bean to dead");
            return null;
        }

        String rawDeadLocation = deadJetBeanInteractor.getDeadLocation(playerName);

        Location deadLocation = string2Location(rawDeadLocation);

        return deadLocation;
    }
}
