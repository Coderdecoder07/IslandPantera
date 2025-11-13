package com.javarush.island.spopkov.view;

import com.javarush.island.spopkov.entity.Organisms;
import com.javarush.island.spopkov.entity.map.Cell;
import com.javarush.island.spopkov.entity.map.GameMap;

import java.util.HashMap;
import java.util.Map;


public class ConsoleView {

    private final GameMap gameMap;
    private static final int DISPLAY_ROWS = 40;
    private static final int DISPLAY_COLS = 50;
    private static final int CELL_WIDTH = 2;

    public ConsoleView(GameMap gameMap) {
        this.gameMap = gameMap;
    }


    public void displayMap() {
        Cell[][] cells = gameMap.getCells();
        int actualRows = Math.min(gameMap.getRows(), DISPLAY_ROWS);
        int actualCols = Math.min(gameMap.getCols(), DISPLAY_COLS);

        System.out.print("\033[2J\033[H");

        int tableWidth = actualCols * CELL_WIDTH + actualCols;

        String header = "ISLAND MAP (" + gameMap.getRows() + "x" + gameMap.getCols() + ")";
        System.out.println("┌" + "─".repeat(tableWidth) + "┐");
        System.out.println("│" + centerText(header, tableWidth) + "│");
        System.out.println("├" + "─".repeat(tableWidth) + "┤");

        for (int i = 0; i < actualRows; i++) {
            System.out.print("│");
            for (int j = 0; j < actualCols; j++) {
                Cell cell = cells[i][j];
                cell.getLock().lock();
                try {
                    String cellDisplay = getCellDisplay(cell);
                    System.out.print(cellDisplay + "│");
                } finally {
                    cell.getLock().unlock();
                }
            }
            System.out.println();

            if (i < actualRows - 1) {
                System.out.print("├");
                for (int j = 0; j < actualCols; j++) {
                    System.out.print("─".repeat(CELL_WIDTH) + "┼");
                }
                System.out.println("─┤");
            }
        }

        System.out.println("└" + "─".repeat(tableWidth) + "┘");
    }


    private String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }


    private String getCellDisplay(Cell cell) {
        Organisms dominant = findDominantOrganism(cell);

        if (dominant == null || dominant.isEmpty()) {
            return " ";
        }

        String icon = dominant.getIcon();
        if (icon == null) {
            String letter = dominant.getLetter();
            if (letter == null || letter.isEmpty()) {
                return " ";
            }
            return letter.substring(0, 1);
        }

        if (icon.length() == 2 && Character.isHighSurrogate(icon.charAt(0))) {
            return icon;
        }

        if (!icon.isEmpty()) {
            return icon.substring(0, 1);
        }

        return " ";
    }


    private Organisms findDominantOrganism(Cell cell) {
        Organisms dominant = null;
        int maxCount = 0;

        for (Organisms organisms : cell.getResidents().values()) {
            int count = organisms.count();
            if (count > maxCount) {
                maxCount = count;
                dominant = organisms;
            }
        }

        return dominant;
    }


    public void displayStatistics() {
        Map<String, Integer> organismCounts = new HashMap<>();

        Cell[][] cells = gameMap.getCells();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.getLock().lock();
                try {
                    for (Map.Entry<String, Organisms> entry : cell.getResidents().getAll().entrySet()) {
                        String type = entry.getKey();
                        int count = entry.getValue().count();
                        organismCounts.merge(type, count, Integer::sum);
                    }
                } finally {
                    cell.getLock().unlock();
                }
            }
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("ISLAND ORGANISM STATISTICS");
        System.out.println("=".repeat(50));

        if (organismCounts.isEmpty()) {
            System.out.println("Island is empty.");
        } else {
            organismCounts.entrySet().stream()
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                    .forEach(entry -> {
                        String icon = getIconForType(entry.getKey());
                        System.out.printf("%-3s %-20s: %5d%n",
                                icon, entry.getKey(), entry.getValue());
                    });
        }

        int totalOrganisms = organismCounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("-".repeat(50));
        System.out.printf("%-25s: %5d%n", "TOTAL ORGANISMS", totalOrganisms);
        System.out.println("=".repeat(50));
    }


    private String getIconForType(String type) {
        Cell[][] cells = gameMap.getCells();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.getLock().lock();
                try {
                    Organisms organisms = cell.getResidents().getByType(type);
                    if (!organisms.isEmpty()) {
                        return organisms.getIcon();
                    }
                } finally {
                    cell.getLock().unlock();
                }
            }
        }
        return "?";
    }


    public void display() {
        displayMap();
        displayStatistics();
    }
}
