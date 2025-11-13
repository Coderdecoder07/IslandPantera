package com.javarush.island.spopkov.services;

import com.javarush.island.spopkov.entity.map.GameMap;
import com.javarush.island.spopkov.repository.GameMapInitializer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameWorker {

    private final GameMap gameMap;
    private final ScheduledExecutorService scheduledPool;
    private final OrganismWorker organismWorker;
    private final StatisticsService statisticsService;
    private final PlantGrowthService plantGrowthService;

    private static final int PERIOD_SECONDS = 1;

    public GameWorker(GameMap gameMap) {
        this.gameMap = gameMap;
        this.scheduledPool = Executors.newScheduledThreadPool(3);
        this.organismWorker = new OrganismWorker(gameMap);
        this.statisticsService = new StatisticsService(gameMap);
        this.plantGrowthService = new PlantGrowthService(gameMap);
    }


    public void start() {
        GameMapInitializer.initializeMap(gameMap);
        scheduledPool.scheduleAtFixedRate(
                organismWorker::processOrganisms,
                0,
                PERIOD_SECONDS,
                TimeUnit.SECONDS
        );

        scheduledPool.scheduleAtFixedRate(
                plantGrowthService::growPlants,
                0,
                PERIOD_SECONDS,
                TimeUnit.SECONDS
        );

        scheduledPool.scheduleAtFixedRate(
                statisticsService::printStatistics,
                0,
                PERIOD_SECONDS,
                TimeUnit.SECONDS
        );

        System.out.println("Island simulation launched!");
    }

    public void stop() {
        organismWorker.shutdown();
        scheduledPool.shutdown();
        try {
            if (!scheduledPool.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduledPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduledPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("The simulation has stopped.");
    }
}
