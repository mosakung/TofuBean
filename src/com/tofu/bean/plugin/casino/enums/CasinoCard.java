package com.tofu.bean.plugin.casino.enums;

import com.tofu.bean.plugin.casino.enums.StandardCardSymbolType;
import com.tofu.bean.plugin.casino.enums.StandardCardPointType;

public class CasinoCard {

    private StandardCardPointType point;
    private StandardCardSymbolType symbol;

    public CasinoCard(
            StandardCardPointType point,
            StandardCardSymbolType symbol
    ) {
        this.point = point;
        this.symbol = symbol;
    }

    public StandardCardPointType getPoint() {
        return point;
    }

    public StandardCardSymbolType getSymbol() {
        return symbol;
    }
}
