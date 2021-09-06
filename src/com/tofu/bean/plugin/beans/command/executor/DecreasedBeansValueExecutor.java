package com.tofu.bean.plugin.beans.command.executor;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import org.bukkit.entity.Player;

public record DecreasedBeansValueExecutor(PlayerBeansInteractor playerBeansInteractor) {

    public void executor(Player player, Double value) {
        playerBeansInteractor.decreasedValue(player.getName(), value);
    }
}
