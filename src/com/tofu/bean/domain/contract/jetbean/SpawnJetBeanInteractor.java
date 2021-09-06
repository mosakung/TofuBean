package com.tofu.bean.domain.contract.jetbean;

public interface SpawnJetBeanInteractor {

    String getSpawnLocation(String spawnName);
    void insertSpawnLocation(String spawnName, String spawnLocation);
    void updateSpawnLocation(String spawnName, String spawnLocation);
    Boolean hasSpawnName(String spawnName);
}
