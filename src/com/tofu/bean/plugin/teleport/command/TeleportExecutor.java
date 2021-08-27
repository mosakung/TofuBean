package com.tofu.bean.plugin.teleport.command;

import com.tofu.bean.plugin.teleport.action.contract.TeleportAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportExecutor implements CommandExecutor {

    private TeleportAction teleportAction;

    public TeleportExecutor(TeleportAction teleportAction) {
        this.teleportAction = teleportAction;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (strings.length != 1) {
                player.sendMessage(ChatColor.YELLOW + "/tp <player name>");
            } else {
                Player playerTarget = Bukkit.getPlayer(strings[0]);

                teleportAction.forceTeleportPlayerCalDistance(player, playerTarget);
            }
        }

        return false;
    }
}
