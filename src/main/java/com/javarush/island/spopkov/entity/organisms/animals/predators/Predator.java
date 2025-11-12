package com.javarush.island.spopkov.entity.organisms.animals.predators;
import com.javarush.island.spopkov.config.EatingProbabilityTable;
import com.javarush.island.spopkov.entity.Limit;
import com.javarush.island.spopkov.entity.Organism;
import com.javarush.island.spopkov.entity.Organisms;
import com.javarush.island.spopkov.entity.organisms.animals.Animal;
import com.javarush.island.spopkov.entity.map.Cell;
import com.javarush.island.spopkov.util.Random;

import java.util.Map;

public abstract class Predator extends Animal {

    public Predator(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

    @Override
    public boolean eat(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            String predatorType = this.getClass().getSimpleName();
            double currentWeight = getWeight();
            double maxWeight = getLimit().maxWeight();
            double neededFood = getLimit().maxFoodQuantity();

            if (currentWeight >= maxWeight) {
                return false;
            }

            for (Map.Entry<String, Organisms> entry : currentCell.getResidents().getAll().entrySet()) {
                String preyType = entry.getKey();
                Organisms preyOrganisms = entry.getValue();

                if (preyType.equals(predatorType) || preyType.equals("Grass")) {
                    continue;
                }

                int probability = EatingProbabilityTable.getProbability(predatorType, preyType);
                if (probability <= 0) {
                    continue;
                }

                if (!preyOrganisms.isEmpty() && Random.checkProbability(probability)) {
                    Organism prey = preyOrganisms.iterator().next();

                    double foodWeight = prey.getWeight();
                    double weightGain = Math.min(foodWeight, neededFood);
                    double newWeight = Math.min(currentWeight + weightGain, maxWeight);

                    if (preyOrganisms.remove(prey)) {
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
