package com.javarush.island.spopkov.entity.organisms.animals.predators;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Eagle",
        icon = "\uD83E\uDD85",
        maxWeight = 6,
        maxCountInCell = 20,
        packSize = 4,
        maxSpeed = 3,
        maxFoodQuantity = 1)
public class Eagle extends Predator {
    public Eagle(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
