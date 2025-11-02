package com.javarush.island.spopkov.entity.organisms.animals.herbivores;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Buffalo",
        icon = "\uD83D\uDC03",
        maxWeight = 700,
        maxCountInCell = 10,
        packSize = 2,
        maxSpeed = 3,
        maxFoodQuantity = 100)
public class Buffalo extends Herbivore {
    public Buffalo(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
