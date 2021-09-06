package com.tofu.bean.domain;

import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultInteractorImpl implements DefaultInteractor {

    private final JavaMySql db;

    public DefaultInteractorImpl(
            JavaMySql db
    ) {
        this.db = db;
    }

    @Override
    public Boolean hasPlayer(String playerName) {

        String selectPlayer = "SELECT `user_id` FROM `user` WHERE `username` = ?";

        String[] dataSet = {playerName};

        final ResultSet result = db.fetch(selectPlayer, dataSet);

        try {
            if (result.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("DefaultInteractorImpl : hasPlayer : SQLException");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getPlayerId(String playerName) {
        String selectPlayer = "SELECT `user_id` FROM `user` WHERE `username` = ?";

        String[] dataSet = {playerName};

        final ResultSet result = db.fetch(selectPlayer, dataSet);

        try {
            if (result.next()) {
                return result.getInt("user_id");
            } else {
                return initPlayer(playerName);
            }
        } catch (SQLException e) {
            System.out.println("DefaultInteractorImpl : getPlayerId : SQLException");
            e.printStackTrace();
        }

        return -1;
    }

    private int initPlayer(String playerName) {
        String createPocket = "INSERT `user` (`username`) VALUES (?)";

        String[] PocketSet = {playerName};

        return db.insert(createPocket, PocketSet);
    }
}
