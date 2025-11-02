package com.javarush.island.spopkov.entity.organisms.animals.predators;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Boa",
        icon = "\uD83D\uDC0D",
        maxWeight = 15,
        maxCountInCell = 30,
        packSize = 3,
        maxSpeed = 1,
        maxFoodQuantity = 3)
public class Boa extends Predator {
    public Boa(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
