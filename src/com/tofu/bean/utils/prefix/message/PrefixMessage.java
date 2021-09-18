package com.tofu.bean.utils.prefix.message;

import org.bukkit.ChatColor;

public class PrefixMessage {

    private static String createPrefix(String prefix) {
        return ChatColor.WHITE + "[" + ChatColor.GOLD + prefix + ChatColor.WHITE + "]" + ChatColor.RESET;
    }

    public static String withGachaPrefix(String message) {
        return createPrefix("Gachapon") + " " + message;
    }
}
