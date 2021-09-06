package com.tofu.bean.data.methods.casino.card.standard;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum StandardCardSymbolMethod {
    DIAMONDS("♦", "diamonds", ChatColor.RED + "♦" + ChatColor.RESET, 1),
    CLUBS("♣", "clubs", ChatColor.BLACK + "♣" + ChatColor.RESET, 2),
    HEART("♥", "heart", ChatColor.RED + "♥" + ChatColor.RESET, 3),
    SPADES("♠", "spades", ChatColor.BLACK + "♠" + ChatColor.RESET, 4);

    private String symbol;
    private String name;
    private String symbolMessage;
    private int priority;

    StandardCardSymbolMethod(
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

    public static StandardCardSymbolMethod getMethod(String name) {
        AtomicReference<StandardCardSymbolMethod> result = new AtomicReference<>(null);

        Arrays.stream(StandardCardSymbolMethod.values()).forEach(standardCardSymbolMethod -> {
            if(standardCardSymbolMethod.getName().equals(name)) {
                result.set(standardCardSymbolMethod);
            }
        });

        return result.get();
    }
}
