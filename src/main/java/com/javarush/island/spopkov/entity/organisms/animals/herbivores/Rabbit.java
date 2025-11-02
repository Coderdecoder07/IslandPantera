package com.javarush.island.spopkov.entity.organisms.animals.herbivores;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Rabbit",
        icon = "\uD83D\uDC07",
        maxWeight = 1,
        maxCountInCell = 150,
        packSize = 3,
        maxSpeed = 2,
        maxFoodQuantity = 0.45)
public class Rabbit extends Herbivore {
    public Rabbit(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
