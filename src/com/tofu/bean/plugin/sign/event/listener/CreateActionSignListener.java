package com.tofu.bean.plugin.sign.event.listener;

import com.tofu.bean.data.enums.PermissionMethod;
import com.tofu.bean.data.enums.sign.branch.BuySignBranch;
import com.tofu.bean.data.enums.sign.branch.CasinoSignBranch;
import com.tofu.bean.data.enums.sign.branch.CookSignBranch;
import com.tofu.bean.data.enums.sign.branch.ExchangeSignBranch;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Arrays;
import java.util.Objects;

import static com.tofu.bean.data.enums.sign.ActionSign.CASINO;
import static com.tofu.bean.data.enums.sign.branch.CasinoSignBranch.getCasinoSignBranch;
import static com.tofu.bean.data.enums.sign.branch.ExchangeSignBranch.getExchangeSignBranch;
import static com.tofu.bean.utils.sign.BuySignMessageUtils.buySignMessageBeansFormat;
import static com.tofu.bean.utils.parser.LocationParser.string2Double;

public class CreateActionSignListener {

    public void call(SignChangeEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission(PermissionMethod.DEVELOPER.getPermission())) {

            String command = event.getLine(0);
            String tracking = event.getLine(1);

            if (Objects.requireNonNull(command).equals("/dev buy")) {

                Arrays.stream(BuySignBranch.values()).forEach(buySignBranch -> {
                    if (Objects.requireNonNull(tracking).equals(buySignBranch.getTracking())) {
                        event.setLine(0, buySignBranch.getActionMessage());
                        event.setLine(1, buySignBranch.getItemMessage());
                        event.setLine(2, buySignBranch.getAmountMessage());
                        event.setLine(3, buySignBranch.getBeansMessage());
                    }
                });
                return;
            }

            if (Objects.requireNonNull(command).equals("/dev cook")) {
                Arrays.stream(CookSignBranch.values()).forEach(cookSignBranch -> {
                    if (Objects.requireNonNull(tracking).equals(cookSignBranch.getTracking())) {
                        event.setLine(0, cookSignBranch.getActionMessage());
                        event.setLine(1, cookSignBranch.getItemMessage());
                        event.setLine(2, cookSignBranch.getAmountMessage());
                        event.setLine(3, cookSignBranch.getBeansMessage());
                    }
                });
                return;
            }

            if (Objects.requireNonNull(command).equals("/dev exchange")) {
                ExchangeSignBranch exchangeSignBranch = getExchangeSignBranch(tracking);

                if(exchangeSignBranch == null) {
                    return;
                }

                event.setLine(0, exchangeSignBranch.getMessageTopic());
                event.setLine(1, exchangeSignBranch.getMessageRequire());
                event.setLine(2, exchangeSignBranch.getMessageReward());
                event.setLine(3, exchangeSignBranch.getMessageBeans());
                return;
            }

            if (Objects.requireNonNull(command).equals("/dev casino")) {
                CasinoSignBranch casinoSignBranch = getCasinoSignBranch(tracking);

                if(casinoSignBranch == null) {
                    return;
                }

                event.setLine(0, casinoSignBranch.getMessageTopic());
                event.setLine(1, casinoSignBranch.getMessageTableNameLabel());
                event.setLine(2, casinoSignBranch.getMessageRule());
                event.setLine(3, casinoSignBranch.getMessageBet());
            }
        }
    }
}
