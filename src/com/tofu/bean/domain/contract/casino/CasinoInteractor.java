package com.tofu.bean.domain.contract.casino;

import com.tofu.bean.data.methods.sign.branch.CasinoSignBranch;
import com.tofu.bean.data.objects.casino.CasinoCard;
import com.tofu.bean.data.objects.casino.CasinoPlayerHand;

public interface CasinoInteractor {

    void sitInTable(String playerName, CasinoSignBranch casinoTableMethod, Double betValue);
    void standUpTable(String playerName);
    void callReady(String playerName);
    void callUnReady(String playerName);
    void overCardInTable(CasinoCard[] casinoCard, CasinoSignBranch casinoTableMethod);
    void pushCardInPlayer(String playerName, CasinoCard casinoCard, int handOrder);
    CasinoPlayerHand[] viewCardPlayer(String playerName);
    void clearCardInPlayer(String[] playerName);
    void clearCardInPlayer(String playerName);
    Boolean hasPlayerAllReady(CasinoSignBranch casinoTableMethod);
    Boolean alreadySitIn(int playerId);
    Boolean hasFullPlayer(int casinoTableId);
}
