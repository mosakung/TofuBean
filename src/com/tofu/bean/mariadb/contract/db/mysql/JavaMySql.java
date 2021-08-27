package com.tofu.bean.mariadb.contract.db.mysql;

import java.sql.ResultSet;

public interface JavaMySql {
    ResultSet fetch(String sql, String[] args);
    int insert(String sql, String[] args);
    void modified(String sql, String[] args);
}
