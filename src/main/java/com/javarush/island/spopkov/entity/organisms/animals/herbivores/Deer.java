package com.javarush.island.spopkov.entity.organisms.animals.herbivores;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Deer",
        icon = "\uD83E\uDD8C",
        maxWeight = 300,
        maxCountInCell = 20,
        packSize = 4,
        maxSpeed = 4,
        maxFoodQuantity = 50)
public class Deer extends Herbivore {
    public Deer(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
