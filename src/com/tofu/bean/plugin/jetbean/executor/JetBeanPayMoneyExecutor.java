package com.tofu.bean.plugin.teleport.executor;

import com.tofu.bean.plugin.money.domain.contract.PlayerMoneyInteractor;
import com.tofu.bean.plugin.teleport.action.contract.TeleportAction;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public record JetBeanPayMoneyExecutor(
        TeleportAction teleportAction, PlayerMoneyInteractor playerMoneyInteractor
) {

    public void executor(Player player, Player target) {
        String name = player.getName();

        Location pl = player.getLocation();
        Location plt = target.getLocation();

        Double diffP2X = pow(pl.getBlockX() - plt.getBlockX(), 2);
        Double diffP2Y = pow(pl.getBlockY() - plt.getBlockY(), 2);
        Double diffP2Z = pow(pl.getBlockZ() - plt.getBlockZ(), 2);

        Double diffDistance = sqrt(diffP2X + diffP2Y + diffP2Z);

        Double cost = diffDistance * 2.5;

        Double pocketValue = playerMoneyInteractor.getValue(name);

        if (pocketValue >= cost) {
            playerMoneyInteractor.decreasedValue(name, cost);

            player.sendMessage(ChatColor.GOLD + "You are premium customer :)");

            teleportAction.forceTeleportPlayer(player, target);
        } else {
            player.sendMessage(ChatColor.DARK_RED + "So poor check you money first :(");
        }
    }
}
