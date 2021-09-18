package com.tofu.bean.data.enums.sign.branch;

import com.tofu.bean.data.enums.item.CustomMaterialItem;
import com.tofu.bean.data.enums.sign.ActionSign;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static com.tofu.bean.data.enums.item.CustomMaterialItem.*;
import static com.tofu.bean.data.enums.sign.ActionSign.EXCHANGE;
import static com.tofu.bean.utils.sign.ExchangeSignMessageUtils.buildExchangeMessageRequire;
import static com.tofu.bean.utils.sign.ExchangeSignMessageUtils.buildExchangeMessageReward;
import static com.tofu.bean.utils.sign.SignMessageUtils.buildSignMessageRequireBeans;
import static org.bukkit.Material.*;

public enum ExchangeSignBranch {
    EXCHANGE_COD_16_TO_STRING_20(
            EXCHANGE,
            "Cod Fish",
            COD,
            16,
            CustomMaterialItem.STRING,
            20,
            0.0,
            "#1"
    ),
    EXCHANGE_SALMON_16_TO_STRING_20(
            EXCHANGE,
            "Salmon Fish",
            SALMON,
            16,
            CustomMaterialItem.STRING,
            20,
            0.0,
            "#2"
    ),
    EXCHANGE_BUCKET_1_TO_BUCKET_OF_TROPICAL_FISH_1_2500(
            EXCHANGE,
            "Bucket",
            BUCKET,
            1,
            BUCKET_OF_TROPICAL_FISH,
            1,
            1250.0,
            "#3"
    ),
    EXCHANGE_DIRT_64_TO_CLAY_64_1000(
            EXCHANGE,
            "Dirt",
            DIRT,
            64,
            CustomMaterialItem.CLAY_BALL,
            64,
            1000.0,
            "#4"
    ),
    EXCHANGE_PUFFER_FISH_16_TO_SPONGE_16_12500(
            EXCHANGE,
            "Puffer Fish",
            PUFFERFISH,
            16,
            CustomMaterialItem.SPONGE,
            16,
            12500.0,
            "#5"
    ),
    EXCHANGE_DRAGON_BREATH_1_TO_ARROW_HEALING_32_5000(
            EXCHANGE,
            "D Breath",
            Material.DRAGON_BREATH,
            1,
            ARROW_HEALING,
            32,
            5000.0,
            "#6"
    ),
    EXCHANGE_DRAGON_BREATH_1_TO_ARROW_HARMING_32_5000(
            EXCHANGE,
            "D Breath",
            Material.DRAGON_BREATH,
            1,
            ARROW_HARMING,
            32,
            5000.0,
            "#7"
    ),
    EXCHANGE_DRAGON_BREATH_1_TO_ARROW_WEAKNESS_32_4000(
            EXCHANGE,
            "D Breath",
            Material.DRAGON_BREATH,
            1,
            ARROW_WEAKNESS,
            32,
            4000.0,
            "#8"
    ),
    EXCHANGE_DRAGON_BREATH_1_TO_ARROW_SLOWNESS_32_3000(
            EXCHANGE,
            "D Breath",
            Material.DRAGON_BREATH,
            1,
            ARROW_SLOWNESS,
            32,
            3000.0,
            "#9"
    );

    private final ActionSign actionSign;
    private final String requireLabel;
    private final Material require;
    private final int amountRequire;
    private final CustomMaterialItem reward;
    private final int amountReward;
    private final Double beans;
    private final String tracking;

    private final String messageTopic;
    private final String messageRequire;
    private final String messageReward;
    private final String messageBeans;

    ExchangeSignBranch(
            ActionSign actionSign,
            String requireLabel,
            Material require,
            int amountRequire,
            CustomMaterialItem reward,
            int amountReward,
            Double beans,
            String tracking
    ) {
        this.actionSign = actionSign;
        this.requireLabel = requireLabel;
        this.require = require;
        this.amountRequire = amountRequire;
        this.reward = reward;
        this.amountReward = amountReward;
        this.beans = beans;
        this.tracking = tracking;

        this.messageTopic = actionSign.getActionMessage();
        this.messageRequire = buildExchangeMessageRequire(this.requireLabel, this.amountRequire);
        this.messageReward = buildExchangeMessageReward(this.reward, this.amountReward);
        this.messageBeans = buildSignMessageRequireBeans(this.beans);
    }

    public ActionSign getActionSign() {
        return actionSign;
    }

    public String getRequireLabel() {
        return requireLabel;
    }

    public Material getRequire() {
        return require;
    }

    public int getAmountRequire() {
        return amountRequire;
    }

    public CustomMaterialItem getReward() {
        return reward;
    }

    public int getAmountReward() {
        return amountReward;
    }

    public Double getBeans() {
        return beans;
    }

    public String getTracking() {
        return tracking;
    }

    public String getMessageTopic() {
        return messageTopic;
    }

    public String getMessageRequire() {
        return messageRequire;
    }

    public String getMessageReward() {
        return messageReward;
    }

    public String getMessageBeans() {
        return messageBeans;
    }

    public Boolean equal(
            String messageRequire,
            String messageReward,
            String messageBeans
    ) {
        return this.messageRequire.equals(messageRequire) && this.messageReward.equals(messageReward) && this.messageBeans.equals(messageBeans);
    }

    public static ExchangeSignBranch getExchangeSignBranch(
            String tracking
    ) {
        AtomicReference<ExchangeSignBranch> _exchangeSignBranch = new AtomicReference<>(null);

        Arrays.stream(ExchangeSignBranch.values()).forEach(exchangeSignBranch -> {

            if(exchangeSignBranch.getTracking().equals(tracking)) {
                _exchangeSignBranch.set(exchangeSignBranch);
            }
        });

        return _exchangeSignBranch.get();
    }

    public static ExchangeSignBranch getExchangeSignBranch(
            String messageRequire,
            String messageReward,
            String messageBeans
    ) {
        AtomicReference<ExchangeSignBranch> _exchangeSignBranch = new AtomicReference<>(null);

        Arrays.stream(ExchangeSignBranch.values()).forEach(exchangeSignBranch -> {

            if(exchangeSignBranch.equal(messageRequire, messageReward, messageBeans)) {
                _exchangeSignBranch.set(exchangeSignBranch);
            }
        });

        return _exchangeSignBranch.get();
    }
}
