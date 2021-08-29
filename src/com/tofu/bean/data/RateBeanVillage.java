package com.tofu.bean.data;

public enum RateBeanVillage {
    RATE_TELEPORT_TO_PLAYER(3.0),
    RATE_TELEPORT_TO_SPAWN(2.5);

    private Double rate;

    RateBeanVillage(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }
}
