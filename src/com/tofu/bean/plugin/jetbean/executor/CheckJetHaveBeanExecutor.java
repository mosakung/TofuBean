package com.tofu.bean.plugin.jetbean.executor;

import com.tofu.bean.utils.UtilsBean;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class CheckJetHaveBeanExecutor extends UtilsBean {

    private static DecimalFormat df = new DecimalFormat("0.00");

    public void executor(Player player, Player target) {

        Location pl = player.getLocation();
        Location plt = target.getLocation();

        Double cost = calDistanceXYZ(pl, plt) * 2.5;

        Double formatCost = doublerFormatP2(cost);;

        player.sendMessage(ChatColor.AQUA + "jet cost " + ChatColor.GOLD + formatCost.toString() + ChatColor.AQUA + " beans" );
    }
}
