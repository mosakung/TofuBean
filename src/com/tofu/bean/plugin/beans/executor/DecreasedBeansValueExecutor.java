package com.tofu.bean.plugin.beans.executor;

import com.tofu.bean.plugin.beans.domain.contract.PlayerBeansInteractor;
import org.bukkit.entity.Player;

public record DecreasedBeansValueExecutor(PlayerBeansInteractor playerBeansInteractor) {

    public void executor(Player player, Double value) {
        playerBeansInteractor.decreasedValue(player.getName(), value);
    }
}
