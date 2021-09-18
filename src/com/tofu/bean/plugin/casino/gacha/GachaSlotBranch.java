package com.tofu.bean.plugin.casino.gacha;

import com.tofu.bean.data.enums.sign.ActionSign;
import com.tofu.bean.plugin.casino.gacha.contact.GachaBoardGUI;
import com.tofu.bean.plugin.casino.gacha.contact.GachaItemGenerate;
import com.tofu.bean.plugin.casino.gacha.impl.general.GachaGeneralGUI;
import com.tofu.bean.plugin.casino.gacha.impl.general.GachaGenerateGeneral;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static com.tofu.bean.data.enums.sign.ActionSign.GACHAPON;
import static com.tofu.bean.utils.sign.SignMessageUtils.buildSignMessageLabel;

public enum GachaSlotBranch {

    COUPON_GACHA_SLOT_NORMAL(
            "General Slot",
            10,
            "#general",
            new GachaGeneralGUI("General Slot", new GachaGenerateGeneral())
    );

    private final String name;
    private final Integer requireCoupon;
    private final String tracking;
    private final GachaBoardGUI gachaBoardGUI;

    private final ActionSign actionSign;
    private final String messageName;
    private final String messageRequireCoupon;
    private final String messageCoupon;

    GachaSlotBranch(
            String name,
            Integer requireCoupon,
            String tracking,
            GachaBoardGUI gachaBoardGUI
    ) {
        this.name = name;
        this.requireCoupon = requireCoupon;
        this.tracking = tracking;
        this.gachaBoardGUI = gachaBoardGUI;

        this.actionSign = GACHAPON;
        this.messageName = buildSignMessageLabel(name);
        this.messageRequireCoupon = ChatColor.GREEN + "" + requireCoupon;
        this.messageCoupon = ChatColor.GOLD + "Coupon";
    }

    public String getName() {
        return name;
    }

    public Integer getRequireCoupon() {
        return requireCoupon;
    }

    public String getTracking() {
        return tracking;
    }

    public GachaBoardGUI getGachaBoardGUI() {
        return gachaBoardGUI;
    }

    public ActionSign getActionSign() {
        return actionSign;
    }

    public String getMessageName() {
        return messageName;
    }

    public String getMessageRequireCoupon() {
        return messageRequireCoupon;
    }

    public String getMessageCoupon() {
        return messageCoupon;
    }

    public Boolean equal(String messageName, String messageRequireCoupon) {
        return this.messageName.equals(messageName) && this.messageRequireCoupon.equals(messageRequireCoupon);
    }

    public Boolean equalTracking(String tracking) {
        return this.tracking.equals(tracking);
    }

    public static GachaSlotBranch find(String messageName, String messageRequireCoupon) {
        AtomicReference<GachaSlotBranch> result = new AtomicReference<>(null);

        Arrays.stream(GachaSlotBranch.values()).forEach(gachaSlotBranch -> {
            if(gachaSlotBranch.equal(messageName, messageRequireCoupon)) {
                result.set(gachaSlotBranch);
            }
        });

        return result.get();
    }

    public static GachaSlotBranch findByTracking(String tracking) {
        AtomicReference<GachaSlotBranch> result = new AtomicReference<>(null);

        Arrays.stream(GachaSlotBranch.values()).forEach(gachaSlotBranch -> {
            if(gachaSlotBranch.equalTracking(tracking)) {
                result.set(gachaSlotBranch);
            }
        });

        return result.get();
    }
}
