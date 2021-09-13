package com.tofu.bean.data.generate.buy.impl.tipped;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;

import static org.bukkit.potion.PotionType.WEAKNESS;

public class GenWeaknessArrow extends GenTippedArrow implements GenerateItemStackFunc {

    @Override
    public ItemStack generate(int amount) {

        PotionData potionData = new PotionData(WEAKNESS);

        return createTippedArrow(amount, potionData);
    }
}
