package com.tofu.bean.plugin.beans;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;

import java.util.ArrayList;
import java.util.List;

public class BeansTabCompleterCommand implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> suggest = new ArrayList<>();
        List<String> onlinePlayer = new ArrayList<>(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).toList());

        if (strings.length == 1) {
            suggest.add("help");
            suggest.add("give");
            suggest.addAll(onlinePlayer);
            return suggest;
        }

        return null;
    }
}
