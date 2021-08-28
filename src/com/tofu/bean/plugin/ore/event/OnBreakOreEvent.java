package com.tofu.bean.plugin.ore.event;

import com.tofu.bean.plugin.beans.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.utils.mapper.BlockMapper;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBreakOreEvent implements Listener {

    private PlayerBeansInteractor playerBeansInteractor;
    private BlockMapper blockMapper;

    public OnBreakOreEvent(PlayerBeansInteractor playerBeansInteractor) {
        this.playerBeansInteractor = playerBeansInteractor;
        blockMapper = new BlockMapper();
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        Material material = block.getType();
        Double money = blockMapper.blockType(material);
        if(money != 0){
            Player player = event.getPlayer();
            playerBeansInteractor.increasedValue(player.getName(), money);
            player.sendMessage(ChatColor.AQUA + "+ " + ChatColor.GOLD + money.toString() + ChatColor.AQUA + " Beans");
        }
    }


}
