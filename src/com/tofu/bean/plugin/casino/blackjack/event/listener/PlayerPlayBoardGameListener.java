package com.tofu.bean.plugin.casino.blackjack.event.listener;

import com.tofu.bean.data.enums.casino.table.CasinoGameType;
import com.tofu.bean.data.enums.sign.branch.CasinoSignBranch;
import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.plugin.casino.blackjack.action.BlackJackActionGame;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerPlayBoardGameListener {

    private final BlackJackActionGame blackJackActionGame;
    private final PlayerBeansInteractor playerBeansInteractor;

    public PlayerPlayBoardGameListener(
            BlackJackActionGame blackJackActionGame,
            PlayerBeansInteractor playerBeansInteractor
    ) {
        this.blackJackActionGame = blackJackActionGame;
        this.playerBeansInteractor = playerBeansInteractor;
    }

    public void call(InventoryClickEvent event) {

        String inventoryName = event.getView().getTitle();
        HumanEntity player = event.getWhoClicked();
        String playerName = player.getName();

        CasinoSignBranch casinoSignBranch = CasinoSignBranch.findCasinoSignBranch(inventoryName, playerName);

        if(casinoSignBranch == null) {
            return;
        }

        if (casinoSignBranch.getCasinoTableType() == CasinoGameType.BLACK_JACK) {

            Inventory inventory = event.getInventory();
            ItemStack itemStack = event.getCurrentItem();
            Double bet = casinoSignBranch.getBet();

            if (itemStack != null) {
                if (blackJackActionGame.isInitializeBoard(inventory)) {
                    if (!blackJackActionGame.hasFlag(inventory)) {
                        switch (itemStack.getType()) {
                            case LIME_CANDLE -> {
                                blackJackActionGame.drawCard(inventory, 19);
                                if (blackJackActionGame.hasOver21(inventory)) {
                                    blackJackActionGame.dealerOpenHidden(inventory);
                                    blackJackActionGame.addLoseFlag(inventory);
                                    blackJackActionGame.setPlayerScore(inventory, 64);
                                } else {
                                    blackJackActionGame.setPlayerScore(inventory, blackJackActionGame.getPlayerBestScore(inventory));
                                }
                            }
                            case WHITE_CANDLE -> {
                                blackJackActionGame.dealerPlay(inventory, false);
                            }
                            case ORANGE_CANDLE -> {
                                Double currPocket = playerBeansInteractor.getValue(playerName);

                                if (currPocket >= bet) {
                                    playerBeansInteractor.decreasedValue(playerName, bet);
                                    player.sendMessage(withLabelCasino(ChatColor.WHITE + "BET More " + ChatColor.GOLD + bet.toString()));
                                    blackJackActionGame.drawCard(inventory, 19);
                                    if (blackJackActionGame.hasOver21(inventory)) {
                                        blackJackActionGame.addLoseFlag(inventory);
                                        blackJackActionGame.setPlayerScore(inventory, 1);
                                    } else {
                                        blackJackActionGame.setPlayerScore(inventory, blackJackActionGame.getPlayerBestScore(inventory));
                                        blackJackActionGame.dealerPlay(inventory, true);
                                    }
                                } else {
                                    player.sendMessage(withLabelCasino(ChatColor.YELLOW + "No beans Double Down " + ChatColor.WHITE + bet.toString()));
                                }
                            }
                            case  RED_CANDLE -> {
                                blackJackActionGame.dealerOpenHidden(inventory);
                                blackJackActionGame.addSurrenderFlag(inventory);
                            }
                            case BLUE_CANDLE -> {
                                if(blackJackActionGame.hasInsurance(inventory)) {
                                    Double insuranceCost = bet / 2;
                                    Double pocket = playerBeansInteractor.getValue(playerName);
                                    if(pocket >= insuranceCost) {
                                        player.sendMessage(withLabelCasino(ChatColor.WHITE + "Buy Insurance " + ChatColor.GOLD + insuranceCost.toString()));
                                        playerBeansInteractor.decreasedValue(playerName, insuranceCost);
                                        blackJackActionGame.addPreInsuranceFlag(inventory);
                                    } else {
                                        player.sendMessage(withLabelCasino(ChatColor.YELLOW + "No beans Insurance " + ChatColor.WHITE + insuranceCost.toString()));
                                    }
                                } else {
                                    player.sendMessage(withLabelCasino(ChatColor.YELLOW + "Face Up card not ACE"));
                                }
                            }
                        }
                    } else {

                        double insuranceReward = bet * 1.5;

                        if (itemStack.getType() == Material.TNT) {
                            if(blackJackActionGame.hasInsuranceFlag(inventory)) {
                                playerBeansInteractor.increasedValue(playerName ,insuranceReward);
                                player.sendMessage(withLabelCasino(ChatColor.AQUA + " (Insurance)" + ChatColor.WHITE + " + " + insuranceReward));
                            }
                            player.closeInventory();
                            player.sendMessage(withLabelCasino(ChatColor.DARK_RED + "!! LOSE !!"));
                        } else if (itemStack.getType() == Material.SPORE_BLOSSOM) {
                            if(blackJackActionGame.hasInsuranceFlag(inventory)) {
                                playerBeansInteractor.increasedValue(playerName ,insuranceReward);
                                player.sendMessage(withLabelCasino(ChatColor.AQUA + " (Insurance)" + ChatColor.WHITE + " + " + insuranceReward));
                            }
                            double reward = bet * 2;
                            player.closeInventory();
                            playerBeansInteractor.increasedValue(playerName, reward);
                            player.sendMessage(withLabelCasino(ChatColor.GREEN + "!! WINNER !!" + ChatColor.WHITE + " + " + reward));
                        } else if (itemStack.getType() == Material.AXOLOTL_BUCKET) {
                            if(blackJackActionGame.hasInsuranceFlag(inventory)) {
                                playerBeansInteractor.increasedValue(playerName ,insuranceReward);
                                player.sendMessage(withLabelCasino(ChatColor.AQUA + " (Insurance)" + ChatColor.WHITE + " + " + insuranceReward));
                            }
                            player.closeInventory();
                            playerBeansInteractor.increasedValue(playerName, bet);
                            player.sendMessage(withLabelCasino(ChatColor.WHITE + "!! DRAW !!" + ChatColor.WHITE + " + " + bet));
                        } else if (itemStack.getType() == Material.BELL) {
                            if(blackJackActionGame.hasInsuranceFlag(inventory)) {
                                playerBeansInteractor.increasedValue(playerName ,insuranceReward);
                                player.sendMessage(withLabelCasino(ChatColor.AQUA + " (Insurance)" + ChatColor.WHITE + " + " + insuranceReward));
                            }
                            double reward = bet * 4;
                            player.closeInventory();
                            playerBeansInteractor.increasedValue(playerName, reward);
                            player.sendMessage(withLabelCasino(ChatColor.GREEN + "!! WINNER !!" + ChatColor.WHITE + " + " + reward));
                        } else if (itemStack.getType() == Material.WHITE_BANNER) {
                            if(blackJackActionGame.hasInsuranceFlag(inventory)) {
                                playerBeansInteractor.increasedValue(playerName ,insuranceReward);
                                player.sendMessage(withLabelCasino(ChatColor.AQUA + " (Insurance)" + ChatColor.WHITE + " + " + insuranceReward));
                            }
                            double halfBet = bet / 2;
                            player.closeInventory();
                            playerBeansInteractor.increasedValue(playerName, halfBet);
                            player.sendMessage(withLabelCasino(ChatColor.DARK_RED + "!! SURRENDER !!" + ChatColor.WHITE + " + " + halfBet));
                        } else if (itemStack.getType() == Material.LANTERN) {
                            if(blackJackActionGame.hasInsuranceFlag(inventory)) {
                                playerBeansInteractor.increasedValue(playerName ,insuranceReward);
                                player.sendMessage(withLabelCasino(ChatColor.AQUA + " (Insurance)" + ChatColor.WHITE + " + " + insuranceReward));
                            }
                            double doubleDownDraw = bet * 2;
                            player.closeInventory();
                            playerBeansInteractor.increasedValue(playerName, doubleDownDraw);
                            player.sendMessage(withLabelCasino(ChatColor.WHITE + "!! DRAW !!" + ChatColor.WHITE + " + " + doubleDownDraw));
                        } else if(itemStack.getType() == Material.WITHER_ROSE) {
                            if(blackJackActionGame.hasInsuranceFlag(inventory)) {
                                playerBeansInteractor.increasedValue(playerName ,insuranceReward);
                                player.sendMessage(withLabelCasino(ChatColor.AQUA + " (Insurance)" + ChatColor.WHITE + " + " + insuranceReward));
                            }
                            double blackjackReward = bet * 2.3;
                            player.closeInventory();
                            playerBeansInteractor.increasedValue(playerName, blackjackReward);
                            player.sendMessage(withLabelCasino(ChatColor.GREEN + "!! BLACK JACK !!" + ChatColor.WHITE + " + " + blackjackReward));
                        }
                    }
                } else {
                    if (itemStack.getType() == Material.EMERALD) {
                        Double pocketValue = playerBeansInteractor.getValue(playerName);

                        if(pocketValue >= bet) {
                            blackJackActionGame.initializeBoard(inventory);
                            playerBeansInteractor.decreasedValue(playerName, bet);
                            player.sendMessage(withLabelCasino(ChatColor.GREEN + "BET " + ChatColor.GOLD + bet.toString()));
                        } else {
                            player.sendMessage(withLabelCasino(ChatColor.YELLOW + "No more beans"));
                        }
                    }
                }
            }

            event.setCancelled(true);
        }
    }

    private String withLabelCasino(String message) {
        return ChatColor.WHITE + "[" + ChatColor.GOLD + "CASINO" + ChatColor.WHITE + "] " + ChatColor.RESET + message;
    }
}
