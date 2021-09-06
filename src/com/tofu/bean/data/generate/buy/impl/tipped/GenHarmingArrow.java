package com.tofu.bean.data.generate.buy.impl.tipped;

import com.tofu.bean.data.generate.buy.contract.GenerateItemStackFunc;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;

import static org.bukkit.potion.PotionType.INSTANT_DAMAGE;

public class GenHarmingArrow extends GenTippedArrow implements GenerateItemStackFunc {

    @Override
    public ItemStack generate(int amount) {

        PotionData potionData = new PotionData(INSTANT_DAMAGE);

        return createTippedArrow(amount, potionData);
    }
}
