package com.javarush.island.spopkov.services;

import com.javarush.island.spopkov.entity.map.GameMap;
import com.javarush.island.spopkov.view.ConsoleView;


public class StatisticsService {

    private final ConsoleView consoleView;

    public StatisticsService(GameMap gameMap) {
        this.consoleView = new ConsoleView(gameMap);
    }

    public void printStatistics() {
        consoleView.display();
    }
}
