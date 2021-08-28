package com.tofu.bean.plugin.permission;

import com.tofu.bean.data.PermissionBean;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

public class BeanPermissionModuleCommand implements CommandExecutor {

    private final Plugin plugin;

    public BeanPermissionModuleCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player && player.hasPermission(PermissionBean.DEVELOPER.getPermission())) {
            if (strings.length == 1 && strings[0].equals("help")) {
                showCommandManual(player);
                return true;
            } else if (strings.length == 3) {

                PermissionAttachment attachment = player.addAttachment(plugin);

                if (strings[0].equals("add")) {
                    if (strings[1].equals("developer")) {
                        attachment.setPermission(PermissionBean.DEVELOPER.getPermission(), true);
                    }
                } else if (strings[0].equals("de")) {
                    if (strings[1].equals("developer")) {
                        attachment.setPermission(PermissionBean.DEVELOPER.getPermission(), false);
                    }
                } else {
                    onInvalidCommand(player);
                }

            } else {
                onInvalidCommand(player);
            }
        }

        return false;
    }

    private void onInvalidCommand(Player player) {
        player.sendMessage(ChatColor.YELLOW + "invalid command /beanp help");
    }

    private void showCommandManual(Player player) {
        player.sendMessage(ChatColor.WHITE + "===== Manual Money Tofu Bean =====");
        player.sendMessage(ChatColor.WHITE + "- /beanp add <permission name> <player name>");
        player.sendMessage(ChatColor.WHITE + "- /beanp de <permission name> <player name>");
    }
}
