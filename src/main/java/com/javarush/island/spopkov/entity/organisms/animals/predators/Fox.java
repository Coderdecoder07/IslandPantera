package com.javarush.island.spopkov.entity.organisms.animals.predators;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Fox",
        icon = "\uD83E\uDD8A",
        maxWeight = 8,
        maxCountInCell = 30,
        packSize = 3,
        maxSpeed = 2,
        maxFoodQuantity = 2)
public class Fox extends Predator {
    public Fox(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
