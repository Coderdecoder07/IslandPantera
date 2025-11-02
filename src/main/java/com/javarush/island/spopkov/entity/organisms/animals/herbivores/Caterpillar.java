package com.javarush.island.spopkov.entity.organisms.animals.herbivores;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Caterpillar",
        icon = "\uD83E\uDD86",
        maxWeight = 0.01,
        maxCountInCell = 1000,
        packSize = 10,
        maxSpeed = 0,
        maxFoodQuantity = 0)
public class Caterpillar extends Herbivore {
    public Caterpillar(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
