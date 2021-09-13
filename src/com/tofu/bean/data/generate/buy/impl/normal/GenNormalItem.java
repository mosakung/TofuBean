package com.tofu.bean.data.generate.buy.impl.normal;

import com.tofu.bean.data.generate.buy.GenerateItemStackFunc;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GenNormalItem implements GenerateItemStackFunc {

    private final Material material;

    public GenNormalItem(
            Material material
    ){
        this.material = material;
    }

    @Override
    public ItemStack generate(int amount) {
        return new ItemStack(material, amount);
    }
}
