package com.tofu.bean.plugin.casino.blackjack.action;

import com.tofu.bean.plugin.casino.enums.CasinoCard;
import com.tofu.bean.plugin.casino.enums.StandardCardSymbolType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.tofu.bean.plugin.casino.enums.StandardCardPointType.*;
import static com.tofu.bean.plugin.casino.enums.StandardCardSymbolType.*;

public class BlackJackDeckAction {

    private List<CasinoCard> casinoCardList = new ArrayList<>();

    public CasinoCard getCasinoCard() {
        if(casinoCardList.size() < 10) {
            resetDeckCard();
        }
        return casinoCardList.remove(0);
    }

    private void resetDeckCard() {
        casinoCardList.clear();
        casinoCardList.addAll(getNewDeck());
        casinoCardList.addAll(getNewDeck());
        casinoCardList.addAll(getNewDeck());
        casinoCardList.addAll(getNewDeck());
        Collections.shuffle(casinoCardList);
    }

    private List<CasinoCard> getNewDeck() {
        List<CasinoCard> newCardDeck = new ArrayList<>();
        addSymbolSet(newCardDeck, CLUBS);
        addSymbolSet(newCardDeck, DIAMONDS);
        addSymbolSet(newCardDeck, HEART);
        addSymbolSet(newCardDeck, SPADES);
        return newCardDeck;
    }

    private void addSymbolSet(List<CasinoCard> newCardDeck, StandardCardSymbolType type) {
        newCardDeck.add(new CasinoCard(ACE, type));
        newCardDeck.add(new CasinoCard(TWO, type));
        newCardDeck.add(new CasinoCard(THREE, type));
        newCardDeck.add(new CasinoCard(FOUR, type));
        newCardDeck.add(new CasinoCard(FIVE, type));
        newCardDeck.add(new CasinoCard(SIX, type));
        newCardDeck.add(new CasinoCard(SEVEN, type));
        newCardDeck.add(new CasinoCard(EIGHT, type));
        newCardDeck.add(new CasinoCard(NINE, type));
        newCardDeck.add(new CasinoCard(TEN, type));
        newCardDeck.add(new CasinoCard(JACK, type));
        newCardDeck.add(new CasinoCard(QUEEN, type));
        newCardDeck.add(new CasinoCard(KING, type));
    }
}
