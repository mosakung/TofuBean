package com.tofu.bean.data.sign;

import static com.tofu.bean.utils.SignMessageUtils.signMessageActionFormat;

public enum SignActionMethod {
    BUY("buy"),
    SELL("sell"),
    CASINO("casino")
    ;

    private final String actionType;
    private final String actionMessage;

    SignActionMethod(
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
