package com.tofu.bean.utils.sign;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuySignMessageUtils {

    public static String buySignMessageItemLabelFormat(String itemLabel) {
        return ChatColor.WHITE + itemLabel;
    }

    public static String buySignMessageAmountFormat(String amount) {
        return ChatColor.GREEN + amount + ChatColor.AQUA + " piece";
    }

    public static String buySignMessageBeansFormat(String beans) {
        return ChatColor.GOLD + beans + ChatColor.AQUA + " beans";
    }

    public static String revertBuySignMessageActionFormat(String action) {

        String cleanString = ChatColor.stripColor(action);

        Pattern pattern = Pattern.compile("^\\[(\\w+)\\]$");

        Matcher matcher = pattern.matcher(cleanString);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    public static String revertBuySignMessageAmountFormat(String amount) {

        String cleanString = ChatColor.stripColor(amount);

        Pattern pattern = Pattern.compile("(\\d+)\\spiece$");

        Matcher matcher = pattern.matcher(cleanString);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    public static String revertBuySignMessageBeansFormat(String beans) {

        String cleanString = ChatColor.stripColor(beans);

        Pattern pattern = Pattern.compile("(\\d+)\\sbeans$");

        Matcher matcher = pattern.matcher(cleanString);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
