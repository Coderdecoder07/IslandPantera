package com.javarush.island.spopkov.services;

import com.javarush.island.spopkov.entity.Organism;
import com.javarush.island.spopkov.entity.Organisms;
import com.javarush.island.spopkov.entity.map.Cell;
import com.javarush.island.spopkov.entity.map.GameMap;
import com.javarush.island.spopkov.util.Random;


public class PlantGrowthService {

    private final GameMap gameMap;
    private static final int GROWTH_PROBABILITY = 20;

    public PlantGrowthService(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void growPlants() {
        Cell[][] cells = gameMap.getCells();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                growPlantsOnCell(cell);
            }
        }
    }

    private void growPlantsOnCell(Cell cell) {
        cell.getLock().lock();
        try {
            Organisms grassOrganisms = cell.getResidents().getByType("Grass");
            int currentCount = grassOrganisms.count();

            if (currentCount > 0 && Random.checkProbability(GROWTH_PROBABILITY)) {
                Organism sample = grassOrganisms.iterator().next();
                int maxCountInCell = sample.getLimit().maxCountInCell();

                if (currentCount < maxCountInCell) {
                    Organism newGrass = sample.cloneOrganism();
                    grassOrganisms.add(newGrass);
                }
            }
        } finally {
            cell.getLock().unlock();
        }
    }
}
