package com.javarush.island.spopkov.services;

import com.javarush.island.spopkov.entity.Organism;
import com.javarush.island.spopkov.entity.Organisms;
import com.javarush.island.spopkov.entity.map.Cell;
import com.javarush.island.spopkov.entity.map.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class OrganismWorker {

    private final GameMap gameMap;
    private final ExecutorService executorPool;

    private static final int THREAD_POOL_SIZE = 4;

    public OrganismWorker(GameMap gameMap) {
        this.gameMap = gameMap;
        this.executorPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public void processOrganisms() {
        Cell[][] cells = gameMap.getCells();
        List<Runnable> tasks = new ArrayList<>();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.getLock().lock();
                try {
                    cell.getResidents().shuffleOrder();
                    for (Organisms organisms : cell.getResidents().values()) {
                        organisms.forEach(organism -> {
                            tasks.add(() -> processOrganism(organism, cell));
                        });
                    }
                } finally {
                    cell.getLock().unlock();
                }
            }
        }

        for (Runnable task : tasks) {
            executorPool.submit(task);
        }
    }

    private void processOrganism(Organism organism, Cell cell) {
        if (organism instanceof com.javarush.island.spopkov.api.entity.Eating) {
            ((com.javarush.island.spopkov.api.entity.Eating) organism).eat(cell);
        }
        if (organism instanceof com.javarush.island.spopkov.api.entity.Movable) {
            ((com.javarush.island.spopkov.api.entity.Movable) organism).move(cell);
        }
        organism.spawn(cell);
    }

    public void shutdown() {
        executorPool.shutdown();
        try {
            if (!executorPool.awaitTermination(5, TimeUnit.SECONDS)) {
                executorPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
