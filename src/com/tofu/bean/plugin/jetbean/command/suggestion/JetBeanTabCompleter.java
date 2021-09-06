package com.tofu.bean.plugin.jetbean.command.suggestion;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;

import java.util.ArrayList;
import java.util.List;

public class JetBeanTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> suggest = new ArrayList<>();
        List<String> onlinePlayer = new ArrayList<>(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).toList());

        if (strings.length == 1) {
            suggest.add("help");
            suggest.add("cost");
            suggest.add("dead");
            suggest.addAll(onlinePlayer);
            return suggest;
        }

        if (strings.length == 2) {

            if (strings[0].equals("dead")) {
                suggest.add("cost");
                suggest.add("--yes");
                return suggest;
            }
        }

        return null;
    }
}
