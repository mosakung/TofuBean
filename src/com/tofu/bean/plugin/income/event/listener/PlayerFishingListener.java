package com.tofu.bean.plugin.income.event.listener;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;

import static com.tofu.bean.utils.mapper.FishingMapper.mapCaughtFishing2Value;

public class PlayerFishingListener {

    private final PlayerBeansInteractor playerBeansInteractor;

    public PlayerFishingListener(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void call(PlayerFishEvent event) {
        Player player = event.getPlayer();

        if (event.getCaught() instanceof Item caught) {
            Material item = caught.getItemStack().getType();

            Double beanReward = mapCaughtFishing2Value(item);

            playerBeansInteractor.increasedValue(player.getName(), beanReward);
            messageOnPlayerCaughtItem(player, beanReward);
        }
    }

    private void messageOnPlayerCaughtItem(Player player, Double reward) {
        player.sendMessage(ChatColor.AQUA + "+ " + ChatColor.GOLD + reward.toString() + ChatColor.AQUA + " Beans");
    }
}
