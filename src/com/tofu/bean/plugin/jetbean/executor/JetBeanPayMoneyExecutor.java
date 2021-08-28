package com.tofu.bean.plugin.jetbean.executor;

import com.tofu.bean.plugin.beans.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import com.tofu.bean.utils.UtilsBean;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class JetBeanPayMoneyExecutor extends UtilsBean {

    private final JetBeanAction jetBeanAction;
    private final PlayerBeansInteractor playerBeansInteractor;

    public JetBeanPayMoneyExecutor(JetBeanAction jetBeanAction, PlayerBeansInteractor playerBeansInteractor) {
        this.jetBeanAction = jetBeanAction;
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void executor(Player player, Player target) {
        String name = player.getName();

        Location pl = player.getLocation();
        Location plt = target.getLocation();

        Double cost = doublerFormatP2(calDistanceXYZ(pl, plt) * 2.5);

        Double pocketValue = playerBeansInteractor.getValue(name);

        if (pocketValue >= cost) {
            playerBeansInteractor.decreasedValue(name, cost);

            player.sendMessage(ChatColor.GOLD + "You are premium customer :)");

            jetBeanAction.forceJetBeanPlayer(player, target);
        } else {
            player.sendMessage(ChatColor.DARK_RED + "So poor check you money first :(");
        }
    }
}
