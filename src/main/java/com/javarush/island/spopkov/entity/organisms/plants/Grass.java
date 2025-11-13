package com.javarush.island.spopkov.entity.organisms.plants;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;
import com.javarush.island.spopkov.entity.Organism;

@OrganismLimitData(name = "Grass",
        icon = "\uD83E\uDEB4",
        maxWeight = 1,
        maxCountInCell = 200,
        packSize = 5,
        maxSpeed = 0,
        maxFoodQuantity = 0)
public class Grass extends Organism {
    public Grass(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
