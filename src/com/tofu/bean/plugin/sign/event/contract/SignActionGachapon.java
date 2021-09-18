package com.tofu.bean.plugin.sign.event.contract;

import com.tofu.bean.plugin.casino.gacha.GachaSlotBranch;
import org.bukkit.entity.Player;

public interface SignActionGachapon {

    void action(Player player, GachaSlotBranch gachaSlotBranch);
}
