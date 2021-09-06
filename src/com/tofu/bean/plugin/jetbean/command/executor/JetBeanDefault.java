package com.tofu.bean.plugin.jetbean.command.executor;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.command.action.contract.JetBeanAction;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.tofu.bean.utils.UtilsBean.doublerFormatP2;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class JetBeanDefault {

    private final JetBeanAction jetBeanAction;
    private final PlayerBeansInteractor playerBeansInteractor;

    public JetBeanDefault(
            JetBeanAction jetBeanAction, PlayerBeansInteractor playerBeansInteractor
    ) {
        this.jetBeanAction = jetBeanAction;
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public Double calDistanceXYZ(Location l1, Location l2) {
        Double diffP2X = pow(l1.getBlockX() - l2.getBlockX(), 2);
        Double diffP2Y = pow(l1.getBlockY() - l2.getBlockY(), 2);
        Double diffP2Z = pow(l1.getBlockZ() - l2.getBlockZ(), 2);

        return sqrt(diffP2X + diffP2Y + diffP2Z);
    }

    public Double jetCost(Location playerLocation, Location targetLocation, Double rate) {

        Double cost = doublerFormatP2(calDistanceXYZ(playerLocation, targetLocation) * rate);

        if (cost <= 1000) {
            return 1000.0;
        }

        return cost;
    }

    public void jetBean(Player player, Location location, Double rate) {
        String playerName = player.getName();

        Location playerLocation = player.getLocation();

        Double cost = jetCost(playerLocation, location, rate);

        Double pocketValue = playerBeansInteractor.getValue(playerName);

        if (pocketValue == null) {
            player.sendMessage(ChatColor.DARK_RED + "Something Error (JetBeanUtils (jetBean)) tell BearSouL : pocketValue == null");
            return;
        }

        if (pocketValue >= cost) {
            playerBeansInteractor.decreasedValue(playerName, cost);

            messageOnJetDone(player);

            jetBeanAction.forceJetBeanLocation(player, location);
        } else {
            messageOnJetFail(player);
        }
    }

    public void messageJetCost(Player player, Double cost) {
        player.sendMessage(ChatColor.AQUA + "jet cost " + ChatColor.GOLD + cost.toString() + ChatColor.AQUA + " beans" );
    }

    public void messageOnJetDone(Player player) {
        player.sendMessage( ChatColor.AQUA + "JetBean Airline : " + ChatColor.GOLD + "You are premium customer :)");
    }

    public void messageOnJetFail(Player player) {
        player.sendMessage(ChatColor.YELLOW + "So poor check you money first :(");
    }
}
