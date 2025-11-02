package com.javarush.island.spopkov.entity.organisms.animals.predators;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Bear",
        icon = "\uD83D\uDC3B",
        maxWeight = 500,
        maxCountInCell = 5,
        packSize = 1,
        maxSpeed = 2,
        maxFoodQuantity = 80)
public class Bear extends Predator {
    public Bear(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
