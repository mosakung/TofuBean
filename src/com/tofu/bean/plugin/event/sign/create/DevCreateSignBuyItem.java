package com.tofu.bean.plugin.event.sign.create;

import com.tofu.bean.data.PermissionMethod;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Objects;

import static com.tofu.bean.data.sign.SignBuyItemMethod.BUY_NAME_TAG_1_5000;

public class DevCreateSignBuyItem {

    public void call(SignChangeEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission(PermissionMethod.DEVELOPER.getPermission())) {

            String command = event.getLine(0);
            String call = event.getLine(1);

            if(Objects.requireNonNull(command).equals("/develop")) {

                if(call.equals(BUY_NAME_TAG_1_5000.getFlag2call())) {
                    event.setLine(0, BUY_NAME_TAG_1_5000.getActionMessage());
                    event.setLine(1, BUY_NAME_TAG_1_5000.getItemMessage());
                    event.setLine(2, BUY_NAME_TAG_1_5000.getAmountMessage());
                    event.setLine(3, BUY_NAME_TAG_1_5000.getBeansMessage());
                }
            }
        }
    }
}
