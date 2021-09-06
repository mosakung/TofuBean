package com.tofu.bean.mariadb.contract.db.mysql;

import java.sql.Connection;
import java.sql.ResultSet;

public interface JavaMySql {
    Connection provideConnection();
    ResultSet fetch(String sql, String[] args);
    int insert(String sql, String[] args);
    void modified(String sql, String[] args);
}
