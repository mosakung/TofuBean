package com.tofu.bean.plugin.rule.event.listener;

import com.tofu.bean.presentation.PresentationPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispenseBucketLavaCauldronListener {

    public void call(BlockDispenseEvent event) {

        if (event != null) {
            Block block = event.getBlock();
            BlockState blockState = block.getState();

            if (blockState instanceof Dispenser dispenser) {
                Inventory inventory = dispenser.getInventory();
                ItemStack itemStack = event.getItem();
                Material material = itemStack.getType();

                if (material.equals(Material.LAVA_BUCKET)) {
                    Location dispenseLocation = block.getLocation();

                    BlockData blockData = block.getBlockData();
                    String rawBlockData = blockData.getAsString();

                    String facing = getFacing(rawBlockData);

                    if (facing != null) {
                        Location targetLocation = getTargetLocation(facing, dispenseLocation);

                        World world = dispenseLocation.getWorld();

                        if (world != null && targetLocation != null) {
                            Block targetBlock = world.getBlockAt(targetLocation);
                            Material targetType = targetBlock.getType();

                            if (targetType.equals(Material.CAULDRON)) {
                                BlockData lavaCauldron = Bukkit.createBlockData(Material.LAVA_CAULDRON);
                                targetBlock.setBlockData(lavaCauldron);

                                event.setCancelled(true);

                                Bukkit.getScheduler().runTaskLater(PresentationPlugin.getInstance(), () -> {
                                    pullItemPlayer(inventory, Material.LAVA_BUCKET, 1);
                                    inventory.addItem(new ItemStack(Material.BUCKET));
                                }, 2L);
                            }
                        }
                    }
                }

                if (material.equals(Material.BUCKET)) {
                    Location dispenseLocation = block.getLocation();

                    BlockData blockData = block.getBlockData();
                    String rawBlockData = blockData.getAsString();

                    String facing = getFacing(rawBlockData);

                    if (facing != null) {
                        Location targetLocation = getTargetLocation(Objects.requireNonNull(facing), dispenseLocation);
                        World world = dispenseLocation.getWorld();

                        if (world != null && targetLocation != null) {
                            Block targetBlock = world.getBlockAt(targetLocation);
                            Material targetType = targetBlock.getType();

                            if (targetType.equals(Material.LAVA_CAULDRON)) {
                                BlockData lavaCauldron = Bukkit.createBlockData(Material.CAULDRON);
                                targetBlock.setBlockData(lavaCauldron);

                                event.setCancelled(true);

                                Bukkit.getScheduler().runTaskLater(PresentationPlugin.getInstance(), () -> {
                                    pullItemPlayer(inventory, Material.BUCKET, 1);
                                    inventory.addItem(new ItemStack(Material.LAVA_BUCKET));
                                }, 2L);
                            }
                        }
                    }
                }
            }
        }
    }

    private void pullItemPlayer(Inventory inventory, Material material, int amount) {
        int itemIndex = -1;
        ItemStack currentItems;

        for (int index = 0; index < inventory.getSize(); index++) {
            currentItems = inventory.getItem(index);

            if (currentItems != null && currentItems.getType() == material && currentItems.getAmount() >= amount) {
                itemIndex = index;
                index = inventory.getSize();
            }
        }

        if (itemIndex != -1) {
            ItemStack itemStack = inventory.getItem(itemIndex);
            if (itemStack != null) {
                int _amount = itemStack.getAmount();
                itemStack.setAmount(_amount - amount);
            }
        }

    }

    private String getFacing(String blockDataAsString) {
        Pattern pattern = Pattern.compile("^minecraft:dispenser\\[facing=(.*),");
        Matcher matcher = pattern.matcher(blockDataAsString);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    private Location getTargetLocation(String facing, Location dispenseLocation) {
        if (facing.equals("west")) {
            return new Location(
                    dispenseLocation.getWorld(),
                    dispenseLocation.getBlockX() - 1,
                    dispenseLocation.getBlockY(),
                    dispenseLocation.getBlockZ()
            );
        }
        if (facing.equals("east")) {
            return new Location(
                    dispenseLocation.getWorld(),
                    dispenseLocation.getBlockX() + 1,
                    dispenseLocation.getBlockY(),
                    dispenseLocation.getBlockZ()
            );
        }
        if (facing.equals("north")) {
            return new Location(
                    dispenseLocation.getWorld(),
                    dispenseLocation.getBlockX(),
                    dispenseLocation.getBlockY(),
                    dispenseLocation.getBlockZ() - 1
            );
        }
        if (facing.equals("south")) {
            return new Location(
                    dispenseLocation.getWorld(),
                    dispenseLocation.getBlockX(),
                    dispenseLocation.getBlockY(),
                    dispenseLocation.getBlockZ() + 1
            );
        }
        return null;
    }
}
