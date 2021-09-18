package com.tofu.bean.data.enums.sign;

import static com.tofu.bean.utils.sign.SignMessageUtils.buildSignMessageActionFormat;

public enum ActionSign {
    BUY("buy"),
    SELL("sell"),
    COOK("cook"),
    EXCHANGE("exchange"),
    CASINO("casino"),
    GACHAPON("gachapon")
    ;

    private final String actionType;
    private final String actionMessage;

    ActionSign(
            String actionMessage
    ) {
        this.actionType = actionMessage;
        this.actionMessage = buildSignMessageActionFormat(actionMessage);
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public Boolean equal(String action) {
        return action.equals(actionType);
    }
}
