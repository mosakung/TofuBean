package com.tofu.bean.plugin.jetbean.command;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.domain.contract.jetbean.SpawnJetBeanInteractor;
import com.tofu.bean.plugin.jetbean.action.contract.JetBeanAction;
import com.tofu.bean.plugin.jetbean.action.impl.JetBeanActionImpl;
import com.tofu.bean.plugin.jetbean.command.executor.spawn.CostJetBeanSpawnExecutor;
import com.tofu.bean.plugin.jetbean.command.executor.spawn.JetBean2SpawnExecutor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.tofu.bean.utils.parser.LocationParser.location2String;

public class SpawnJetBeanModuleCommand implements CommandExecutor {

    private final SpawnJetBeanInteractor spawnJetBeanInteractor;

    private final JetBean2SpawnExecutor jetBean2SpawnExecutor;
    private final CostJetBeanSpawnExecutor costJetBeanSpawnExecutor;

    public SpawnJetBeanModuleCommand(
            PlayerBeansInteractor playerBeansInteractor,
            SpawnJetBeanInteractor spawnJetBeanInteractor
    ) {
        this.spawnJetBeanInteractor = spawnJetBeanInteractor;

        JetBeanAction jetBeanAction = new JetBeanActionImpl();

        this.jetBean2SpawnExecutor = new JetBean2SpawnExecutor(jetBeanAction, playerBeansInteractor, spawnJetBeanInteractor);
        this.costJetBeanSpawnExecutor = new CostJetBeanSpawnExecutor(jetBeanAction, playerBeansInteractor, spawnJetBeanInteractor);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player) {

            if (strings.length == 0) {
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

                spawn(player, strings[0]);
                return true;
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

    private void spawn(Player player, String spawnName) {
        final Boolean condition = spawnJetBeanInteractor.hasSpawnName(spawnName);

        if (!condition) {
            showNotFoundSpawn(player);
            return;
        }

        jetBean2SpawnExecutor.execute(player, spawnName);
    }

    private void setSpawn(Player player, String spawnName) {
        Location location = player.getLocation();

        String rawLocation = location2String(location);

        final Boolean condition = spawnJetBeanInteractor.hasSpawnName(spawnName);

        if (condition) {
            spawnJetBeanInteractor.updateSpawnLocation(spawnName, rawLocation);
        } else {
            spawnJetBeanInteractor.insertSpawnLocation(spawnName, rawLocation);
        }

        player.sendMessage(ChatColor.AQUA + "set spawn : " + spawnName);
    }

    private void costSpawn(Player player, String spawnName) {
        final Boolean condition = spawnJetBeanInteractor.hasSpawnName(spawnName);

        if (!condition) {
            showNotFoundSpawn(player);
            return;
        }

        costJetBeanSpawnExecutor.executor(player, spawnName);
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
