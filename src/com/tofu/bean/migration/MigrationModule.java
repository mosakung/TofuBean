package com.tofu.bean.migration;

import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.migration.casino.table.CasinoTableMigrate;

public class MigrationModule {

    private final JavaMySql db;

    private final CasinoTableMigrate casinoTableMigrate;

    public MigrationModule(JavaMySql db) {
        this.db = db;
        this.casinoTableMigrate = new CasinoTableMigrate(db);
    }

    public void migrate() {
        this.casinoTableMigrate.initialize();
    }
}
