package com.javarush.island.spopkov.entity.map;

import com.javarush.island.spopkov.entity.Organisms;
import com.javarush.island.spopkov.util.Random;

import java.util.LinkedHashMap;
import java.util.Map;


public class ResidentMap {
    private final LinkedHashMap<String, Organisms> organismsByType;
    private static final int ROTATION_CHANCE = 1;

    public ResidentMap() {
        this.organismsByType = new LinkedHashMap<>();
    }

    public Organisms getByType(String typeName) {
        return organismsByType.computeIfAbsent(typeName, k -> new Organisms());
    }

    public Organisms getByType(Class<?> type) {
        return getByType(type.getSimpleName());
    }

    public void add(com.javarush.island.spopkov.entity.Organism organism) {
        String typeName = organism.getClass().getSimpleName();
        getByType(typeName).add(organism);
    }

    public boolean remove(com.javarush.island.spopkov.entity.Organism organism) {
        String typeName = organism.getClass().getSimpleName();
        Organisms organisms = organismsByType.get(typeName);
        return organisms != null && organisms.remove(organism);
    }

    public Map<String, Organisms> getAll() {
        return organismsByType;
    }

    public java.util.Collection<Organisms> values() {
        return organismsByType.values();
    }

    public void shuffleOrder() {
        if (organismsByType.size() > 1 && Random.checkProbability(ROTATION_CHANCE)) {
            synchronized (organismsByType) {
                Map.Entry<String, Organisms> firstEntry = organismsByType.entrySet().iterator().next();
                String key = firstEntry.getKey();
                Organisms value = firstEntry.getValue();
                organismsByType.remove(key);
                organismsByType.put(key, value);
            }
        }
    }

    public int size() {
        return organismsByType.size();
    }

    public boolean isEmpty() {
        return organismsByType.isEmpty();
    }
}
