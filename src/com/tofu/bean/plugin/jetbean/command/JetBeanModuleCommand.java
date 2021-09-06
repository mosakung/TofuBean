package com.tofu.bean.plugin.jetbean.command;

import com.tofu.bean.domain.contract.jetbean.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.jetbean.command.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.command.action.impl.JetBeanActionImpl;
import com.tofu.bean.plugin.jetbean.command.executor.dead.CostJetBeanDeadLocationExecutorExecutor;
import com.tofu.bean.plugin.jetbean.command.executor.dead.JetBean2DeadLoactionExecutor;
import com.tofu.bean.plugin.jetbean.command.executor.original.CostJetBeanPlayerExecutor;
import com.tofu.bean.plugin.jetbean.command.executor.original.JetBean2PlayerExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JetBeanModuleCommand implements CommandExecutor {

    private final JetBean2PlayerExecutor jetBean2PlayerExecutor;
    private final CostJetBeanPlayerExecutor costJetBeanPlayerExecutor;

    private final JetBean2DeadLoactionExecutor jetBean2DeadLoaction;
    private final CostJetBeanDeadLocationExecutorExecutor costJetBeanDeadLocationExecutor;

    public JetBeanModuleCommand(
            PlayerBeansInteractor playerBeansInteractor,
            DeadJetBeanInteractor deadJetBeanInteractor
    ) {
        JetBeanAction jetBeanAction = new JetBeanActionImpl();

        this.jetBean2PlayerExecutor = new JetBean2PlayerExecutor(jetBeanAction, playerBeansInteractor);
        this.costJetBeanPlayerExecutor = new CostJetBeanPlayerExecutor(jetBeanAction, playerBeansInteractor);

        this.jetBean2DeadLoaction = new JetBean2DeadLoactionExecutor(jetBeanAction, playerBeansInteractor, deadJetBeanInteractor);
        this.costJetBeanDeadLocationExecutor = new CostJetBeanDeadLocationExecutorExecutor(jetBeanAction, playerBeansInteractor, deadJetBeanInteractor);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player) {

            if (strings.length == 1) {

                if(strings[0].equals("help")) {
                    showCommandManual(player);
                    return true;
                }

                Player playerTarget = Bukkit.getPlayer(strings[0]);

                if (playerTarget != null) {
                    jetBean2PlayerExecutor.executor(player, playerTarget);
                    return true;
                } else {
                    showNotFoundPlayer(player);
                    return false;
                }
            }

            if(strings.length == 2) {

                if(strings[0].equals("cost")) {
                    Player playerTarget = Bukkit.getPlayer(strings[1]);

                    if (playerTarget != null) {
                        costJetBeanPlayerExecutor.executor(player, playerTarget);
                        return true;
                    } else {
                        showNotFoundPlayer(player);
                        return false;
                    }
                }

                if(strings[0].equals("dead")) {

                    if(strings[1].equals("cost")) {
                        costJetBeanDeadLocationExecutor.executor(player);
                        return true;
                    }

                    if(strings[1].equals("--yes")) {
                        jetBean2DeadLoaction.executor(player);
                        return true;
                    }

                }
            }

            onInvalidCommand(player);
            return false;
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
