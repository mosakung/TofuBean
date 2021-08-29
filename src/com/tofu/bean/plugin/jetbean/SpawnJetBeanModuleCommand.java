package com.tofu.bean.plugin.jetbean;

import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.domain.contract.SpawnBeanInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.action.impl.JetBeanActionImpl;
import com.tofu.bean.plugin.jetbean.executor.spawn.CostJetBeanSpawn;
import com.tofu.bean.plugin.jetbean.executor.spawn.JetBean2Spawn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnBeanModuleCommand implements CommandExecutor {

    private final SpawnBeanInteractor spawnBeanInteractor;

    private final JetBean2Spawn jetBean2Spawn;
    private final CostJetBeanSpawn costJetBeanSpawn;

    public SpawnBeanModuleCommand(
            PlayerBeansInteractor playerBeansInteractor,
            SpawnBeanInteractor spawnBeanInteractor
    ) {
        this.spawnBeanInteractor = spawnBeanInteractor;

        JetBeanAction jetBeanAction = new JetBeanActionImpl();

        this.jetBean2Spawn = new JetBean2Spawn(jetBeanAction, playerBeansInteractor);
        this.costJetBeanSpawn = new CostJetBeanSpawn(jetBeanAction, playerBeansInteractor);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player) {

            if (strings.length == 0) {
                player.sendMessage(ChatColor.AQUA + "1");
                spawn(player, "default");
                return true;
            }

            if (strings.length == 1) {

                if (strings[0].equals("help")) {
                    showCommandManual(player);
                    return true;
                }

                if (strings[0].equals("set")) {
                    setSpawn(player, "default");
                    return true;
                }

                if (strings[0].equals("cost")) {
                    costSpawn(player, "default");
                    return true;
                }
            }

            if (strings.length == 2) {

                if (strings[0].equals("set")) {
                    setSpawn(player, strings[1]);
                    return true;
                }

                if (strings[0].equals("cost")) {
                    costSpawn(player, strings[1]);
                    return true;
                }

                onInvalidCommand(player);
                return false;
            }

            onInvalidCommand(player);
            return false;
        }

        return false;
    }

    private Boolean spawn(Player player, String spawnName) {
        final Boolean condition = spawnBeanInteractor.hasSpawnName(spawnName);

        if (!condition) {
            showNotFoundSpawn(player);
            return false;
        }

        String rawLocation = spawnBeanInteractor.getSpawnLocation(spawnName);

        String[] partLocation = rawLocation.split("!");

        Location location = new Location(
                Bukkit.getServer().getWorld(partLocation[3]),
                Integer.parseInt(partLocation[0]),
                Integer.parseInt(partLocation[1]),
                Integer.parseInt(partLocation[2])
        );

        jetBean2Spawn.execute(player, location);

        return true;
    }

    private void setSpawn(Player player, String spawnName) {
        Location location = player.getLocation();

        String rawLocation = location.getBlockX() + "!" + location.getBlockY() + "!" +
                location.getBlockZ() + "!" + location.getWorld().getName();

        final Boolean condition = spawnBeanInteractor.hasSpawnName(spawnName);

        if (condition) {
            spawnBeanInteractor.updateSpawnLocation(spawnName, rawLocation);
        } else {
            spawnBeanInteractor.insertSpawnLocation(spawnName, rawLocation);
        }

        player.sendMessage(ChatColor.AQUA + "set spawn : " + spawnName);
    }

    private void costSpawn(Player player, String spawnName) {
        final Boolean condition = spawnBeanInteractor.hasSpawnName(spawnName);

        if (!condition) {
            showNotFoundSpawn(player);
            return;
        }

        String rawLocation = spawnBeanInteractor.getSpawnLocation(spawnName);

        String[] partLocation = rawLocation.split("!");

        Location location = new Location(
                Bukkit.getServer().getWorld(partLocation[3]),
                Integer.parseInt(partLocation[0]),
                Integer.parseInt(partLocation[1]),
                Integer.parseInt(partLocation[2])
        );

        costJetBeanSpawn.executor(player, location);
    }

    private void onInvalidCommand(Player player) {
        player.sendMessage(ChatColor.YELLOW + "invalid command /spawn help");
    }

    private void showCommandManual(Player player) {
        player.sendMessage(ChatColor.WHITE + "===== Manual Tofu Bean =====");
        player.sendMessage(ChatColor.WHITE + "- /spawn <spawn name> | default");
        player.sendMessage(ChatColor.WHITE + "- /spawn set <spawn name> | default (developer only)");
    }

    private void showNotFoundSpawn(Player player) {
        player.sendMessage(ChatColor.YELLOW + "not found spawn name");
    }
}
