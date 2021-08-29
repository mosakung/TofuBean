package com.tofu.bean.plugin.jetbean.suggestion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class JetBeanTabCompleterCommand implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> suggest = new ArrayList<>();

        if (strings.length == 1) {
            suggest.add("help");
            suggest.add("give");
            return suggest;
        }

        return null;
    }
}
