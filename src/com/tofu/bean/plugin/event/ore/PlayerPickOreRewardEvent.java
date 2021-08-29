package com.tofu.bean.plugin.event.ore;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import static com.tofu.bean.utils.mapper.OreMapper.mapOre2Value;

public class PlayerPickOreRewardEvent {

    private PlayerBeansInteractor playerBeansInteractor;

    public PlayerPickOreRewardEvent(
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void call(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Double money = mapOre2Value(material);
        if (money != 0) {
            Player player = event.getPlayer();
            playerBeansInteractor.increasedValue(player.getName(), money);
            player.sendMessage(ChatColor.AQUA + "+ " + ChatColor.GOLD + money.toString() + ChatColor.AQUA + " Beans");
        }
    }
}
