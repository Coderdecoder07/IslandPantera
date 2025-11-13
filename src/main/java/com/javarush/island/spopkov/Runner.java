package com.javarush.island.spopkov;

import com.javarush.island.spopkov.entity.map.GameMap;
import com.javarush.island.spopkov.services.GameWorker;

public class Runner {

    private static final int MAP_ROWS = 40;
    private static final int MAP_COLS = 50;

    public static void main(String[] args) {
        System.out.println("Launching the island simulation...");

        GameMap gameMap = new GameMap(MAP_ROWS, MAP_COLS);

        GameWorker gameWorker = new GameWorker(gameMap);
        gameWorker.start();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        gameWorker.stop();

        System.out.println("The program is completed.");
    }
}
