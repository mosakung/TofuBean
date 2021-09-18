package com.tofu.bean.plugin.sign.event.impl;

import com.tofu.bean.plugin.casino.coupon.CouponManagement;
import com.tofu.bean.plugin.casino.gacha.GachaSlotBranch;
import com.tofu.bean.plugin.sign.event.contract.SignActionGachapon;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static com.tofu.bean.utils.prefix.message.PrefixMessage.withGachaPrefix;

public class SignActionGachaponImpl implements SignActionGachapon {

    @Override
    public void action(Player player, GachaSlotBranch gachaSlotBranch) {

        if(CouponManagement.retrieveCoupon(player, gachaSlotBranch.getRequireCoupon())) {
            player.sendMessage(withGachaPrefix(ChatColor.WHITE + "spend " + ChatColor.GREEN + gachaSlotBranch.getRequireCoupon() + ChatColor.WHITE + " coupon"));
            gachaSlotBranch.getGachaBoardGUI().open(player);
        } else {
            player.sendMessage(withGachaPrefix(ChatColor.YELLOW + "require minimum "+ ChatColor.DARK_RED + gachaSlotBranch.getRequireCoupon() + ChatColor.YELLOW + " coupon"));
        }
    }
}
