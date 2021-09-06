package com.tofu.bean.data.methods.sign.branch;

import com.tofu.bean.data.methods.casino.table.CasinoTableMethod;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static com.tofu.bean.data.methods.casino.table.CasinoTableMethod.*;
import static com.tofu.bean.utils.SignMessageUtils.signMessageItemLabelFormat;

public enum CasinoSignBranch {

    // Auto Migrate //

    DUMMY_MIGRATE(
            "dummy_migrate",
            "dummy_migrate",
            DUMMY,
            1,
            "null:dummy"
    ),
    LOBBY_TABLE(
            "lobby",
            "lobby",
            LOBBY,
            100,
            "null:lobby"
    ),
    BLACK_JACK_TABLE_1(
            "black_jack_table_1",
            "Black Jack 21",
            BLACK_JACK,
            5,
            "blackjack:#1"
    );

    private final String name;
    private final String tableNameMessage;
    private final CasinoTableMethod type;
    private final int maxPlayer;
    private final String tracking;

    CasinoSignBranch(
            String name,
            String messageNameView,
            CasinoTableMethod type,
            int maxPlayer,
            String tracking
    ) {
        this.name = name;
        this.tableNameMessage = signMessageItemLabelFormat(messageNameView);
        this.type = type;
        this.maxPlayer = maxPlayer;
        this.tracking = tracking;
    }

    public String getName() {
        return name;
    }

    public String getTableNameMessage() {
        return tableNameMessage;
    }

    public CasinoTableMethod getType() {
        return type;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public String getTracking() {
        return tracking;
    }

    public Boolean equalTableNameMessage(String tableNameMessage) {
        return this.tableNameMessage.equals(tableNameMessage);
    }

    public Boolean equalTableName(String name) {
        return this.name.equals(name);
    }

    public static CasinoSignBranch getTableMethod(String name) {
        AtomicReference<CasinoSignBranch> result = new AtomicReference<>(null);

        Arrays.stream(CasinoSignBranch.values()).forEach(signCasinoTableMethod -> {

            if(signCasinoTableMethod.equalTableName(name)) {
                result.set(signCasinoTableMethod);
            }
        });

        return result.get();
    }
}
