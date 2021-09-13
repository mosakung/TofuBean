package com.tofu.bean.data.enums.sign.branch;

import com.tofu.bean.data.enums.item.CustomMaterialItem;
import com.tofu.bean.data.enums.sign.ActionSign;
import org.bukkit.Material;

import static com.tofu.bean.data.enums.sign.ActionSign.COOK;
import static com.tofu.bean.data.enums.item.CustomMaterialItem.*;
import static com.tofu.bean.utils.sign.BuySignMessageUtils.*;
import static com.tofu.bean.utils.parser.LocationParser.string2Double;

public enum CookSignBranch {
    COOK_BREAD_16_TO_BREAD_SMALL_FAST_FOOD_16_250(
            COOK,
            BREAD_SMALL_FAST_FOOD,
            "16",
            "300",
            "#1",
            Material.BREAD,
            16
    ),
    COOK_APPLE_1_TO_LUCKY_APPLE_1_250(
            COOK,
            LUCKY_APPLE_FOOD,
            "1",
            "500",
            "#2",
            Material.APPLE,
            1
    );

    private final ActionSign actionType;
    private final CustomMaterialItem materialType;
    private final String amountMessage;
    private final String beansMessage;
    private final String tracking;
    private final Material exchangeItem;
    private final int amountExchange;

    private final String actionMessage;
    private final String itemMessage;

    CookSignBranch(
            ActionSign actionType,
            CustomMaterialItem materialType,
            String amountMessage,
            String beansMessage,
            String tracking,
            Material exchangeItem,
            int amountExchange
    ) {
        this.actionType = actionType;
        this.actionMessage = this.actionType.getActionMessage();
        this.materialType = materialType;
        this.itemMessage = buySignMessageItemLabelFormat(materialType.getItemName());
        this.amountMessage = buySignMessageAmountFormat(amountMessage);
        this.beansMessage = buySignMessageBeansFormat(beansMessage);
        this.tracking = tracking;
        this.exchangeItem = exchangeItem;
        this.amountExchange = amountExchange;
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

    public Material getExchangeItem() {
        return exchangeItem;
    }

    public int getAmountExchange() {
        return amountExchange;
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
