package com.javarush.island.spopkov.entity.organisms.animals.herbivores;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Horse",
        icon = "\uD83D\uDC0E",
        maxWeight = 400,
        maxCountInCell = 20,
        packSize = 4,
        maxSpeed = 4,
        maxFoodQuantity = 60)
public class Horse extends Herbivore {
    public Horse(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
