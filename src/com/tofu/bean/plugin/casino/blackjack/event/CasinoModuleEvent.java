package com.tofu.bean.plugin.casino.blackjack.event;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.casino.blackjack.action.BlackJackActionGame;
import com.tofu.bean.plugin.casino.blackjack.action.BlackJackDeckAction;
import com.tofu.bean.plugin.casino.blackjack.event.listener.PlayerPlayBoardGameListener;
import com.tofu.bean.plugin.casino.coupon.CouponManagement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CasinoModuleEvent implements Listener {

    private final BlackJackDeckAction blackJackDeckAction;
    private final BlackJackActionGame blackJackActionGame;
    private final PlayerPlayBoardGameListener playerPlayBoardGameListener;

    public CasinoModuleEvent(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.blackJackDeckAction = new BlackJackDeckAction();
        this.blackJackActionGame = new BlackJackActionGame(blackJackDeckAction);
        this.playerPlayBoardGameListener = new PlayerPlayBoardGameListener(blackJackActionGame, playerBeansInteractor);
    }

    @EventHandler
    public void providePlayerPlayBoardGameListener(InventoryClickEvent event) {
        playerPlayBoardGameListener.call(event);
    }
}
