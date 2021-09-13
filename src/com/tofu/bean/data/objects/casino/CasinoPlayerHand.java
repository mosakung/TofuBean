package com.tofu.bean.data.objects.casino;

import com.tofu.bean.plugin.casino.enums.CasinoCard;

public class CasinoPlayerHand {

    private CasinoCard[] hand;
    private int handOrder;

    public CasinoPlayerHand(
            CasinoCard[] hand,
            int handOrder
    ) {
        this.hand = hand;
        this.handOrder = handOrder;
    }

    public CasinoCard[] getHand() {
        return hand;
    }

    public int getHandOrder() {
        return handOrder;
    }
}
