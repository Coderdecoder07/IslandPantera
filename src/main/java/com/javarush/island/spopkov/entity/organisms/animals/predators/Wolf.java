package com.javarush.island.spopkov.entity.organisms.animals.predators;


import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;

@OrganismLimitData(name = "Wolf",
        icon = "\uD83D\uDC3A",
        maxWeight = 50,
        maxCountInCell = 30,
        packSize = 3,
        maxSpeed = 3,
        maxFoodQuantity = 8)
public class Wolf extends Predator {
    public Wolf(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
