package com.tofu.bean.domain.impl;

import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.domain.contract.PlayerBeansInteractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerBeansInteractorImpl extends DefaultInteractorImpl implements PlayerBeansInteractor    {

    private final JavaMySql db;

    public PlayerBeansInteractorImpl(
            JavaMySql db
    ) {
        super(db);
        this.db = db;
    }


    @Override
    public Double getValue(String playerName) {
        final int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return null;
        }

        String selectValueById = "SELECT `money` FROM `user` WHERE `user_id` = " + playerId;

        String[] dataSet = {};

        final ResultSet result = db.fetch(selectValueById, dataSet);

        try {
            if (result.next()) {
                return result.getDouble("money");
            }
        } catch (SQLException e) {
            System.out.println("fetch money Id error");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void increasedValue(String playerName, Double value) {
        final int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return;
        }

        String increasedValueById = "UPDATE `user` SET `money` = money + " + value.toString() + " WHERE `user_id` = " + playerId;

        String[] dataSet = {};

        db.modified(increasedValueById, dataSet);
    }

    @Override
    public void decreasedValue(String playerName, Double value) {
        final int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return;
        }

        String decreasedValueById = "UPDATE `user` SET `money` = money - " + value.toString() + " WHERE `user_id` = " + playerId;

        String[] dataSet = {};

        db.modified(decreasedValueById, dataSet);
    }
}
