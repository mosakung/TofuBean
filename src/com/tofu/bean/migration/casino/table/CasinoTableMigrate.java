package com.tofu.bean.migration.casino.table;

import com.tofu.bean.data.methods.sign.branch.CasinoSignBranch;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.migration.contract.MigrateInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public record CasinoTableMigrate(JavaMySql db) implements MigrateInterface {

    @Override
    public void initialize() {
        Arrays.stream(CasinoSignBranch.values()).forEach(table -> {

            if (alreadyTable(table.getName())) {
                updateTable(table);
            } else {
                insertTable(table);
            }
        });
    }

    private Boolean alreadyTable(String tableName) {
        String selectTable = "SELECT `casino_table_id` FROM `casino_table` WHERE `name` = ?";

        String[] dataSet = {tableName};

        final ResultSet result = db.fetch(selectTable, dataSet);

        try {
            return result.next();
        } catch (SQLException e) {
            System.out.println("CasinoTableMigrate : alreadyTable : SQLException");
            e.printStackTrace();
        }

        return false;
    }

    private void insertTable(CasinoSignBranch table) {
        String insertTable = "INSERT INTO `casino_table` (`name`, `type`, `max_player`) VALUES (?, ?, " + table.getMaxPlayer() + ")";

        String[] dataSet = {table.getName(), table.getType().getType()};

        db.insert(insertTable, dataSet);

        System.out.println("BEAN HELLO Plugin : CASINO Migrate : " + table.getName());
    }

    private void updateTable(CasinoSignBranch table) {
        String updateTable = "UPDATE `casino_table` SET `type` = ?, `max_player` = " + table.getMaxPlayer() + " WHERE `name` = ?";

        String[] dataSet = {table.getType().getType(), table.getName()};

        db.modified(updateTable, dataSet);
    }
}
