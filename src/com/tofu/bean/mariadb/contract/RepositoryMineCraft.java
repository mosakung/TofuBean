package com.tofu.bean.mariadb.contract;

import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;

public interface RepositoryMineCraft {
    JavaMySql dbProvider();
}
