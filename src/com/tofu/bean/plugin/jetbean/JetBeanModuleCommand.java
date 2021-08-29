package com.tofu.bean.plugin.jetbean;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.action.impl.JetBeanActionImpl;
import com.tofu.bean.plugin.jetbean.executor.original.CostJetBeanPlayer;
import com.tofu.bean.plugin.jetbean.executor.original.JetBean2Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JetBeanModuleCommand implements CommandExecutor {

    private final JetBean2Player jetBean2Player;
    private final CostJetBeanPlayer costJetBeanPlayer;

    public JetBeanModuleCommand(PlayerBeansInteractor playerBeansInteractor) {
        JetBeanAction jetBeanAction = new JetBeanActionImpl();

        this.jetBean2Player = new JetBean2Player(jetBeanAction, playerBeansInteractor);
        this.costJetBeanPlayer = new CostJetBeanPlayer(jetBeanAction, playerBeansInteractor);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player) {

            if (strings.length == 1 && strings[0].equals("help")) {
                showCommandManual(player);
                return true;
            } else if (strings.length == 1) {
                Player playerTarget = Bukkit.getPlayer(strings[0]);

                if (playerTarget != null) {
                    jetBean2Player.executor(player, playerTarget);
                    return true;
                }else {
                    showNotFoundPlayer(player);
                }
            } else if (strings.length == 2 && strings[0].equals("cost")) {
                Player playerTarget = Bukkit.getPlayer(strings[1]);

                if (playerTarget != null) {
                    costJetBeanPlayer.executor(player, playerTarget);
                    return true;
                } else {
                    showNotFoundPlayer(player);
                }
            } else {
                onInvalidCommand(player);
            }
        }

        return false;
    }

    private void onInvalidCommand(Player player) {
        player.sendMessage(ChatColor.YELLOW + "invalid command /jetbean help");
    }

    private void showCommandManual(Player player) {
        player.sendMessage(ChatColor.WHITE + "===== Manual Tofu Bean =====");
        player.sendMessage(ChatColor.WHITE + "- /jetbean <player target name>");
        player.sendMessage(ChatColor.WHITE + "- /jetbean cost <player target name>");
    }

    private void showNotFoundPlayer(Player player) {
        player.sendMessage(ChatColor.WHITE + "Can't find this player");
    }
}
