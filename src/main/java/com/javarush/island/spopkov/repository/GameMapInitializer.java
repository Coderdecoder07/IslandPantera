package com.javarush.island.spopkov.repository;

import com.javarush.island.spopkov.config.OrganismScanner;
import com.javarush.island.spopkov.entity.Organism;
import com.javarush.island.spopkov.entity.map.Cell;
import com.javarush.island.spopkov.entity.map.GameMap;
import com.javarush.island.spopkov.util.Random;

import java.util.List;


public class GameMapInitializer {

    private static final int INITIAL_FILL_PERCENT = 50;

    public static void initializeMap(GameMap gameMap) {
        List<Organism> prototypes = OrganismScanner.createPrototypes();

        Cell[][] cells = gameMap.getCells();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                fillCell(cell, prototypes);
            }
        }
    }


    private static void fillCell(Cell cell, List<Organism> prototypes) {
        cell.getLock().lock();
        try {
            for (Organism prototype : prototypes) {
                if (Random.checkProbability(INITIAL_FILL_PERCENT)) {
                    int maxCountInCell = prototype.getLimit().maxCountInCell();
                    int countToAdd = Random.nextInt(1, maxCountInCell / 2 + 1);

                    for (int i = 0; i < countToAdd; i++) {
                        Organism organism = prototype.cloneOrganism();
                        cell.getResidents().add(organism);
                    }
                }
            }
        } finally {
            cell.getLock().unlock();
        }
    }

    public static void clearMap(GameMap gameMap) {
        Cell[][] cells = gameMap.getCells();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.getLock().lock();
                try {
                    cell.getResidents().getAll().clear();
                } finally {
                    cell.getLock().unlock();
                }
            }
        }
    }
}
