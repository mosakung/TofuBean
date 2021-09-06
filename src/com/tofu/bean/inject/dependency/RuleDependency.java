package com.tofu.bean.inject.dependency;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.rule.event.PlayerRuleModuleEvent;
import com.tofu.bean.presentation.PresentationPlugin;
import org.bukkit.Bukkit;

public class RuleDependency {

    private final PresentationPlugin presentationPlugin;
    private final PlayerBeansInteractor playerBeansInteractor;

    public RuleDependency(
            PresentationPlugin presentationPlugin,
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.presentationPlugin = presentationPlugin;
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void initialize() {
        // Event //
        Bukkit.getPluginManager().registerEvents(new PlayerRuleModuleEvent(playerBeansInteractor), presentationPlugin);
    }
}
