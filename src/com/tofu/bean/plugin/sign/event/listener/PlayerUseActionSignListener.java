package com.tofu.bean.plugin.sign.event.listener;

import com.tofu.bean.data.enums.sign.branch.BuySignBranch;
import com.tofu.bean.data.enums.sign.branch.CasinoSignBranch;
import com.tofu.bean.data.enums.sign.branch.CookSignBranch;
import com.tofu.bean.data.enums.sign.branch.ExchangeSignBranch;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.casino.gacha.GachaSlotBranch;
import com.tofu.bean.plugin.sign.event.contract.SignActionCasino;
import com.tofu.bean.plugin.sign.event.contract.SignActionGachapon;
import com.tofu.bean.plugin.sign.event.impl.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.Objects;

import static com.tofu.bean.data.enums.sign.ActionSign.*;
import static com.tofu.bean.data.enums.sign.branch.CasinoSignBranch.getCasinoSignBranch;
import static com.tofu.bean.data.enums.sign.branch.ExchangeSignBranch.getExchangeSignBranch;
import static com.tofu.bean.utils.mapper.SignMapper.isSignMaterial;
import static com.tofu.bean.utils.sign.BuySignMessageUtils.revertBuySignMessageActionFormat;

public class PlayerUseActionSignListener {

    private final SignActionBuyImpl signActionBuyImpl;
    private final SignActionCookImpl signActionCookImpl;
    private final SignActionExchangeImpl signActionExchange;
    private final SignActionCasino signActionCasino;
    private final SignActionGachapon signActionGachapon;


    public PlayerUseActionSignListener(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.signActionBuyImpl = new SignActionBuyImpl(playerBeansInteractor);
        this.signActionCookImpl = new SignActionCookImpl(playerBeansInteractor);
        this.signActionExchange = new SignActionExchangeImpl(playerBeansInteractor);
        this.signActionCasino = new SignActionCasinoImpl();
        this.signActionGachapon = new SignActionGachaponImpl();
    }

    public void call(PlayerInteractEvent event) {

        Block block = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && isSignMaterial(Objects.requireNonNull(block).getType())) {

            if (block.getState() instanceof Sign sign) {
                Player player = event.getPlayer();
                String signLine1 = sign.getLine(0);

                if (signLine1.contains("§")) {

                    String actionFormat = revertBuySignMessageActionFormat(signLine1);

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

                    if (EXCHANGE.equal(actionFormat)) {
                        ExchangeSignBranch exchangeSignBranch = getExchangeSignBranch(sign.getLine(1), sign.getLine(2), sign.getLine(3));

                        if(exchangeSignBranch == null) {
                            return;
                        }

                        signActionExchange.action(player, exchangeSignBranch);
                    }

                    if (CASINO.equal(actionFormat)) {
                        CasinoSignBranch casinoSignBranch = getCasinoSignBranch(sign.getLine(1), sign.getLine(2), sign.getLine(3));

                        if(casinoSignBranch == null) {
                            return;
                        }

                        signActionCasino.action(player, casinoSignBranch);
                    }

                    if (GACHAPON.equal(actionFormat)) {
                        GachaSlotBranch gachaSlotBranch = GachaSlotBranch.find(sign.getLine(1), sign.getLine(2));

                        if (gachaSlotBranch == null) {
                            return;
                        }

                        signActionGachapon.action(player, gachaSlotBranch);
                    }
                }
            }
        }
    }
}
