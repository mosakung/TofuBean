package com.tofu.bean.utils.sign;

import com.tofu.bean.data.enums.item.CustomMaterialItem;
import org.bukkit.ChatColor;
import oshi.util.tuples.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tofu.bean.data.enums.item.CustomMaterialItem.getCustomMaterialItem;

public class ExchangeSignMessageUtils {

    public static String buildExchangeMessageRequire(String requireLabel, int amountRequire) {
        return ChatColor.YELLOW + "(" + ChatColor.GREEN + amountRequire + " " + ChatColor.WHITE + requireLabel + ChatColor.YELLOW + ")";
    }

    public static String buildExchangeMessageReward(CustomMaterialItem reward, int amountReward) {
        return ChatColor.GREEN + String.valueOf(amountReward) + " " + ChatColor.WHITE + reward.getItemName();
    }

    public static Pair<CustomMaterialItem, Integer> revertExchangeMessageRequire(String message) {
        String cleanString = ChatColor.stripColor(message);

        Pattern pattern = Pattern.compile("^\\((\\d+)\\s(.+)\\)$");

        return convertMessage2PairValue(cleanString, pattern);
    }

    public static Pair<CustomMaterialItem, Integer> revertExchangeMessageReward(String message) {
        String cleanString = ChatColor.stripColor(message);

        Pattern pattern = Pattern.compile("^(\\d+)\\s(.+)$");

        return convertMessage2PairValue(cleanString, pattern);
    }

    private static Pair<CustomMaterialItem, Integer> convertMessage2PairValue(String cleanString, Pattern pattern) {
        Matcher matcher = pattern.matcher(cleanString);

        if (matcher.find()) {

            String amountRequire = matcher.group(1);
            String customMaterialItem = matcher.group(2);

            CustomMaterialItem _customMaterialItem = getCustomMaterialItem(customMaterialItem);
            try {
                int _amountRequire = Integer.parseInt(amountRequire);

                if (_customMaterialItem == null) {
                    return null;
                }

                return new Pair<>(_customMaterialItem, _amountRequire);
            } catch (NumberFormatException exec) {
                return null;
            }
        }

        return null;
    }
}
