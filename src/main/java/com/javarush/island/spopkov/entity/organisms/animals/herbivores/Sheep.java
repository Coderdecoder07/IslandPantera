package com.javarush.island.spopkov.entity.organisms.animals.herbivores;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Sheep",
        icon = "\uD83D\uDC11",
        maxWeight = 70,
        maxCountInCell = 140,
        packSize = 4,
        maxSpeed = 3,
        maxFoodQuantity = 15)
public class Sheep extends Herbivore {
    public Sheep(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
