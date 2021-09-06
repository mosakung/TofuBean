package com.tofu.bean.inject.dependency;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.beans.command.BeansModuleCommand;
import com.tofu.bean.plugin.beans.command.BeansTabCompleter;
import com.tofu.bean.presentation.PresentationPlugin;

import java.util.Objects;

public class BeansDependency {

    private final PresentationPlugin presentationPlugin;
    private final PlayerBeansInteractor playerBeansInteractor;

    public BeansDependency(
            PresentationPlugin presentationPlugin,
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.presentationPlugin = presentationPlugin;
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void initialize() {
        // Command //
        Objects.requireNonNull(presentationPlugin.getCommand("beans")).setExecutor(new BeansModuleCommand(playerBeansInteractor));
        Objects.requireNonNull(presentationPlugin.getCommand("beans")).setTabCompleter(new BeansTabCompleter());
    }
}
