package com.tofu.bean.inject.dependency;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.domain.contract.jetbean.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.jetbean.SpawnJetBeanInteractor;
import com.tofu.bean.plugin.jetbean.command.JetBeanModuleCommand;
import com.tofu.bean.plugin.jetbean.command.SpawnJetBeanModuleCommand;
import com.tofu.bean.plugin.jetbean.command.suggestion.JetBeanTabCompleter;
import com.tofu.bean.plugin.jetbean.command.suggestion.SpawnJetBeanTabcompleter;
import com.tofu.bean.plugin.jetbean.event.JetBeanModuleEvent;
import com.tofu.bean.presentation.PresentationPlugin;
import org.bukkit.Bukkit;

import java.util.Objects;

public class JetBeanDependency {

    private final PresentationPlugin presentationPlugin;
    private final PlayerBeansInteractor playerBeansInteractor;
    private final SpawnJetBeanInteractor spawnJetBeanInteractor;
    private final DeadJetBeanInteractor deadJetBeanInteractor;

    public JetBeanDependency(
            PresentationPlugin presentationPlugin,
            PlayerBeansInteractor playerBeansInteractor,
            SpawnJetBeanInteractor spawnJetBeanInteractor,
            DeadJetBeanInteractor deadJetBeanInteractor
    ) {
        this.presentationPlugin = presentationPlugin;
        this.playerBeansInteractor = playerBeansInteractor;
        this.spawnJetBeanInteractor = spawnJetBeanInteractor;
        this.deadJetBeanInteractor = deadJetBeanInteractor;
    }

    public void initialize() {
        // Command //
        Objects.requireNonNull(presentationPlugin.getCommand("jetbean")).setExecutor(new JetBeanModuleCommand(playerBeansInteractor, deadJetBeanInteractor));
        Objects.requireNonNull(presentationPlugin.getCommand("jetbean")).setTabCompleter(new JetBeanTabCompleter());

        Objects.requireNonNull(presentationPlugin.getCommand("spawn")).setExecutor(new SpawnJetBeanModuleCommand(playerBeansInteractor, spawnJetBeanInteractor));
        Objects.requireNonNull(presentationPlugin.getCommand("spawn")).setTabCompleter(new SpawnJetBeanTabcompleter());

        // Event //
        Bukkit.getPluginManager().registerEvents(new JetBeanModuleEvent(deadJetBeanInteractor), presentationPlugin);
    }
}
