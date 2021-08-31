package com.tofu.bean.plugin.evoke;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class EvokeTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> suggest = new ArrayList<>();

        if (strings.length == 1) {
            suggest.add("help");
            suggest.add("beans");
            return suggest;
        }

        return null;
    }
}
