package com.tofu.bean.data.methods.casino.card.standard;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum StandardCardPointMethod {
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

    StandardCardPointMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static StandardCardPointMethod getMethod(String name) {
        AtomicReference<StandardCardPointMethod> result = new AtomicReference<>(null);

        Arrays.stream(StandardCardPointMethod.values()).forEach(standardCardPointMethod -> {
            if(standardCardPointMethod.getName().equals(name)) {
                result.set(standardCardPointMethod);
            }
        });

        return result.get();
    }
}
