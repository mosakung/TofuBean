package com.tofu.bean.plugin.teleport;

import com.tofu.bean.plugin.money.domain.contract.PlayerMoneyInteractor;
import com.tofu.bean.plugin.teleport.action.contract.TeleportAction;
import com.tofu.bean.plugin.teleport.action.impl.TeleportActionImpl;
import com.tofu.bean.plugin.teleport.executor.CheckJetHaveBeanExecutor;
import com.tofu.bean.plugin.teleport.executor.JetBeanPayMoneyExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportModuleCommand implements CommandExecutor {

    private final JetBeanPayMoneyExecutor jetBeanPayMoneyExecutor;
    private final CheckJetHaveBeanExecutor checkJetHaveBeanExecutor;

    public TeleportModuleCommand(PlayerMoneyInteractor playerMoneyInteractor) {
        TeleportAction teleportAction = new TeleportActionImpl();

        this.jetBeanPayMoneyExecutor = new JetBeanPayMoneyExecutor(teleportAction, playerMoneyInteractor);
        this.checkJetHaveBeanExecutor = new CheckJetHaveBeanExecutor();
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
                    jetBeanPayMoneyExecutor.executor(player, playerTarget);
                    return true;
                }else {
                    showNotFoundPlayer(player);
                }
            } else if (strings.length == 2 && strings[0].equals("cost")) {
                Player playerTarget = Bukkit.getPlayer(strings[1]);

                if (playerTarget != null) {
                    checkJetHaveBeanExecutor.executor(player, playerTarget);
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
        player.sendMessage(ChatColor.WHITE + "===== Manual Money Tofu Bean =====");
        player.sendMessage(ChatColor.WHITE + "- /jetbean <player target name>");
        player.sendMessage(ChatColor.WHITE + "- /jetbean cost <player target name>");
    }

    private void showNotFoundPlayer(Player player) {
        player.sendMessage(ChatColor.WHITE + "Can't find this player");
    }
}
