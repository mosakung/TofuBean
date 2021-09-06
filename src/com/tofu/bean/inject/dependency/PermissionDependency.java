package com.tofu.bean.inject.dependency;

import com.tofu.bean.plugin.permission.command.BeanPermissionModuleCommand;
import com.tofu.bean.plugin.permission.command.BeanPermissionTabCompleter;
import com.tofu.bean.plugin.permission.event.PermissionModuleEvent;
import com.tofu.bean.presentation.PresentationPlugin;
import org.bukkit.Bukkit;

import java.util.Objects;

public class PermissionDependency {

    private final PresentationPlugin presentationPlugin;

    public PermissionDependency(
            PresentationPlugin presentationPlugin
    ) {
        this.presentationPlugin = presentationPlugin;
    }

    public void initialize() {
        // Command //
        Objects.requireNonNull(presentationPlugin.getCommand("tofu")).setExecutor(new BeanPermissionModuleCommand());
        Objects.requireNonNull(presentationPlugin.getCommand("tofu")).setTabCompleter(new BeanPermissionTabCompleter());

        // Event //
        Bukkit.getPluginManager().registerEvents(new PermissionModuleEvent(), presentationPlugin);
    }
}
