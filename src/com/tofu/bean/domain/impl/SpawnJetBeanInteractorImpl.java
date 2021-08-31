package com.tofu.bean.domain.impl;

import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.domain.contract.SpawnJetBeanInteractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public record SpawnJetBeanInteractorImpl(
        JavaMySql db
) implements SpawnJetBeanInteractor {

    @Override
    public String getSpawnLocation(String spawnName) {

        String selectSpawnLocation = "SELECT `spawn_location` FROM `spawn` WHERE `spawn_name` = ?";

        String[] dataSet = {spawnName};

        final ResultSet result = db.fetch(selectSpawnLocation, dataSet);

        try {
            if (result.next()) {
                return result.getString("spawn_location");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("fetch spawn_location by spawn_name error");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void insertSpawnLocation(String spawnName, String spawnLocation) {
        String insertSpawnPlace = "INSERT INTO `spawn` (`spawn_name`, `spawn_location`) VALUES (?, ?)";

        String[] dataSet = {spawnName, spawnLocation};

        db.insert(insertSpawnPlace, dataSet);
    }

    @Override
    public void updateSpawnLocation(String spawnName, String spawnLocation) {

        if (!hasSpawnName(spawnName)) {
            return;
        }

        String updateSpawnLocation = "UPDATE `spawn` SET `spawn_location` = ? WHERE `spawn_name` = ?";

        String[] dataSet = {spawnLocation, spawnName};

        db.modified(updateSpawnLocation, dataSet);
    }

    @Override
    public Boolean hasSpawnName(String spawnName) {

        String selectSpawnLocation = "SELECT * FROM `spawn` WHERE `spawn_name` = ?";

        String[] dataSet = {spawnName};

        final ResultSet result = db.fetch(selectSpawnLocation, dataSet);

        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("fetch spawn_location by spawn_name error");
            e.printStackTrace();
        }

        System.out.println("Don't ");
        return false;
    }
}
