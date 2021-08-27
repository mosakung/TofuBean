package com.tofu.bean.plugin.money.domain.contract;

public interface PlayerMoneyInteractor {
    Double getValue(String playerName);
    void increasedValue(String playerName, Double value);
    void decreasedValue(String playerName, Double value);
}
