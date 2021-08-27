package com.tofu.bean.plugin.money;

import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.plugin.money.domain.contract.PlayerMoneyInteractor;
import com.tofu.bean.plugin.money.executor.DecreasedMoneyValueExecutor;
import com.tofu.bean.plugin.money.executor.IncreasedMoneyValueExecutor;
import com.tofu.bean.plugin.money.executor.ShowMoneyValueExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyModuleCommand implements CommandExecutor {

    private final ShowMoneyValueExecutor showMoneyValueExecutor;
    private final IncreasedMoneyValueExecutor increasedMoneyValueExecutor;
    private final DecreasedMoneyValueExecutor decreasedMoneyValueExecutor;

    public MoneyModuleCommand(PlayerMoneyInteractor playerMoneyInteractor) {
        this.showMoneyValueExecutor = new ShowMoneyValueExecutor(playerMoneyInteractor);
        this.increasedMoneyValueExecutor = new IncreasedMoneyValueExecutor(playerMoneyInteractor);
        this.decreasedMoneyValueExecutor = new DecreasedMoneyValueExecutor(playerMoneyInteractor);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (strings.length == 0) {
                showMoneyValueExecutor.executor(player);
            } else if (strings.length == 1 && strings[0].equals("help")) {
                showCommandManual(player);
                return true;
            } else if (strings.length == 2 && strings[0].equals("add")) {

                try {
                    Double addValue = Double.parseDouble(strings[1]);
                    increasedMoneyValueExecutor.executor(player, addValue);
                    return true;
                } catch (NumberFormatException exec) {
                    player.sendMessage(ChatColor.YELLOW + "please input format number");
                }

            } else if (strings.length == 2 && strings[0].equals("de")) {

                try {
                    Double addValue = Double.parseDouble(strings[1]);
                    decreasedMoneyValueExecutor.executor(player, addValue);
                    return true;
                } catch (NumberFormatException exec) {
                    player.sendMessage(ChatColor.YELLOW + "please input format number");
                }

            } else {
                onInvalidCommand(player);
            }
        }

        return false;
    }

    private void onInvalidCommand(Player player) {
        player.sendMessage(ChatColor.YELLOW + "invalid command /money help");
    }

    private void showCommandManual(Player player) {
        player.sendMessage(ChatColor.WHITE + "===== Manual Money Tofu Bean =====");
        player.sendMessage(ChatColor.WHITE + "- /money");
        player.sendMessage(ChatColor.WHITE + "- /money add <number>");
        player.sendMessage(ChatColor.WHITE + "- /money de <number>");
    }
}
