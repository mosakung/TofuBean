package com.tofu.bean.plugin.evoke;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.evoke.excutor.beans.EvokeBean2ExpExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EvokeModuleCommnd implements CommandExecutor {

    private final EvokeBean2ExpExecutor evokeBean2ExpExecutor;

    public EvokeModuleCommnd(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.evokeBean2ExpExecutor = new EvokeBean2ExpExecutor(playerBeansInteractor);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player) {

            if (strings.length == 1) {

                if (strings[0].equals("beans")) {
                    showBeansRate(player);
                    return true;
                }

                if (strings[0].equals("help")) {
                    showCommandManual(player);
                    return true;
                }
            }

            if (strings.length == 2) {

                if (strings[0].equals("beans")) {
                    try {
                        Double value = Double.parseDouble(strings[1]);
                        evokeBean2ExpExecutor.executor(player, value);
                        return true;
                    } catch (NumberFormatException exec) {
                        onInvalidNumber(player);
                        return false;
                    }
                }
            }

            onInvalidCommand(player);
            return false;
        }


        return false;
    }

    private void onInvalidCommand(Player player) {
        player.sendMessage(ChatColor.YELLOW + "invalid command /evoke help");
    }

    private void onInvalidNumber(Player player) {
        player.sendMessage(ChatColor.YELLOW + "please input number only");
    }

    private void showCommandManual(Player player) {
        player.sendMessage(ChatColor.WHITE + "===== Manual Tofu Bean =====");
        player.sendMessage(ChatColor.WHITE + "- /evoke beans");
        player.sendMessage(ChatColor.WHITE + "- /evoke beans <number>");
    }

    private void showBeansRate(Player player) {
        player.sendMessage(ChatColor.AQUA + "evoke rate "
                + ChatColor.GOLD + "7.2 bean "
                + ChatColor.AQUA + "-> "
                + ChatColor.GOLD + "1 exp "
                + ChatColor.AQUA + "("
                + ChatColor.GOLD + "10000 beans "
                + ChatColor.AQUA + ": "
                + ChatColor.GOLD + "30 level"
                + ChatColor.AQUA + ")"
        );
    }
}
