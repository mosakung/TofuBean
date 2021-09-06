package com.tofu.bean.plugin.beans.command.executor;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import org.bukkit.entity.Player;

public record IncreasedBeansValueExecutor(PlayerBeansInteractor playerBeansInteractor) {

    public void executor(Player player, Double value) {
        playerBeansInteractor.increasedValue(player.getName(), value);
    }
}
