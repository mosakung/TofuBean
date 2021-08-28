package com.tofu.bean.plugin.beans.domain.contract;

public interface PlayerBeansInteractor {
    Double getValue(String playerName);
    void increasedValue(String playerName, Double value);
    void decreasedValue(String playerName, Double value);
}
