package com.tofu.bean.utils;

import com.tofu.bean.data.objects.casino.CasinoCard;
import com.tofu.bean.data.objects.casino.CasinoPlayerHand;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class CasinoUtils {

    public static CasinoPlayerHand[] covertRawtoCasinoPlayerHands(List<Pair<Integer, CasinoCard>> pairList) {

        List<Integer> orderHands = new ArrayList<>();

        pairList.forEach(casinoCardPair -> {

            Integer orderHand = casinoCardPair.getA();

            if (!(orderHands.contains(orderHand))) {
                orderHands.add(orderHand);
            }
        });

        List<CasinoPlayerHand> casinoPlayerHands = new ArrayList<>();

        orderHands.forEach(integer -> {

            List<CasinoCard> cardInHand = new ArrayList<>();

            pairList.forEach(casinoCardPair -> {

                if(casinoCardPair.getA().equals(integer)) {
                    cardInHand.add(casinoCardPair.getB());
                }
            });

            casinoPlayerHands.add(new CasinoPlayerHand(cardInHand.toArray(CasinoCard[]::new), integer));
        });

        return casinoPlayerHands.toArray(CasinoPlayerHand[]::new);
    }
}
