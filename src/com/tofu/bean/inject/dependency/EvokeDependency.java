package com.tofu.bean.inject.dependency;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.beans.command.BeansModuleCommand;
import com.tofu.bean.plugin.beans.command.BeansTabCompleter;
import com.tofu.bean.plugin.evoke.command.EvokeModuleCommnd;
import com.tofu.bean.plugin.evoke.command.EvokeTabCompleter;
import com.tofu.bean.presentation.PresentationPlugin;

import java.util.Objects;

public class EvokeDependency {

    private final PresentationPlugin presentationPlugin;
    private final PlayerBeansInteractor playerBeansInteractor;

    public EvokeDependency(
            PresentationPlugin presentationPlugin,
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.presentationPlugin = presentationPlugin;
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void initialize() {
        // Command //
        Objects.requireNonNull(presentationPlugin.getCommand("evoke")).setExecutor(new EvokeModuleCommnd(playerBeansInteractor));
        Objects.requireNonNull(presentationPlugin.getCommand("evoke")).setTabCompleter(new EvokeTabCompleter());
    }
}
