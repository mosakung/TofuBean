package com.tofu.bean.utils.sign;

import org.bukkit.ChatColor;

public class CasinoSignMessageUtils {

    public static String buildCasinoMessageTableNameLabel(String tableNameLabel) {
        return ChatColor.WHITE + tableNameLabel;
    }

    public static String buildCasinoMessageRule(int maxPlayer, int minimumPlayer) {
        return "" + ChatColor.GREEN + minimumPlayer + ChatColor.AQUA + "(" + ChatColor.DARK_RED + maxPlayer + ChatColor.AQUA + ") player";
    }

    public static String buildCasinoMessageMessageBet(Double bet) {
        return ChatColor.GOLD + bet.toString() + ChatColor.AQUA + " bet";
    }
}
