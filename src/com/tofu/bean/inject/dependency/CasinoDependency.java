package com.tofu.bean.inject.dependency;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.casino.blackjack.event.CasinoModuleEvent;
import com.tofu.bean.plugin.casino.coupon.CouponManagement;
import com.tofu.bean.presentation.PresentationPlugin;
import org.bukkit.Bukkit;

public class CasinoDependency {

    private final PresentationPlugin presentationPlugin;
    private final PlayerBeansInteractor playerBeansInteractor;

    public CasinoDependency(
            PresentationPlugin presentationPlugin,
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.presentationPlugin = presentationPlugin;
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void initialize() {
        Bukkit.getPluginManager().registerEvents(new CasinoModuleEvent(playerBeansInteractor), presentationPlugin);
    }
}
