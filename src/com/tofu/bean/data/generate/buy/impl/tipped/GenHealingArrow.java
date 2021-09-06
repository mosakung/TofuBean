package com.tofu.bean.data.generate.buy.impl.tipped;

import com.tofu.bean.data.generate.buy.contract.GenerateItemStackFunc;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;

import static org.bukkit.potion.PotionType.INSTANT_HEAL;

public class GenHealingArrow extends GenTippedArrow implements GenerateItemStackFunc {

    @Override
    public ItemStack generate(int amount) {

        PotionData potionData = new PotionData(INSTANT_HEAL);

        return createTippedArrow(amount, potionData);
    }
}
