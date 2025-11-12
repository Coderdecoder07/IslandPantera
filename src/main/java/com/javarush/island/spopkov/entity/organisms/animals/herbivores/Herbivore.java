package com.javarush.island.spopkov.entity.organisms.animals.herbivores;
import com.javarush.island.spopkov.config.EatingProbabilityTable;
import com.javarush.island.spopkov.entity.Limit;
import com.javarush.island.spopkov.entity.Organism;
import com.javarush.island.spopkov.entity.Organisms;
import com.javarush.island.spopkov.entity.organisms.animals.Animal;
import com.javarush.island.spopkov.entity.map.Cell;
import com.javarush.island.spopkov.util.Random;

import java.util.Map;

public abstract class Herbivore extends Animal {

    public Herbivore(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

    @Override
    public boolean eat(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            String herbivoreType = this.getClass().getSimpleName();
            double currentWeight = getWeight();
            double maxWeight = getLimit().maxWeight();
            double neededFood = getLimit().maxFoodQuantity();

            if (currentWeight >= maxWeight) {
                return false;
            }

            for (Map.Entry<String, Organisms> entry : currentCell.getResidents().getAll().entrySet()) {
                String foodType = entry.getKey();
                Organisms foodOrganisms = entry.getValue();

                if (foodType.equals(herbivoreType)) {
                    continue;
                }

                int probability = EatingProbabilityTable.getProbability(herbivoreType, foodType);
                if (probability <= 0) {
                    continue;
                }

                if (!foodOrganisms.isEmpty() && Random.checkProbability(probability)) {
                    Organism food = foodOrganisms.iterator().next();

                    double foodWeight = food.getWeight();
                    double weightGain = Math.min(foodWeight, neededFood);
                    double newWeight = Math.min(currentWeight + weightGain, maxWeight);

                    if (foodOrganisms.remove(food)) {
                        setWeight(newWeight);
                        return true;
                    }
                }
            }

            return false;
        } finally {
            currentCell.getLock().unlock();
        }
    }
}
