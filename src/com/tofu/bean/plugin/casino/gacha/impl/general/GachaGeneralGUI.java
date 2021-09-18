package com.tofu.bean.plugin.casino.gacha.impl.general;

import com.tofu.bean.plugin.casino.gacha.GachaRarity;
import com.tofu.bean.plugin.casino.gacha.contact.GachaBoardGUI;
import com.tofu.bean.plugin.casino.gacha.contact.GachaItemGenerate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import oshi.util.tuples.Pair;

import static com.tofu.bean.utils.prefix.message.PrefixMessage.withGachaPrefix;
import static com.tofu.bean.utils.sign.SignMessageUtils.buildSignMessageLabel;

public class GachaGeneralGUI implements GachaBoardGUI {

    private String gachaName;
    private final GachaItemGenerate randomInteractor;

    public GachaGeneralGUI(String gachaName, GachaItemGenerate randomInteractor) {
        this.gachaName = gachaName;
        this.randomInteractor = randomInteractor;
    }

    @Override
    public void open(Player player) {
        int BOARD_SIZE = 9;
        int VIEW_ITEM = 4;

        Pair<GachaRarity, ItemStack> pair = randomInteractor.random();

        if (pair == null) {
            return;
        }

        GachaRarity gachaRarity = pair.getA();
        ItemStack itemStack = pair.getB();

        Inventory gachaBoard = Bukkit.createInventory(null, BOARD_SIZE, buildSignMessageLabel(gachaName) + ChatColor.WHITE + " : " + player.getName());

        player.openInventory(gachaBoard);
        gachaBoard.setItem(VIEW_ITEM, itemStack);

        switch (gachaRarity) {
            case NORMAL -> player.sendMessage(withGachaPrefix(ChatColor.WHITE + gachaRarity.getRarity() + " !!"));
            case RARE -> player.sendMessage(withGachaPrefix(ChatColor.GREEN + gachaRarity.getRarity() + " !!"));
            case EPIC -> player.sendMessage(withGachaPrefix(ChatColor.LIGHT_PURPLE + gachaRarity.getRarity() + " !!"));
            case LEGENDARY -> player.sendMessage(withGachaPrefix(ChatColor.GOLD + gachaRarity.getRarity() + " !!"));
        }
    }
}
