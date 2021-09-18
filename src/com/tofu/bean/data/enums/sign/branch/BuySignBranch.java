package com.tofu.bean.data.enums.sign.branch;

import com.tofu.bean.data.enums.item.CustomMaterialItem;
import com.tofu.bean.data.enums.sign.ActionSign;

import static com.tofu.bean.data.enums.sign.ActionSign.BUY;
import static com.tofu.bean.data.enums.item.CustomMaterialItem.*;
import static com.tofu.bean.utils.sign.BuySignMessageUtils.*;
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
    BUY_ARROW_HEALING_16_2500(
            BUY,
            ARROW_HEALING,
            "16",
            "2500",
            "#4@"
    ),
    BUY_ARROW_HARMING_16_2500(
            BUY,
            ARROW_HARMING,
            "16",
            "2500",
            "#5@"
    ),
    BUY_ARROW_WEAKNESS_16_2500(
            BUY,
            ARROW_WEAKNESS,
            "16",
            "2500",
            "#6@"
    ),
    BUY_ARROW_SLOWNESS_16_2500(
            BUY,
            ARROW_SLOWNESS,
            "16",
            "2500",
            "#7@"
    ),
    BUY_FIREWORK_ROCKET_64_1000(
            BUY,
            FIREWORK_ROCKET,
            "64",
            "2000",
            "#8"
    ),
    BUY_APPLE_16_300(
            BUY,
            APPLE,
            "16",
            "480",
            "#9"
    ),
    DRAGON_BREATH_1_500(
            BUY,
            DRAGON_BREATH,
            "1",
            "250",
            "#10"
    ),
    DEBUG(
            BUY,
            CASINO_COUPON,
            "10",
            "0",
            "debug"
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
        this.itemMessage = buySignMessageItemLabelFormat(materialType.getItemName());
        this.amountMessage = buySignMessageAmountFormat(amountMessage);
        this.beansMessage = buySignMessageBeansFormat(beansMessage);
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
        String amount = revertBuySignMessageAmountFormat(this.amountMessage);

        if (amount == null) {
            return -1;
        }

        return Integer.parseInt(amount);
    }

    public Double getBeansValue() {
        String beans = revertBuySignMessageBeansFormat(this.beansMessage);

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
