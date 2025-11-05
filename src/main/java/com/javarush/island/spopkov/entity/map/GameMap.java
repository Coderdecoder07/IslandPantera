package com.javarush.island.spopkov.entity.map;

import com.javarush.island.spopkov.entity.Organism;
import com.javarush.island.spopkov.entity.Organisms;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;


public class GameMap {
    private final Cell[][] grid;

    @Getter
    private final int rows;

    @Getter
    private final int cols;

    public GameMap(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Map size must be positive");
        }
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].initializeNeighbors(this, i, j);
            }
        }
    }

    public Cell[][] getCells() {
        return grid;
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return null;
        }
        return grid[row][col];
    }

    public Organisms getAllOrganisms() {
        Organisms result = new Organisms();
        Set<Organism> allSet = new HashSet<>();

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                cell.getResidents().values().forEach(organisms -> {
                    organisms.forEach(allSet::add);
                });
            }
        }

        result.addAll(allSet);
        return result;
    }
}
