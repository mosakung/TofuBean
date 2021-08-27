package com.tofu.bean.plugin.money.executor;

import com.tofu.bean.plugin.money.domain.contract.PlayerMoneyInteractor;
import org.bukkit.entity.Player;

public record DecreasedMoneyValueExecutor(PlayerMoneyInteractor playerMoneyInteractor) {

    public void executor(Player player, Double value) {
        playerMoneyInteractor.decreasedValue(player.getName(), value);
    }
}
