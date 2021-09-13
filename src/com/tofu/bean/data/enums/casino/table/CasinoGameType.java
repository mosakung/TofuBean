package com.tofu.bean.data.enums.casino.table;

public enum CasinoGameType {
    BLACK_JACK("black_jack");

    private final String type;

    CasinoGameType(
            String type
    ) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
