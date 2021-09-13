package com.tofu.bean.data.generate.food.impl;

import com.tofu.bean.data.generate.food.ActiveCustomFood;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ActiveBreadSmallSpeed implements ActiveCustomFood {

    @Override
    public void active(Player player) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 100, 1);
        player.addPotionEffect(potionEffect);
    }
}
