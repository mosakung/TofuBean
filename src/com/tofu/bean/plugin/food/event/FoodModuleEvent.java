package com.tofu.bean.plugin.food.event;

import com.tofu.bean.plugin.food.event.listener.PlayerEatCustomFoodListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class FoodModuleEvent implements Listener {

    private final PlayerEatCustomFoodListener playerEatCustomFoodListener;

    public FoodModuleEvent() {
        this.playerEatCustomFoodListener = new PlayerEatCustomFoodListener();
    }

    @EventHandler
    public void providePlayerEatCustomFoodListener(PlayerItemConsumeEvent event) {
        playerEatCustomFoodListener.call(event);
    }
}
