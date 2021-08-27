package com.tofu.bean.mariadb.impl.db.mysql;

import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;

import java.sql.*;

public class JavaMySqlImpl implements JavaMySql {

    final String ip = "127.0.0.1";
    final String port = "3306";
    final String dbName = "minecraft-tofu-bean";
    final String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;

    final String mos = "jdbc:mysql://127.0.0.1:3306/minecraft-tofu-bean";

    final String username = "root";

    private Connection connection;

    public JavaMySqlImpl() {
        try {
            this.connection = DriverManager.getConnection(mos, username, "");
            System.out.println("Connected database...");
        } catch (SQLException exec) {
            System.out.println("Can't connect to database");
            exec.printStackTrace();
        }
    }

    @Override
    public ResultSet fetch(String sql, String[] args) {
        if (connection == null) {
            return null;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int index = 1; index <= args.length; index++) {
                statement.setString(index, args[index - 1]);
            }

            return statement.executeQuery();
        } catch (SQLException exec) {
            System.out.println("Can't inject SQL to database " + sql);
            exec.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(String sql, String[] args) {
        if (connection == null) {
            return -1;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int index = 1; index <= args.length; index++) {
                statement.setString(index, args[index - 1]);
            }

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException exec) {
            System.out.println("Can't inject SQL to database " + sql);
            exec.printStackTrace();
        }

        return -1;
    }


    @Override
    public void modified(String sql, String[] args) {
        if (connection == null) {
            return;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int index = 1; index <= args.length; index++) {
                statement.setString(index, args[index - 1]);
            }

            statement.executeUpdate();
        } catch (SQLException exec) {
            System.out.println("Can't inject SQL to database " + sql);
            exec.printStackTrace();
        }
    }
}
