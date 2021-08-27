package com.tofu.bean.plugin.money.executor;

import com.tofu.bean.plugin.money.domain.contract.PlayerMoneyInteractor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public record ShowMoneyValueExecutor(PlayerMoneyInteractor playerMoneyInteractor) {

    public void executor(Player player) {
        Double money = playerMoneyInteractor.getValue(player.getName());

        player.sendMessage(ChatColor.AQUA + "money : " + money.toString());
    }
}
