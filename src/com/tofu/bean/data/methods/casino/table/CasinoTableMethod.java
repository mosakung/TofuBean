package com.tofu.bean.data.methods.casino.table;

public enum CasinoTableMethod {
    DUMMY("dummy"),
    LOBBY("lobby"),
    BLACK_JACK("black_jack");

    private final String type;

    CasinoTableMethod(
            String type
    ) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
