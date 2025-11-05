package com.javarush.island.spopkov.util;

import java.util.concurrent.ThreadLocalRandom;


public final class Random {

    private Random() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static int nextInt(int min, int max) {
        if (min >= max) {
            return min;
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static double nextDouble(double min, double max) {
        if (min >= max) {
            return min;
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static boolean checkProbability(int probability) {
        if (probability <= 0) return false;
        if (probability >= 100) return true;
        return nextInt(0, 100) < probability;
    }
}
