package com.tofu.bean.plugin.casino.blackjack.gui;

import com.tofu.bean.data.enums.sign.branch.CasinoSignBranch;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

import static com.tofu.bean.plugin.casino.enums.blackjack.BlackJackActionType.*;

public class BlackJackGUI implements BoardGUI {

    public BlackJackGUI() {
    }

    @Override
    public void open(Player player, CasinoSignBranch casinoSignBranch) {
        initializeBlackJackBoard(player, casinoSignBranch);
    }

    private void initializeBlackJackBoard(Player player, CasinoSignBranch casinoSignBranch) {
        int BOARD_SIZE = 45;
        Inventory bjBoard = Bukkit.createInventory(null, BOARD_SIZE, casinoSignBranch.getMessageInGameTableName(player.getName()));

        int ACTION_ROW = 36;
        // Hand //
        bjBoard.setItem(0, getDealerHand());
        bjBoard.setItem(18, getYourHand());

        // Score //
        bjBoard.setItem(17, getScoreDealer());
        bjBoard.setItem(35, getScorePlayer());

        // Action //
        bjBoard.setItem(ACTION_ROW, getHitAction());
        bjBoard.setItem(ACTION_ROW + 1, getStandAction());
        bjBoard.setItem(ACTION_ROW + 2, getDoubleDownAction());
        bjBoard.setItem(ACTION_ROW + 3, getSurrenderAction());
        bjBoard.setItem(ACTION_ROW + 4, getInsuranceAction());

        // init //
        bjBoard.setItem(22, getStart());

        player.openInventory(bjBoard);
    }

    private ItemStack getHitAction() {
        ItemStack hit = new ItemStack(HIT.getDisplayIcon());
        ItemMeta hitMeta = hit.getItemMeta();
        ArrayList<String> hitLore = new ArrayList<>();

        Objects.requireNonNull(hitMeta).setDisplayName(HIT.getDisplayName());
        hitLore.add(ChatColor.WHITE + "take a card");
        hitMeta.setLore(hitLore);
        hit.setItemMeta(hitMeta);

        return hit;
    }

    private ItemStack getStandAction() {
        ItemStack stand = new ItemStack(STAND.getDisplayIcon());
        ItemMeta hitMeta = stand.getItemMeta();
        ArrayList<String> hitLore = new ArrayList<>();

        Objects.requireNonNull(hitMeta).setDisplayName(STAND.getDisplayName());
        hitLore.add(ChatColor.WHITE + "end their turn and stop without");
        hitLore.add(ChatColor.WHITE + "taking a card");
        hitMeta.setLore(hitLore);
        stand.setItemMeta(hitMeta);

        return stand;
    }

    private ItemStack getDoubleDownAction() {
        ItemStack doubleDown = new ItemStack(DOUBLE_DOWN.getDisplayIcon());
        ItemMeta hitMeta = doubleDown.getItemMeta();
        ArrayList<String> hitLore = new ArrayList<>();

        Objects.requireNonNull(hitMeta).setDisplayName(DOUBLE_DOWN.getDisplayName());
        hitLore.add(ChatColor.WHITE + "double their wager, take a single card");
        hitLore.add(ChatColor.WHITE + "and finish");
        hitMeta.setLore(hitLore);
        doubleDown.setItemMeta(hitMeta);

        return doubleDown;
    }

    private ItemStack getSurrenderAction() {
        ItemStack surrender = new ItemStack(SURRENDER.getDisplayIcon());
        ItemMeta hitMeta = surrender.getItemMeta();
        ArrayList<String> hitLore = new ArrayList<>();

        Objects.requireNonNull(hitMeta).setDisplayName(SURRENDER.getDisplayName());
        hitLore.add(ChatColor.WHITE + "give up a half-bet and retire from the game");
        hitMeta.setLore(hitLore);
        surrender.setItemMeta(hitMeta);

        return surrender;
    }

    private ItemStack getInsuranceAction() {
        ItemStack insurance = new ItemStack(INSURANCE.getDisplayIcon());
        ItemMeta hitMeta = insurance.getItemMeta();
        ArrayList<String> hitLore = new ArrayList<>();

        Objects.requireNonNull(hitMeta).setDisplayName(INSURANCE.getDisplayName());
        hitLore.add(ChatColor.WHITE + "Which may be placed when the dealer's face up");
        hitLore.add(ChatColor.WHITE + "card is an ace. Additional side bets, such as");
        hitLore.add(ChatColor.WHITE + "\"Dealer Match\" which pays when the player's");
        hitLore.add(ChatColor.WHITE + "cards match the dealer's up card");
        hitMeta.setLore(hitLore);
        insurance.setItemMeta(hitMeta);

        return insurance;
    }

    private ItemStack getYourHand() {
        ItemStack hit = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta hitMeta = hit.getItemMeta();

        Objects.requireNonNull(hitMeta).setDisplayName("Your hand");
        hit.setItemMeta(hitMeta);

        return hit;
    }

    private ItemStack getDealerHand() {
        ItemStack hit = new ItemStack(Material.DRAGON_HEAD);
        ItemMeta hitMeta = hit.getItemMeta();

        Objects.requireNonNull(hitMeta).setDisplayName("Dealer hand");
        hit.setItemMeta(hitMeta);

        return hit;
    }

    private ItemStack getScorePlayer() {
        ItemStack hit = new ItemStack(Material.PAINTING);
        ItemMeta hitMeta = hit.getItemMeta();
        ArrayList<String> hitLore = new ArrayList<>();

        Objects.requireNonNull(hitMeta).setDisplayName("Your score");
        hitLore.add(ChatColor.WHITE + "calculate best score from your hand");
        hitMeta.setLore(hitLore);
        hit.setItemMeta(hitMeta);

        return hit;
    }

    private ItemStack getScoreDealer() {
        ItemStack hit = new ItemStack(Material.PAINTING);
        ItemMeta hitMeta = hit.getItemMeta();

        Objects.requireNonNull(hitMeta).setDisplayName("Dealer score");
        hit.setItemMeta(hitMeta);

        return hit;
    }

    private ItemStack getStart() {
        ItemStack hit = new ItemStack(Material.EMERALD);
        ItemMeta hitMeta = hit.getItemMeta();

        Objects.requireNonNull(hitMeta).setDisplayName("Click to start");
        hit.setItemMeta(hitMeta);

        return hit;
    }
}
