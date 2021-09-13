package com.tofu.bean.plugin.casino.blackjack.action;

import com.tofu.bean.plugin.casino.enums.CasinoCard;
import com.tofu.bean.plugin.casino.enums.StandardCardPointType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class BlackJackActionGame {

    private BlackJackDeckAction blackJackDeckAction;

    public BlackJackActionGame(BlackJackDeckAction blackJackDeckAction) {
        this.blackJackDeckAction = blackJackDeckAction;
    }

    public void initializeBoard(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(1);

        if(itemStack == null) {
            inventory.setItem(1, changeCasinoCardtoItem(blackJackDeckAction.getCasinoCard()));
            inventory.setItem(19, changeCasinoCardtoItem(blackJackDeckAction.getCasinoCard()));
            inventory.setItem(2, getHideCard());
            inventory.setItem(20, changeCasinoCardtoItem(blackJackDeckAction.getCasinoCard()));

            setPlayerScore(inventory, getPlayerBestScore(inventory));
            setDealerScore(inventory, getDealerBestScore(inventory));

            ItemStack c1 = inventory.getItem(19);
            ItemStack c2 = inventory.getItem(20);

            if(c1 != null && c2 != null) {
                if((c1.getAmount() == 1 && c2.getAmount() == 10) || (c1.getAmount() == 10 && c2.getAmount() == 1)) {
                    addWinBlackJackFlag(inventory);
                }
            }
        }

        inventory.setItem(22, new ItemStack(Material.EMERALD, 0));
    }

    public void dealerPlay(Inventory inventory, Boolean doubleDown) {

        inventory.setItem(2, changeCasinoCardtoItem(blackJackDeckAction.getCasinoCard()));

        int after2Card = getDealerBestScore(inventory);
        setDealerScore(inventory, after2Card);

        if(after2Card >= 17) {
            logicPointMore17(inventory, after2Card, doubleDown);
            shouldInsuranceFlag(inventory);
            return;
        }

        while(true) {
            drawCard(inventory, 3);
            int dealerPoint = getDealerBestScore(inventory);
            setDealerScore(inventory, dealerPoint);
            if(dealerPoint > 21) {
                if(doubleDown) addDoubleDownFlag(inventory);
                else addWinerFlag(inventory);
                break;
            } else if(dealerPoint >= 17) {
                logicPointMore17(inventory, dealerPoint, doubleDown);
                break;
            }
        }
        shouldInsuranceFlag(inventory);
    }

    public void dealerOpenHidden(Inventory inventory) {
        inventory.setItem(2, changeCasinoCardtoItem(blackJackDeckAction.getCasinoCard()));
        setDealerScore(inventory, getDealerBestScore(inventory));

        shouldInsuranceFlag(inventory);
    }

    private Boolean dealerBlackJack(Inventory inventory) {
        ItemStack c1 = inventory.getItem(1);
        ItemStack c2 = inventory.getItem(2);

        if(c1 != null && c2 != null && inventory.getItem(3) == null) {
            return c1.getAmount() == 1 && c2.getAmount() == 10;
        }

        return false;
    }

    public Boolean hasFlag(Inventory inventory) {
        return hasLoseFlag(inventory) || hasWinerFlag(inventory) || hasDrawFlag(inventory) || hasDoubleDownFlag(inventory) || hasSurrenderFlag(inventory) || hasDoubleDownDrawFlag(inventory) || hasWinBlackJackFlag(inventory);
    }

    private void logicPointMore17(Inventory inventory, int after2Card, Boolean doubleDown) {
        if(after2Card == getPlayerBestScore(inventory)) {
            if (doubleDown) addDoubleDownDrawFlag(inventory);
            else addDrawFlag(inventory);
        } else if (after2Card > getPlayerBestScore(inventory)) {
            addLoseFlag(inventory);
        }else {
            if(doubleDown) addDoubleDownFlag(inventory);
            else addWinerFlag(inventory);
        }
    }

    public Boolean isInitializeBoard(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(22);

        if(itemStack == null) {
            return true;
        }

        return itemStack.getType() != Material.EMERALD;
    }

    public void drawCard(Inventory inventory, int target) {
        ItemStack targetItem = inventory.getItem(target);

        if (targetItem == null) {
            inventory.setItem(target, changeCasinoCardtoItem(blackJackDeckAction.getCasinoCard()));
        } else {
            drawCard(inventory, target + 1);
        }
    }

    public int getPlayerBestScore(Inventory inventory) {
        List<Integer> scores = calculatePlayerAllScore(inventory);;

        int max = Collections.max(scores);

        return max;
    }

    public void setPlayerScore(Inventory inventory, int score) {
        ItemStack itemStack = inventory.getItem(35);

        if(itemStack != null) {
            itemStack.setAmount(score);
        }
    }

    public int getDealerBestScore(Inventory inventory) {
        List<Integer> scores = calculateDealerAllScore(inventory);;

        if(scores.size() == 0) {
            return 64;
        }

        return Collections.max(scores);
    }

    public void setDealerScore(Inventory inventory, int score) {
        ItemStack itemStack = inventory.getItem(17);

        if(itemStack != null) {
            itemStack.setAmount(score);
        }
    }

    private List<Integer> calculateDealerAllScore(Inventory inventory) {
        List<Integer> pointList = new ArrayList<>();

        int cardIndex = 1;
        pointList.add(0);
        while (true) {
            ItemStack card = inventory.getItem(cardIndex);

            if (card != null) {
                if(card.getType() == Material.NAME_TAG) {
                    break;
                }
                logicPoint(pointList, card);
            } else {
                break;
            }

            cardIndex += 1;
        }

        List<Integer> below21 = pointList.stream().filter(integer -> integer <= 21).toList();

        return below21;
    }

    private void logicPoint(List<Integer> pointList, ItemStack card) {
        int point = card.getAmount();
        if (point == 1) {
            AtomicReference<Integer> preSet = new AtomicReference<>(0);
            pointList.replaceAll(integer -> {
                preSet.set(integer);
                return integer + 1;
            });
            pointList.add(preSet.get() + 11);
        } else {
            pointList.replaceAll(
                    integer -> integer + point
            );
        }
    }

    public Boolean hasOver21(Inventory inventory) {
        List<Integer> scores = calculatePlayerAllScore(inventory);

        return !(scores.size() > 0);
    }

    public void addLoseFlag(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.TNT);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setDisplayName("You Lose, Click to exit");
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(44, itemStack);
        }
    }

    public Boolean hasLoseFlag(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(44);

        if(itemStack == null) {
            return false;
        }

        return itemStack.getType() == Material.TNT;
    }

    public void addWinerFlag(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.SPORE_BLOSSOM);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setDisplayName("You Winner, Click to exit");
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(44, itemStack);
        }
    }

    public Boolean hasWinerFlag(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(44);

        if(itemStack == null) {
            return false;
        }

        return itemStack.getType() == Material.SPORE_BLOSSOM;
    }

    public void addDrawFlag(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.AXOLOTL_BUCKET);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setDisplayName("You Draw, Click to exit");
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(44, itemStack);
        }
    }

    public Boolean hasDrawFlag(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(44);

        if(itemStack == null) {
            return false;
        }

        return itemStack.getType() == Material.AXOLOTL_BUCKET;
    }

    public void addDoubleDownFlag(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.BELL);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setDisplayName("You Winner \"Double Down\", Click to exit");
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(44, itemStack);
        }
    }

    public Boolean hasDoubleDownFlag(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(44);

        if(itemStack == null) {
            return false;
        }

        return itemStack.getType() == Material.BELL;
    }

    public void addSurrenderFlag(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.WHITE_BANNER);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setDisplayName("You Surrender, Click to exit");
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(44, itemStack);
        }
    }

    public Boolean hasSurrenderFlag(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(44);

        if(itemStack == null) {
            return false;
        }

        return itemStack.getType() == Material.WHITE_BANNER;
    }

    public Boolean hasInsurance(Inventory inventory) {
        ItemStack faceUp = inventory.getItem(1);
        if (faceUp != null) {
            return faceUp.getAmount() == 1;
        }
        return false;
    }

    public void addPreInsuranceFlag(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.EGG);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setDisplayName("Insurance holding");
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(43, itemStack);
        }
    }

    public Boolean hasPreInsuranceFlag(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(43);

        if(itemStack == null) {
            return false;
        }

        return itemStack.getType() == Material.EGG;
    }

    private void shouldInsuranceFlag(Inventory inventory) {
        if(hasPreInsuranceFlag(inventory) && dealerBlackJack(inventory)) {
            addInsuranceFlag(inventory);
        }
    }

    public void addInsuranceFlag(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setDisplayName("Insurance Success");
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(43, itemStack);
        }
    }

    public Boolean hasInsuranceFlag(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(43);

        if(itemStack == null) {
            return false;
        }

        return itemStack.getType() == Material.NETHER_STAR;
    }

    public void addDoubleDownDrawFlag(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.LANTERN);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setDisplayName("You Draw, Click to exit");
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(44, itemStack);
        }
    }

    public Boolean hasDoubleDownDrawFlag(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(44);

        if(itemStack == null) {
            return false;
        }

        return itemStack.getType() == Material.LANTERN;
    }

    public void addWinBlackJackFlag(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.WITHER_ROSE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setDisplayName("Black Jack, Click to exit");
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(44, itemStack);
        }
    }

    public Boolean hasWinBlackJackFlag(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(44);

        if(itemStack == null) {
            return false;
        }

        return itemStack.getType() == Material.WITHER_ROSE;
    }

    private List<Integer> calculatePlayerAllScore(Inventory inventory) {
        List<Integer> pointList = new ArrayList<>();

        int cardIndex = 19;
        pointList.add(0);
        while (true) {
            ItemStack card = inventory.getItem(cardIndex);

            if (card != null) {
                logicPoint(pointList, card);
            } else {
                break;
            }

            cardIndex += 1;
        }

        return pointList.stream().filter(integer -> integer <= 21).toList();
    }

    private ItemStack changeCasinoCardtoItem(CasinoCard casinoCard) {
        ItemStack card = new ItemStack(Material.PAPER, mapPoint2Value(casinoCard.getPoint()));
        ItemMeta cardItemMeta = card.getItemMeta();
        if (cardItemMeta != null) {
            cardItemMeta.setDisplayName(ChatColor.WHITE + casinoCard.getPoint().getName() + " " + casinoCard.getSymbol().getSymbolMessage());
            card.setItemMeta(cardItemMeta);
        }

        return card;
    }

    private int mapPoint2Value(StandardCardPointType pointType) {
        return switch (pointType) {
            case ACE -> 1;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN, JACK, QUEEN, KING -> 10;
        };
    }

    private ItemStack getHideCard() {
        ItemStack hit = new ItemStack(Material.NAME_TAG);
        ItemMeta hitMeta = hit.getItemMeta();

        Objects.requireNonNull(hitMeta).setDisplayName("Hidden Card");
        hit.setItemMeta(hitMeta);

        return hit;
    }
}
