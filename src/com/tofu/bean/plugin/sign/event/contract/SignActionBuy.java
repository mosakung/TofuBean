package com.tofu.bean.plugin.sign.event.contract;

import com.tofu.bean.data.methods.sign.branch.BuySignBranch;
import org.bukkit.entity.Player;

public interface SignActionBuy {
    void action(Player player, BuySignBranch buySignBranch);
}
