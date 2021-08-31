package com.tofu.bean.plugin.event.sign.use;

import com.tofu.bean.data.sign.SignBuyItemMethod;
import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Objects;

import static com.tofu.bean.data.sign.SignActionMethod.BUY;
import static com.tofu.bean.utils.SignMessageUtils.revertSignMessageActionFormat;
import static com.tofu.bean.utils.mapper.SignMapper.isSignMaterial;

public record PlayerUseSignShop(PlayerBeansInteractor playerBeansInteractor) {

    public void call(PlayerInteractEvent event) {

        Block block = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && isSignMaterial(Objects.requireNonNull(block).getType())) {

            if (block.getState() instanceof Sign sign) {
                Player player = event.getPlayer();
                String signLine1 = sign.getLine(0);

                if (signLine1.contains("§")) {

                    player.sendMessage(ChatColor.AQUA + "mos1");

                    String actionFormat = revertSignMessageActionFormat(signLine1);

                    if (actionFormat == null) {
                        return;
                    }


                    if (BUY.equal(actionFormat)) {
                        Arrays.stream(SignBuyItemMethod.values()).forEach(signBuyItemMethod -> {

                            if (signBuyItemMethod.equal(sign.getLine(1), sign.getLine(2), sign.getLine(3))) {
                                purchaseItem(player, signBuyItemMethod);
                            }
                        });
                    }
                }
            }
        }
    }

    private void sendItem2Player(Player player, Material material, int amount) {
        ItemStack itemStack = new ItemStack(material, amount);
        player.getInventory().addItem(itemStack);
    }

    private void purchaseItem(Player player, SignBuyItemMethod signBuyItemMethod) {

        String playerName = player.getName();
        Double pocketValue = playerBeansInteractor.getValue(playerName);

        if (pocketValue == null) {
            player.sendMessage(ChatColor.DARK_RED + "Something Error (PlayerUseSignShop (purchaseItem)) tell BearSouL : pocketValue == null");
            return;
        }

        Material item2buy = signBuyItemMethod.getMaterial();
        Double cost = signBuyItemMethod.getBeansValue();
        int amount = signBuyItemMethod.getAmountValue();
        String itemName = signBuyItemMethod.getItemName();

        if (pocketValue >= cost) {
            playerBeansInteractor.decreasedValue(playerName, cost);

            messageOnPlayerBuySuccess(player, itemName, amount);

            sendItem2Player(player, item2buy, amount);
        } else {
            messageOnPlayerBuyFail(player);
        }
    }

    private void messageOnPlayerBuySuccess(Player player, String itemName, int amount) {
        player.sendMessage(ChatColor.AQUA + "buy " + amount + " " + itemName);
    }

    private void messageOnPlayerBuyFail(Player player) {
        player.sendMessage(ChatColor.YELLOW + "can't buy this, check you beans!!");
    }
}
