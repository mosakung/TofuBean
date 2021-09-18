package com.tofu.bean.plugin.casino.gacha.contact;

import com.tofu.bean.plugin.casino.gacha.GachaRarity;
import org.bukkit.inventory.ItemStack;
import oshi.util.tuples.Pair;

public interface GachaItemGenerate {
    Pair<GachaRarity, ItemStack> random();
}
