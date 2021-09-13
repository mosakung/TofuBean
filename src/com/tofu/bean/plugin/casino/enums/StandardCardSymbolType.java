package com.tofu.bean.plugin.casino.enums;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum StandardCardSymbolType {
    DIAMONDS("♦", "diamonds", ChatColor.RED + "♦" + ChatColor.RESET, 1),
    CLUBS("♣", "clubs", ChatColor.AQUA + "♣" + ChatColor.RESET, 2),
    HEART("♥", "heart", ChatColor.RED + "♥" + ChatColor.RESET, 3),
    SPADES("♠", "spades", ChatColor.AQUA + "♠" + ChatColor.RESET, 4);

    private String symbol;
    private String name;
    private String symbolMessage;
    private int priority;

    StandardCardSymbolType(
            String symbol,
            String name,
            String symbolMessage,
            int priority
    ) {
        this.symbol = symbol;
        this.name = name;
        this.symbolMessage = symbolMessage;
        this.priority = priority;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbolMessage() {
        return symbolMessage;
    }

    public int getPriority() {
        return priority;
    }

    public static StandardCardSymbolType getMethod(String name) {
        AtomicReference<StandardCardSymbolType> result = new AtomicReference<>(null);

        Arrays.stream(StandardCardSymbolType.values()).forEach(standardCardSymbolType -> {
            if(standardCardSymbolType.getName().equals(name)) {
                result.set(standardCardSymbolType);
            }
        });

        return result.get();
    }
}
