package com.javarush.island.spopkov.entity.organisms.animals.herbivores;
import com.javarush.island.spopkov.entity.Limit;
import com.javarush.island.spopkov.entity.organisms.animals.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
