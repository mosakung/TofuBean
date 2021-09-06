package com.tofu.bean.plugin.evoke.command.excutor;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static com.tofu.bean.utils.UtilsBean.doublerFormatP2;

public class EvokeBean2ExpExecutor {

    private final PlayerBeansInteractor playerBeansInteractor;

    public EvokeBean2ExpExecutor(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void executor(Player player, Double beans) {

        int newExp = (int) Math.floor(beans * 0.1186);

        if (newExp > 0) {
            Double cost = doublerFormatP2(newExp / 0.1186);

            String playerName = player.getName();

            Double pocketValue = playerBeansInteractor.getValue(playerName);

            if (pocketValue >= cost) {
                player.giveExp(newExp);
                playerBeansInteractor.decreasedValue(player.getName(), cost);

                player.sendMessage(ChatColor.AQUA + "evoke " + ChatColor.GOLD + cost + ChatColor.AQUA + " beans");
            } else {
                messageOnNoBeans(player);
            }

        } else {
            player.sendMessage(ChatColor.YELLOW + "should evoke more beans");
        }
    }

    private void messageOnNoBeans(Player player) {
        player.sendMessage(ChatColor.YELLOW + "can't evoke beans, check you beans!!");
    }
}
