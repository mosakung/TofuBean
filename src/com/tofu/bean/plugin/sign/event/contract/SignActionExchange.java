package com.tofu.bean.plugin.sign.event.contract;

import com.tofu.bean.data.enums.sign.branch.ExchangeSignBranch;
import org.bukkit.entity.Player;

public interface SignActionExchange {
    void action(Player player, ExchangeSignBranch exchangeSignBranch);
}
