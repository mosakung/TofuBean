package com.tofu.bean.plugin.beans.command;

import com.tofu.bean.data.methods.PermissionMethod;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.beans.command.executor.DecreasedBeansValueExecutor;
import com.tofu.bean.plugin.beans.command.executor.GiveBean2FriendExecutor;
import com.tofu.bean.plugin.beans.command.executor.IncreasedBeansValueExecutor;
import com.tofu.bean.plugin.beans.command.executor.ShowBeansValueExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BeansModuleCommand implements CommandExecutor {

    private final ShowBeansValueExecutor showBeansValueExecutor;
    private final IncreasedBeansValueExecutor increasedBeansValueExecutor;
    private final DecreasedBeansValueExecutor decreasedBeansValueExecutor;
    private final GiveBean2FriendExecutor giveBean2FriendExecutor;

    public BeansModuleCommand(PlayerBeansInteractor playerBeansInteractor) {
        this.showBeansValueExecutor = new ShowBeansValueExecutor(playerBeansInteractor);
        this.increasedBeansValueExecutor = new IncreasedBeansValueExecutor(playerBeansInteractor);
        this.decreasedBeansValueExecutor = new DecreasedBeansValueExecutor(playerBeansInteractor);
        this.giveBean2FriendExecutor = new GiveBean2FriendExecutor(playerBeansInteractor);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {

            final boolean developer = player.hasPermission(PermissionMethod.DEVELOPER.getPermission());

            if (strings.length == 0) {
                showBeansValueExecutor.executor(player);
                return true;
            } else if (strings.length == 1 && strings[0].equals("help")) {
                showCommandManual(player);
                return true;
            } else if(strings.length == 3 && strings[0].equals("give")) {

                try {
                    Player target = Bukkit.getPlayer(strings[1]);
                    Double beanCost = Double.parseDouble(strings[2]);

                    if(target != null) {
                        giveBean2FriendExecutor.executor(player, target, beanCost);
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "not found player name");
                    }

                    return true;
                } catch (NumberFormatException exec) {
                    player.sendMessage(ChatColor.YELLOW + "please input format number");
                }
            } else if(developer) {
                if (strings.length == 2 && strings[0].equals("add")) {

                    try {
                        Double addValue = Double.parseDouble(strings[1]);
                        increasedBeansValueExecutor.executor(player, addValue);
                        return true;
                    } catch (NumberFormatException exec) {
                        player.sendMessage(ChatColor.YELLOW + "please input format number");
                    }

                } else if (strings.length == 2 && strings[0].equals("de")) {

                    try {
                        Double addValue = Double.parseDouble(strings[1]);
                        decreasedBeansValueExecutor.executor(player, addValue);
                        return true;
                    } catch (NumberFormatException exec) {
                        player.sendMessage(ChatColor.YELLOW + "please input format number");
                    }

                }
            } else {
                onInvalidCommand(player);
            }
        }

        return false;
    }

    private void onInvalidCommand(Player player) {
        player.sendMessage(ChatColor.YELLOW + "invalid command /beans help");
    }

    private void showCommandManual(Player player) {
        player.sendMessage(ChatColor.WHITE + "===== Manual Tofu Bean =====");
        player.sendMessage(ChatColor.WHITE + "- /beans");
        player.sendMessage(ChatColor.WHITE + "- /beans add <number> (developer only)");
        player.sendMessage(ChatColor.WHITE + "- /beans de <number> (developer only)");
    }
}
