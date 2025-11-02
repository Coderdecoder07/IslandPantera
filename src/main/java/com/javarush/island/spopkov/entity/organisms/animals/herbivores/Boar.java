package com.javarush.island.spopkov.entity.organisms.animals.herbivores;
import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;


@OrganismLimitData(name = "Boar",
        icon = "\uD83D\uDC17",
        maxWeight = 400,
        maxCountInCell = 50,
        packSize = 5,
        maxSpeed = 2,
        maxFoodQuantity = 50)
public class Boar extends Herbivore {
    public Boar(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }
}
