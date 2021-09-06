package com.tofu.bean.inject.dependency;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.food.event.FoodModuleEvent;
import com.tofu.bean.presentation.PresentationPlugin;
import org.bukkit.Bukkit;

public class FoodDependency {

    private final PresentationPlugin presentationPlugin;

    public FoodDependency(
            PresentationPlugin presentationPlugin
    ) {
        this.presentationPlugin = presentationPlugin;
    }

    public void initialize() {
        // Event //
        Bukkit.getPluginManager().registerEvents(new FoodModuleEvent(), presentationPlugin);
    }
}
