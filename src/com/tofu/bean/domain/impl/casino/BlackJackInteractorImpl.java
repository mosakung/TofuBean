package com.tofu.bean.domain.impl.casino;

import com.tofu.bean.domain.contract.casino.BlackJackInteractor;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;

public class BlackJackInteractorImpl extends CasinoInteractorImpl implements BlackJackInteractor {

    public BlackJackInteractorImpl(JavaMySql db) {
        super(db);
    }


    @Override
    public void startGame() {

    }
}
