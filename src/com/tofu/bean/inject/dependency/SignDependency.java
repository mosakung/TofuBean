package com.tofu.bean.inject.dependency;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.sign.event.SignModuleEvent;
import com.tofu.bean.presentation.PresentationPlugin;
import org.bukkit.Bukkit;

public class SignDependency {

    private final PresentationPlugin presentationPlugin;
    private final PlayerBeansInteractor playerBeansInteractor;

    public SignDependency(
            PresentationPlugin presentationPlugin,
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.presentationPlugin = presentationPlugin;
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void initialize() {
        // Event //
        Bukkit.getPluginManager().registerEvents(new SignModuleEvent(playerBeansInteractor), presentationPlugin);
    }
}
