package com.javarush.island.spopkov.entity.organisms.animals.herbivores;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Goat",
        icon = "\uD83D\uDC10",
        maxWeight = 60,
        maxCountInCell = 140,
        packSize = 4,
        maxSpeed = 3,
        maxFoodQuantity = 10)
public class Goat extends Herbivore {
    public Goat(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
