package com.tofu.bean.domain.impl;

import com.tofu.bean.domain.contract.DeadJetBeanInteractor;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeadJetBeanInteractorImpl extends DefaultInteractorImpl implements DeadJetBeanInteractor {

    private final JavaMySql db;

    public DeadJetBeanInteractorImpl(
            JavaMySql db
    ) {
        super(db);
        this.db = db;
    }

    @Override
    public Boolean hasDeadLocation(String playerName) {

        final int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return false;
        }

        String selectDeadLocation = "SELECT `curr_location_dead_used` FROM `user` WHERE `user_id` = " + playerId;

        String[] dataSet = {};

        final ResultSet result = db.fetch(selectDeadLocation, dataSet);

        try {
            if (result.next()) {
                return result.getBoolean("curr_location_dead_used");
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("fetch spawn_location by spawn_name error");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String getDeadLocation(String playerName) {

        final int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return null;
        }

        String selectDeadLocation = "SELECT `curr_location_dead` FROM `user` WHERE `username` = ? AND `curr_location_dead_used` = 1";

        String[] dataSet = {playerName};

        final ResultSet result = db.fetch(selectDeadLocation, dataSet);

        try {
            if (result.next()) {
                String deadLocation = result.getString("curr_location_dead");

                if (deadLocation == null) {
                    return null;
                }

                return deadLocation;
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
    public void setDeadLoaction(String playerName, String location) {
        final int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return;
        }

        String updateDeadLocation = "UPDATE `user` SET `curr_location_dead` = ? , `curr_location_dead_used` = 1 WHERE `user_id` = " + playerId;

        String[] dataSet = {location};

        db.modified(updateDeadLocation, dataSet);
    }

    @Override
    public void useDeadLocation(String playerName) {
        final int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return;
        }

        String updateDeadLocation = "UPDATE `user` SET `curr_location_dead_used` = 0 WHERE `user_id` = " + playerId;

        String[] dataSet = {};

        db.modified(updateDeadLocation, dataSet);
    }
}
