package com.tofu.bean.plugin.sign.event.listener;

import com.tofu.bean.data.methods.PermissionMethod;
import com.tofu.bean.data.methods.sign.branch.BuySignBranch;
import com.tofu.bean.data.methods.sign.branch.CasinoSignBranch;
import com.tofu.bean.data.methods.sign.branch.CookSignBranch;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Arrays;
import java.util.Objects;

import static com.tofu.bean.data.methods.sign.ActionSign.CASINO;
import static com.tofu.bean.utils.SignMessageUtils.signMessageBeansFormat;
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
            }

            if (Objects.requireNonNull(command).equals("/dev casino")) {
                String betString = event.getLine(2);

                Double bet = string2Double(Objects.requireNonNull(betString));

                if (bet == null) {
                    return;
                }

                Arrays.stream(CasinoSignBranch.values()).forEach(signCasinoTableMethod -> {
                    if (Objects.requireNonNull(tracking).equals(signCasinoTableMethod.getTracking())) {
                        event.setLine(0, CASINO.getActionMessage());
                        event.setLine(1, signCasinoTableMethod.getTableNameMessage());
                        event.setLine(2, signMessageBeansFormat(betString));
                    }
                });
            }
        }
    }
}
