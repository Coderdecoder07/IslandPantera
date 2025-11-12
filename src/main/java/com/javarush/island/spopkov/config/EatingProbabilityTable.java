package com.javarush.island.spopkov.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Table of animal feeding probabilities
 * Stores the probability that a predator animal will eat a prey animal
 */
public class EatingProbabilityTable {

    private static final Map<String, Map<String, Integer>> PROBABILITY_TABLE = new HashMap<>();

    static {
        initializeTable();
    }

    private static void initializeTable() {
        // Wolf
        Map<String, Integer> wolf = new HashMap<>();
        wolf.put("Horse", 10);
        wolf.put("Deer", 15);
        wolf.put("Rabbit", 60);
        wolf.put("Mouse", 80);
        wolf.put("Goat", 60);
        wolf.put("Sheep", 70);
        wolf.put("Boar", 15);
        wolf.put("Buffalo", 10);
        wolf.put("Duck", 40);
        PROBABILITY_TABLE.put("Wolf", wolf);

        // Boa
        Map<String, Integer> boa = new HashMap<>();
        boa.put("Fox", 15);
        boa.put("Rabbit", 20);
        boa.put("Mouse", 40);
        boa.put("Duck", 10);
        PROBABILITY_TABLE.put("Boa", boa);

        // Fox
        Map<String, Integer> fox = new HashMap<>();
        fox.put("Rabbit", 70);
        fox.put("Mouse", 90);
        fox.put("Duck", 60);
        fox.put("Caterpillar", 40);
        PROBABILITY_TABLE.put("Fox", fox);

        // Bear
        Map<String, Integer> bear = new HashMap<>();
        bear.put("Boa", 80);
        bear.put("Horse", 40);
        bear.put("Deer", 80);
        bear.put("Rabbit", 80);
        bear.put("Mouse", 90);
        bear.put("Goat", 70);
        bear.put("Sheep", 70);
        bear.put("Boar", 50);
        bear.put("Buffalo", 20);
        bear.put("Duck", 10);
        PROBABILITY_TABLE.put("Bear", bear);

        // Eagle
        Map<String, Integer> eagle = new HashMap<>();
        eagle.put("Fox", 10);
        eagle.put("Rabbit", 90);
        eagle.put("Mouse", 90);
        eagle.put("Duck", 80);
        PROBABILITY_TABLE.put("Eagle", eagle);

        // Horse
        Map<String, Integer> horse = new HashMap<>();
        horse.put("Grass", 100);
        PROBABILITY_TABLE.put("Horse", horse);

        // Deer
        Map<String, Integer> deer = new HashMap<>();
        deer.put("Grass", 100);
        PROBABILITY_TABLE.put("Deer", deer);

        // Rabbit
        Map<String, Integer> rabbit = new HashMap<>();
        rabbit.put("Grass", 100);
        PROBABILITY_TABLE.put("Rabbit", rabbit);

        // Mouse
        Map<String, Integer> mouse = new HashMap<>();
        mouse.put("Caterpillar", 90);
        mouse.put("Grass", 100);
        PROBABILITY_TABLE.put("Mouse", mouse);

        // Goat
        Map<String, Integer> goat = new HashMap<>();
        goat.put("Grass", 100);
        PROBABILITY_TABLE.put("Goat", goat);

        // Sheep
        Map<String, Integer> sheep = new HashMap<>();
        sheep.put("Grass", 100);
        PROBABILITY_TABLE.put("Sheep", sheep);

        // Boar
        Map<String, Integer> boar = new HashMap<>();
        boar.put("Mouse", 50);
        boar.put("Caterpillar", 90);
        boar.put("Grass", 100);
        PROBABILITY_TABLE.put("Boar", boar);

        // Buffalo
        Map<String, Integer> buffalo = new HashMap<>();
        buffalo.put("Grass", 100);
        PROBABILITY_TABLE.put("Buffalo", buffalo);

        // Duck
        Map<String, Integer> duck = new HashMap<>();
        duck.put("Caterpillar", 90);
        duck.put("Grass", 100);
        PROBABILITY_TABLE.put("Duck", duck);

        // Caterpillar
        Map<String, Integer> caterpillar = new HashMap<>();
        caterpillar.put("Grass", 100);
        PROBABILITY_TABLE.put("Caterpillar", caterpillar);
    }

    /**
     * Get the eating probability (in percent 0–100)
     * @param predatorType the type of predator/animal (e.g., "Wolf")
     * @param preyType the type of prey/food (e.g., "Rabbit")
     * @return the probability in percent (0–100), or 0 if such a pair does not exist in the table
     */
    public static int getProbability(String predatorType, String preyType) {
        Map<String, Integer> predatorMap = PROBABILITY_TABLE.get(predatorType);
        if (predatorMap == null) {
            return 0;
        }
        return predatorMap.getOrDefault(preyType, 0);
    }

    /**
     * Get the eating probability based on classes
     * @param predatorClass the class of the predator
     * @param preyClass the class of the prey
     * @return the probability in percent (0–100)
     */
    public static int getProbability(Class<?> predatorClass, Class<?> preyClass) {
        return getProbability(predatorClass.getSimpleName(), preyClass.getSimpleName());
    }

    /**
     * Check if the predator can eat the prey
     * @param predatorType the type of predator
     * @param preyType the type of prey
     * @return true if the probability is greater than 0
     */
    public static boolean canEat(String predatorType, String preyType) {
        return getProbability(predatorType, preyType) > 0;
    }
}
