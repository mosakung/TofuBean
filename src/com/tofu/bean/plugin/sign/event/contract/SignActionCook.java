package com.tofu.bean.plugin.sign.event.contract;

import com.tofu.bean.data.enums.sign.branch.CookSignBranch;
import org.bukkit.entity.Player;

public interface SignActionCook {
    void action(Player player, CookSignBranch cookItemMethod);
}
