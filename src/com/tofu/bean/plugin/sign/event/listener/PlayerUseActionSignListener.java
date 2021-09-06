package com.tofu.bean.plugin.sign.event.listener;

import com.tofu.bean.data.methods.sign.branch.BuySignBranch;
import com.tofu.bean.data.methods.sign.branch.CasinoSignBranch;
import com.tofu.bean.data.methods.sign.branch.CookSignBranch;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.casino.PlayerUseBlackJackSign;
import com.tofu.bean.plugin.sign.event.impl.SignActionCookImpl;
import com.tofu.bean.plugin.sign.event.impl.SignActionBuyImpl;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.Objects;

import static com.tofu.bean.data.methods.sign.ActionSign.*;
import static com.tofu.bean.utils.SignMessageUtils.revertSignMessageActionFormat;
import static com.tofu.bean.utils.SignMessageUtils.revertSignMessageBeansFormat;
import static com.tofu.bean.utils.mapper.SignMapper.isSignMaterial;
import static com.tofu.bean.utils.parser.LocationParser.string2Double;

public class PlayerUseActionSignListener {

    private final SignActionBuyImpl signActionBuyImpl;
    private final PlayerUseBlackJackSign playerUseBlackJackSign;
    private final SignActionCookImpl signActionCookImpl;

    public PlayerUseActionSignListener(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.signActionBuyImpl = new SignActionBuyImpl(playerBeansInteractor);
        this.playerUseBlackJackSign = new PlayerUseBlackJackSign();
        this.signActionCookImpl = new SignActionCookImpl(playerBeansInteractor);
    }

    public void call(PlayerInteractEvent event) {

        Block block = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && isSignMaterial(Objects.requireNonNull(block).getType())) {

            if (block.getState() instanceof Sign sign) {
                Player player = event.getPlayer();
                String signLine1 = sign.getLine(0);

                if (signLine1.contains("§")) {

                    String actionFormat = revertSignMessageActionFormat(signLine1);

                    if (actionFormat == null) {
                        return;
                    }

                    if (BUY.equal(actionFormat)) {
                        Arrays.stream(BuySignBranch.values()).forEach(buySignBranch -> {

                            if (buySignBranch.equal(sign.getLine(1), sign.getLine(2), sign.getLine(3))) {
                                signActionBuyImpl.action(player, buySignBranch);
                            }
                        });
                    }

                    if (COOK.equal(actionFormat)) {
                        Arrays.stream(CookSignBranch.values()).forEach(cookSignBranch -> {

                            if (cookSignBranch.equal(sign.getLine(1), sign.getLine(2), sign.getLine(3))) {
                                signActionCookImpl.action(player, cookSignBranch);
                            }
                        });
                    }

                    if (CASINO.equal(actionFormat)) {
                        Arrays.stream(CasinoSignBranch.values()).forEach(signCasinoTableMethod -> {

                            if (signCasinoTableMethod.equalTableNameMessage(sign.getLine(1))) {
                                String rawBet = revertSignMessageBeansFormat(sign.getLine(2));

                                Double bet = string2Double(rawBet);

                                if(bet == null) {
                                    return;
                                }

                                playerUseBlackJackSign.actionSit(player, signCasinoTableMethod, bet);
                            }
                        });
                    }
                }
            }
        }
    }
}
