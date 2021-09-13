package com.tofu.bean.utils.sign;

import org.bukkit.ChatColor;

public class SignMessageUtils {

    public static String buildSignMessageActionFormat(String action) {
        return ChatColor.AQUA + "[" + ChatColor.DARK_RED + action + ChatColor.AQUA + "]" + ChatColor.DARK_RED;
    }

    public static String buildExchangeMessageBeans(Double beans) {
        return ChatColor.GOLD + beans.toString() + ChatColor.AQUA + " beans";
    }

}
