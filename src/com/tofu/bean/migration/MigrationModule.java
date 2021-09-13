package com.tofu.bean.migration;

import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;

public class MigrationModule {

    private final JavaMySql db;

    public MigrationModule(JavaMySql db) {
        this.db = db;
    }

    public void migrate() {
    }
}
