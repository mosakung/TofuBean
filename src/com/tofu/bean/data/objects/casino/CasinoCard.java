package com.tofu.bean.data.objects.casino;

import com.tofu.bean.data.methods.casino.card.standard.StandardCardSymbolMethod;
import com.tofu.bean.data.methods.casino.card.standard.StandardCardPointMethod;

public class CasinoCard {

    private StandardCardPointMethod point;
    private StandardCardSymbolMethod symbol;

    public CasinoCard(
            StandardCardPointMethod point,
            StandardCardSymbolMethod symbol
    ) {
        this.point = point;
        this.symbol = symbol;
    }

    public StandardCardPointMethod getPoint() {
        return point;
    }

    public StandardCardSymbolMethod getSymbol() {
        return symbol;
    }
}
