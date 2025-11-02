package com.javarush.island.spopkov.entity.organisms.animals.herbivores;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Mouse",
        icon = "\uD83D\uDC01",
        maxWeight = 0.05,
        maxCountInCell = 500,
        packSize = 5,
        maxSpeed = 1,
        maxFoodQuantity = 0.01)
public class Mouse extends Herbivore {
    public Mouse(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
