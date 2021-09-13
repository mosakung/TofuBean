package com.tofu.bean.plugin.casino.blackjack.gui;

import com.tofu.bean.data.enums.sign.branch.CasinoSignBranch;
import org.bukkit.entity.Player;

public interface BoardGUI {
    void open(Player player, CasinoSignBranch casinoSignBranch);
}
