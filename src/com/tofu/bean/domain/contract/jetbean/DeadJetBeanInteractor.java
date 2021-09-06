package com.tofu.bean.domain.contract.jetbean;

public interface DeadJetBeanInteractor {

    Boolean hasDeadLocation(String playerName);
    String getDeadLocation(String playerName);
    void setDeadLoaction(String playerName, String location);
    void useDeadLocation(String playerName);
}
