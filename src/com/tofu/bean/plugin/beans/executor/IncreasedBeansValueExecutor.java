package com.tofu.bean.plugin.beans.executor;

import com.tofu.bean.plugin.beans.domain.contract.PlayerBeansInteractor;
import org.bukkit.entity.Player;

public record IncreasedBeansValueExecutor(PlayerBeansInteractor playerBeansInteractor) {

    public void executor(Player player, Double value) {
        playerBeansInteractor.increasedValue(player.getName(), value);
    }
}
