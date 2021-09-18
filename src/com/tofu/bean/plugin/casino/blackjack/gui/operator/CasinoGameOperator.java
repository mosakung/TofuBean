package com.tofu.bean.plugin.casino.blackjack.gui.operator;

import com.tofu.bean.data.enums.casino.table.CasinoGameType;
import com.tofu.bean.plugin.casino.blackjack.gui.BlackJackGUICasino;
import com.tofu.bean.plugin.casino.blackjack.gui.CasinoBoardGUI;

public class CasinoGameOperator {

    private final BlackJackGUICasino blackJackGUI;

    public CasinoGameOperator() {
        this.blackJackGUI = new BlackJackGUICasino();
    }

    public CasinoBoardGUI provide(CasinoGameType casinoGameType) {
        if(CasinoGameType.BLACK_JACK == casinoGameType) {
            return blackJackGUI;
        }

        return null;
    }
}
