package com.tofu.bean.plugin.sign.event.contract;

import com.tofu.bean.data.enums.sign.branch.CasinoSignBranch;
import org.bukkit.entity.Player;

public interface SignActionCasino {

    void action(Player player, CasinoSignBranch casinoSignBranch);
}
