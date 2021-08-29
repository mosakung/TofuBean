package com.tofu.bean.plugin.permission;

import com.tofu.bean.data.PermissionBean;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanPermissionTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> suggest = new ArrayList<>();
        List<String> onlinePlayer = new ArrayList<>(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).toList());

        if (strings.length == 1) {
            suggest.add("add");
            suggest.add("de");
            return suggest;
        }

        if (strings.length == 2) {
            suggest.addAll(getPermission());
            return suggest;
        }

        return null;
    }

    private List<String> getPermission() {

        List<String> permissionName = new ArrayList<>();
        PermissionBean[] permissionBeans = PermissionBean.class.getEnumConstants();

        Arrays.stream(permissionBeans).map(PermissionBean::getValue).forEach(permissionName::add);

        return permissionName;
    }
}
