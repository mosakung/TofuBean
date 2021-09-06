package com.tofu.bean.data.methods.sign;

import static com.tofu.bean.utils.SignMessageUtils.signMessageActionFormat;

public enum ActionSign {
    BUY("buy"),
    SELL("sell"),
    COOK("cook"),
    CASINO("casino")
    ;

    private final String actionType;
    private final String actionMessage;

    ActionSign(
            String actionMessage
    ) {
        this.actionType = actionMessage;
        this.actionMessage = signMessageActionFormat(actionMessage);
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public Boolean equal(String action) {
        return action.equals(actionType);
    }
}
