package com.javarush.island.spopkov.entity.organisms.animals.predators;
import com.javarush.island.spopkov.entity.Limit;
import com.javarush.island.spopkov.entity.organisms.animals.Animal;

public abstract class Predator extends Animal {
    public Predator(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
