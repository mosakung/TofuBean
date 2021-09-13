package com.tofu.bean.plugin.casino.blackjack.gui.operator;

import com.tofu.bean.data.enums.casino.table.CasinoGameType;
import com.tofu.bean.plugin.casino.blackjack.gui.BlackJackGUI;
import com.tofu.bean.plugin.casino.blackjack.gui.BoardGUI;

public class CasinoGameOperator {

    private final BlackJackGUI blackJackGUI;

    public CasinoGameOperator() {
        this.blackJackGUI = new BlackJackGUI();
    }

    public BoardGUI provide(CasinoGameType casinoGameType) {
        if(CasinoGameType.BLACK_JACK == casinoGameType) {
            return blackJackGUI;
        }

        return null;
    }
}
