package com.tofu.bean.data;

public enum CostRateBeansMethod {
    RATE_TELEPORT_TO_PLAYER(3.0),
    RATE_TELEPORT_TO_SPAWN(2.5),
    RATE_TELEPORT_TO_DEAD_LOCATION(30.0);

    private Double rate;

    CostRateBeansMethod(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }
}
