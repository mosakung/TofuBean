package com.tofu.bean.plugin.money.executor;

import com.tofu.bean.plugin.money.domain.contract.PlayerMoneyInteractor;
import org.bukkit.entity.Player;

public record IncreasedMoneyValueExecutor(PlayerMoneyInteractor playerMoneyInteractor) {

    public void executor(Player player, Double value) {
        playerMoneyInteractor.increasedValue(player.getName(), value);
    }
}
