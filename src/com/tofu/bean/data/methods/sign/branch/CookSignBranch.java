package com.tofu.bean.data.methods.sign.branch;

import com.tofu.bean.data.methods.item.CustomMaterialItem;
import com.tofu.bean.data.methods.sign.ActionSign;
import org.bukkit.Material;

import static com.tofu.bean.data.methods.sign.ActionSign.COOK;
import static com.tofu.bean.data.methods.item.CustomMaterialItem.*;
import static com.tofu.bean.utils.SignMessageUtils.*;
import static com.tofu.bean.utils.parser.LocationParser.string2Double;

public enum CookSignBranch {
    EXCHANGE_BREAD_16_TO_BREAD_SMALL_FAST_FOOD_16_250(
            COOK,
            BREAD_SMALL_FAST_FOOD,
            "16",
            "250",
            "#1",
            Material.BREAD,
            16
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
        this.itemMessage = signMessageItemLabelFormat(materialType.getItemName());
        this.amountMessage = signMessageAmountFormat(amountMessage);
        this.beansMessage = signMessageBeansFormat(beansMessage);
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
