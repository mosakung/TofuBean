package com.tofu.bean.plugin.casino.gacha.impl.general;

import com.tofu.bean.plugin.casino.gacha.GachaRarity;
import com.tofu.bean.plugin.casino.gacha.contact.GachaCouponPool;
import com.tofu.bean.plugin.casino.gacha.contact.GachaItemGenerate;
import com.tofu.bean.plugin.casino.gacha.impl.general.pool.GachaGeneralEpic;
import com.tofu.bean.plugin.casino.gacha.impl.general.pool.GachaGeneralLegendary;
import com.tofu.bean.plugin.casino.gacha.impl.general.pool.GachaGeneralNormal;
import com.tofu.bean.plugin.casino.gacha.impl.general.pool.GachaGeneralRare;
import com.tofu.bean.utils.random.RandomCollection;
import org.bukkit.inventory.ItemStack;
import oshi.util.tuples.Pair;

import java.util.Arrays;

public class GachaGenerateGeneral implements GachaItemGenerate {

    private RandomCollection<Pair<GachaRarity, RandomCollection<GachaCouponPool>>> rarity = new RandomCollection<>();

    private Pair<GachaRarity, RandomCollection<GachaCouponPool>> normalPoolWithLabel;
    private Pair<GachaRarity, RandomCollection<GachaCouponPool>> rarePoolWithLabel;
    private Pair<GachaRarity, RandomCollection<GachaCouponPool>> epicPoolWithLabel;
    private Pair<GachaRarity, RandomCollection<GachaCouponPool>> legendaryPoolWithLabel;

    private RandomCollection<GachaCouponPool> normalPool = new RandomCollection<>();
    private RandomCollection<GachaCouponPool> rarePool = new RandomCollection<>();
    private RandomCollection<GachaCouponPool> epicPool = new RandomCollection<>();
    private RandomCollection<GachaCouponPool> legendaryPool = new RandomCollection<>();

    public GachaGenerateGeneral() {
        initialize();
    }

    @Override
    public Pair<GachaRarity, ItemStack> random() {
        Pair<GachaRarity, RandomCollection<GachaCouponPool>> pair = rarity.next();

        GachaRarity gachaRarity = pair.getA();
        RandomCollection randomCollection = pair.getB();

        if (randomCollection.next() instanceof GachaCouponPool gachaCouponPool) {
            return new Pair<>(gachaRarity, gachaCouponPool.generate());
        }

        return null;
    }

    private void initialize() {
        genNormalPool();
        genRarePool();
        genEpicPool();
        genLegendaryPool();
        injectPool();
        genRandomCollection();
    }

    private void genRandomCollection() {
        rarity
                .add(74.9, normalPoolWithLabel)
                .add(20, rarePoolWithLabel)
                .add(5, epicPoolWithLabel)
                .add(0.1, legendaryPoolWithLabel);
    }

    private void genNormalPool() {
        Arrays.stream(GachaGeneralNormal.values()).forEach(gachaGeneralNormal -> normalPool.add(gachaGeneralNormal.getWeight(), gachaGeneralNormal));
    }

    private void genRarePool() {
        Arrays.stream(GachaGeneralRare.values()).forEach(gachaGeneralRare -> rarePool.add(gachaGeneralRare.getWeight(), gachaGeneralRare));
    }

    private void genEpicPool() {
        Arrays.stream(GachaGeneralEpic.values()).forEach(gachaGeneralEpic -> epicPool.add(gachaGeneralEpic.getWeight(), gachaGeneralEpic));
    }

    private void genLegendaryPool() {
        Arrays.stream(GachaGeneralLegendary.values()).forEach(gachaGeneralLegendary -> legendaryPool.add(gachaGeneralLegendary.getWeight(), gachaGeneralLegendary));
    }

    private void injectPool() {
        normalPoolWithLabel = new Pair<>(GachaRarity.NORMAL, normalPool);
        rarePoolWithLabel = new Pair<>(GachaRarity.RARE, rarePool);
        epicPoolWithLabel = new Pair<>(GachaRarity.EPIC, epicPool);
        legendaryPoolWithLabel = new Pair<>(GachaRarity.LEGENDARY, legendaryPool);
    }
}
