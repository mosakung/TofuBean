package com.tofu.bean.domain.impl;

import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;

public class InteractorTemplate {

    private final JavaMySql db;

    public InteractorTemplate(JavaMySql db) {
        this.db = db;
    }

}
