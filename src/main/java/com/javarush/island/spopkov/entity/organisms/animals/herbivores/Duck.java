package com.javarush.island.spopkov.entity.organisms.animals.herbivores;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Duck",
        icon = "\uD83E\uDD86",
        maxWeight = 1,
        maxCountInCell = 200,
        packSize = 4,
        maxSpeed = 4,
        maxFoodQuantity = 0.15)
public class Duck extends Herbivore {
    public Duck(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
