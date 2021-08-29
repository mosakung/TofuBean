package com.tofu.bean.domain.contract;

public interface PlayerBeansInteractor {
    Double getValue(String playerName);
    void increasedValue(String playerName, Double value);
    void decreasedValue(String playerName, Double value);
}
