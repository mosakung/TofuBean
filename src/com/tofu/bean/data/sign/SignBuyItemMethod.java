package com.tofu.bean.data.sign;

import org.bukkit.Material;

import static com.tofu.bean.data.sign.SignActionMethod.BUY;
import static com.tofu.bean.data.sign.SignNameMaterial.NAME_TAG;
import static com.tofu.bean.utils.SignMessageUtils.*;


public enum SignBuyItemMethod {
    BUY_NAME_TAG_1_5000(
            BUY,
            NAME_TAG,
            "1",
            "5000",
            "buy:#1"
    );

    private final SignActionMethod actionType;
    private final String actionMessage;
    private final SignNameMaterial materialType;
    private final String itemMessage;
    private final String amountMessage;
    private final String beansMessage;
    private final String flag2call;

    SignBuyItemMethod(
            SignActionMethod actionType,
            SignNameMaterial materialType,
            String amountMessage,
            String beansMessage,
            String flag2call
    ) {
        this.actionType = actionType;
        this.actionMessage = actionType.getActionMessage();
        this.materialType = materialType;
        this.itemMessage = signMessageItemLabelFormat(materialType.getItemName());
        this.amountMessage = signMessageAmountFormat(amountMessage);
        this.beansMessage = signMessageBeansFormat(beansMessage);
        this.flag2call = flag2call;
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public String getItemName() {
        return materialType.getItemName();
    }

    public Material getMaterial() {
        return materialType.getMaterial();
    }

    public String getItemMessage() {
        return itemMessage;
    }

    public String getAmountMessage() {
        return amountMessage;
    }

    public String getBeansMessage() {
        return beansMessage;
    }

    public String getFlag2call() {
        return flag2call;
    }

    public int getAmountValue() {
        String amount = revertSignMessageAmountFormat(this.amountMessage);

        if (amount == null) {
            return -1;
        }

        return Integer.parseInt(amount);
    }

    public Double getBeansValue() {
        String beans = revertSignMessageBeansFormat(this.beansMessage);

        if (beans == null) {
            return null;
        }

        return Double.parseDouble(beans);
    }

    public Boolean equal(
            String itemLabel,
            String amount,
            String beans
    ) {
        return this.itemMessage.equals(itemLabel) && this.amountMessage.equals(amount) && this.beansMessage.equals(beans);
    }
}
