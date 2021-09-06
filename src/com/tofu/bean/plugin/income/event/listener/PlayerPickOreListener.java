package com.tofu.bean.plugin.income.event.listener;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.tofu.bean.utils.mapper.OreMapper.mapOre2Value;

public class PlayerPickOreListener {

    private PlayerBeansInteractor playerBeansInteractor;

    public PlayerPickOreListener(
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

            ItemStack tool = player.getInventory().getItemInMainHand();

            if (!hasSilkTouch(tool)) {
                playerBeansInteractor.increasedValue(player.getName(), money);
                player.sendMessage(ChatColor.AQUA + "+ " + ChatColor.GOLD + money.toString() + ChatColor.AQUA + " Beans");
            }

        }
    }

    private Boolean hasSilkTouch(ItemStack tool) {
        Map<Enchantment, Integer> enchantments = tool.getEnchantments();

        AtomicReference<Boolean> result = new AtomicReference<>(false);

        enchantments.forEach((enchantment, integer) -> {
            if (enchantment.equals(Enchantment.SILK_TOUCH)) {
                result.set(true);
            };
        });

        return result.get();
    }
}
