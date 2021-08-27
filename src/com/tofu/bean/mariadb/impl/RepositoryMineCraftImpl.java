package com.tofu.bean.mariadb.impl;

import com.tofu.bean.mariadb.contract.RepositoryMineCraft;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.mariadb.impl.db.mysql.JavaMySqlImpl;

public class RepositoryMineCraftImpl implements RepositoryMineCraft {

    private final JavaMySql db;

    public RepositoryMineCraftImpl() {
        this.db = new JavaMySqlImpl();
    }

    @Override
    public JavaMySql dbProvider() {
        return db;
    }
}
