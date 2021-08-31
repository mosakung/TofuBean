package com.tofu.bean.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignMessageUtils {

    public static String signMessageActionFormat(String action) {
        System.out.println();
        return ChatColor.AQUA + "[" + ChatColor.DARK_RED + action + ChatColor.AQUA + "]" + ChatColor.DARK_RED;
    }

    public static String signMessageItemLabelFormat(String itemLabel) {
        return ChatColor.WHITE + itemLabel;
    }

    public static String signMessageAmountFormat(String amount) {
        return ChatColor.GREEN + amount + ChatColor.AQUA + " piece";
    }

    public static String signMessageBeansFormat(String beans) {
        return ChatColor.GOLD + beans + ChatColor.AQUA + " beans";
    }

    public static String revertSignMessageActionFormat(String action) {

        String cleanString = ChatColor.stripColor(action);

        Pattern pattern = Pattern.compile("^\\[(\\w+)\\]$");

        Matcher matcher = pattern.matcher(cleanString);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    public static String revertSignMessageAmountFormat(String amount) {

        String cleanString = ChatColor.stripColor(amount);

        Pattern pattern = Pattern.compile("(\\d+)\\spiece$");

        Matcher matcher = pattern.matcher(cleanString);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    public static String revertSignMessageBeansFormat(String beans) {

        String cleanString = ChatColor.stripColor(beans);

        Pattern pattern = Pattern.compile("(\\d+)\\sbeans$");

        Matcher matcher = pattern.matcher(cleanString);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
