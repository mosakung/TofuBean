package com.tofu.bean.data.methods.sign.branch;

import com.tofu.bean.data.methods.item.CustomMaterialItem;
import com.tofu.bean.data.methods.sign.ActionSign;

import static com.tofu.bean.data.methods.sign.ActionSign.BUY;
import static com.tofu.bean.data.methods.item.CustomMaterialItem.*;
import static com.tofu.bean.utils.SignMessageUtils.*;
import static com.tofu.bean.utils.parser.LocationParser.string2Double;


public enum BuySignBranch {
    BUY_NAME_TAG_1_5000(
            BUY,
            NAME_TAG,
            "1",
            "5000",
            "#1"
    ),
    BUY_SADDLE_1_7500(
            BUY,
            SADDLE,
            "1",
            "7500",
            "#2"
    ),
    BUY_ELYTRA_1_100000(
            BUY,
            ELYTRA,
            "1",
            "100000",
            "#3"
    ),
    BUY_ARROW_HEALING_64_1000(
            BUY,
            ARROW_HEALING,
            "64",
            "2000",
            "#4"
    ),
    BUY_ARROW_HARMING_64_1000(
            BUY,
            ARROW_HARMING,
            "64",
            "2000",
            "#5"
    ),
    BUY_ARROW_WEAKNESS_64_1000(
            BUY,
            ARROW_WEAKNESS,
            "64",
            "100",
            "#6"
    ),
    BUY_ARROW_SLOWNESS_64_1000(
            BUY,
            ARROW_SLOWNESS,
            "64",
            "700",
            "#7"
    ),
    BUY_FIREWORK_ROCKET_64_1000(
            BUY,
            FIREWORK_ROCKET,
            "64",
            "2000",
            "#8"
    );

    private final ActionSign actionType;
    private final CustomMaterialItem materialType;
    private final String amountMessage;
    private final String beansMessage;
    private final String tracking;

    private final String actionMessage;
    private final String itemMessage;

    BuySignBranch(
            ActionSign actionType,
            CustomMaterialItem materialType,
            String amountMessage,
            String beansMessage,
            String tracking
    ) {
        this.actionType = actionType;
        this.actionMessage = this.actionType.getActionMessage();
        this.materialType = materialType;
        this.itemMessage = signMessageItemLabelFormat(materialType.getItemName());
        this.amountMessage = signMessageAmountFormat(amountMessage);
        this.beansMessage = signMessageBeansFormat(beansMessage);
        this.tracking = tracking;
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public String getItemName() {
        return materialType.getItemName();
    }

    public CustomMaterialItem getMaterialType() {
        return this.materialType;
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

    public String getTracking() {
        return tracking;
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

        return string2Double(beans);
    }

    public Boolean equal(
            String itemLabel,
            String amount,
            String beans
    ) {
        return this.itemMessage.equals(itemLabel) && this.amountMessage.equals(amount) && this.beansMessage.equals(beans);
    }
}
