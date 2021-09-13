package com.tofu.bean.plugin.casino.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum StandardCardPointType {
    ACE("ace"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    NINE("nine"),
    TEN("ten"),
    JACK("jack"),
    QUEEN("queen"),
    KING("king");

    private final String name;

    StandardCardPointType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static StandardCardPointType getMethod(String name) {
        AtomicReference<StandardCardPointType> result = new AtomicReference<>(null);

        Arrays.stream(StandardCardPointType.values()).forEach(standardCardPointType -> {
            if(standardCardPointType.getName().equals(name)) {
                result.set(standardCardPointType);
            }
        });

        return result.get();
    }
}
