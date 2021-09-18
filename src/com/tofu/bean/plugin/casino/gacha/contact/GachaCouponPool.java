package com.tofu.bean.plugin.casino.gacha.contact;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import org.bukkit.inventory.ItemStack;

public interface GachaCouponPool {
    GenerateItemStackFunc getGenerateItemStackFunc();
    int getAmount();
    Double getWeight();
    ItemStack generate();
}
