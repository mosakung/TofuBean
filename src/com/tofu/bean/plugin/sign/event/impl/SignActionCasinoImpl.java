package com.tofu.bean.plugin.sign.event.impl;

import com.tofu.bean.data.enums.sign.branch.CasinoSignBranch;
import com.tofu.bean.plugin.casino.blackjack.gui.operator.CasinoGameOperator;
import com.tofu.bean.plugin.sign.event.contract.SignActionCasino;
import org.bukkit.entity.Player;

public class SignActionCasinoImpl implements SignActionCasino {
    private final CasinoGameOperator casinoGameOperator;

    public SignActionCasinoImpl(
    ) {
        this.casinoGameOperator = new CasinoGameOperator();
    }

    @Override
    public void action(Player player, CasinoSignBranch casinoSignBranch) {
        casinoGameOperator.provide(casinoSignBranch.getCasinoTableType()).open(player, casinoSignBranch);
    }
}
