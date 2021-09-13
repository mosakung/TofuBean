package com.tofu.bean.plugin.permission.command;

import com.tofu.bean.data.enums.PermissionMethod;
import com.tofu.bean.presentation.PresentationPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public record BeanPermissionModuleCommand() implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player && player.hasPermission(PermissionMethod.DEVELOPER.getPermission())) {
            if (strings.length == 1 && strings[0].equals("help")) {
                showCommandManual(player);
                return true;
            } else if (strings.length == 2) {

                getterAndSetterPermission(strings, player, player);

            } else if (strings.length == 3) {

                Player target = Bukkit.getPlayer(strings[2]);

                if (target == null) {
                    player.sendMessage(ChatColor.YELLOW + "not found player name " + strings[2]);
                    return false;
                } else {
                    getterAndSetterPermission(strings, player, target);
                }

            } else {
                onInvalidCommand(player);
            }
        }

        return false;
    }

    private void getterAndSetterPermission(String[] strings, Player player, Player target) {
        PermissionAttachment targetAttachment = target.addAttachment(PresentationPlugin.getInstance());

        if (strings[0].equals("add")) {
            if (strings[1].equals(PermissionMethod.DEVELOPER.getValue())) {
                targetAttachment.setPermission(PermissionMethod.DEVELOPER.getPermission(), true);
                player.sendMessage(ChatColor.AQUA + "set permission developer " + target.getName());
            }
        } else if (strings[0].equals("de")) {
            if (strings[1].equals(PermissionMethod.DEVELOPER.getValue())) {
                targetAttachment.setPermission(PermissionMethod.DEVELOPER.getPermission(), false);
                player.sendMessage(ChatColor.AQUA + "delete permission developer " + target.getName());
            }
        } else {
            onInvalidCommand(player);
        }
    }

    private void onInvalidCommand(Player player) {
        player.sendMessage(ChatColor.YELLOW + "invalid command /beanp help");
    }

    private void showCommandManual(Player player) {
        player.sendMessage(ChatColor.WHITE + "===== Manual Tofu Bean =====");
        player.sendMessage(ChatColor.WHITE + "- /tofu add <permission name>");
        player.sendMessage(ChatColor.WHITE + "- /tofu de <permission name>");
        player.sendMessage(ChatColor.WHITE + "- /tofu add <permission name> <player name>");
        player.sendMessage(ChatColor.WHITE + "- /tofu de <permission name> <player name>");
    }
}
