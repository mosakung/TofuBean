package com.tofu.bean.plugin.jetbean.command.suggestion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SpawnJetBeanTabcompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> suggest = new ArrayList<>();

        if (strings.length == 1) {
            suggest.add("cost");
            suggest.add("help");
            return suggest;
        }

        return null;
    }
}
