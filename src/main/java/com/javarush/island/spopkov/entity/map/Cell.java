package com.javarush.island.spopkov.entity.map;

import com.javarush.island.spopkov.entity.Organisms;
import com.javarush.island.spopkov.util.Random;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


public class Cell {

    @Getter
    private final ReentrantLock lock = new ReentrantLock(true);

    @Getter
    private final ResidentMap residents = new ResidentMap();

    private final List<Cell> neighbors = new ArrayList<>(8); // максимум 8 соседей (4 прямых + 4 диагональных)


    public void initializeNeighbors(GameMap map, int row, int col) {
        neighbors.clear();
        Cell[][] grid = map.getCells();
        int maxRows = map.getRows();
        int maxCols = map.getCols();

        // Straight directions (4 sides)

        // Top cell
        if (row > 0) {
            neighbors.add(grid[row - 1][col]);
        }
        // Left cell
        if (col > 0) {
            neighbors.add(grid[row][col - 1]);
        }
        // Bottom cell
        if (row < maxRows - 1) {
            neighbors.add(grid[row + 1][col]);
        }
        // Right cell
        if (col < maxCols - 1) {
            neighbors.add(grid[row][col + 1]);
        }

        // Diagonal directions (4 corners)

        // Upper left corner
        if (row > 0 && col > 0) {
            neighbors.add(grid[row - 1][col - 1]);
        }
        // Upper right corner
        if (row > 0 && col < maxCols - 1) {
            neighbors.add(grid[row - 1][col + 1]);
        }
        // Bottom left corner
        if (row < maxRows - 1 && col > 0) {
            neighbors.add(grid[row + 1][col - 1]);
        }
        // Bottom right corner
        if (row < maxRows - 1 && col < maxCols - 1) {
            neighbors.add(grid[row + 1][col + 1]);
        }
    }


    public Cell selectRandomNeighbor(int steps) {
        if (neighbors.isEmpty()) {
            return this;
        }

        Cell target = this;
        List<Cell> visited = new ArrayList<>();

        for (int i = 0; i < steps && !target.neighbors.isEmpty(); i++) {
            List<Cell> available = target.neighbors.stream()
                    .filter(cell -> !visited.contains(cell))
                    .toList();

            if (available.isEmpty()) {
                break;
            }

            int randomIndex = Random.nextInt(0, available.size());
            target = available.get(available.size() - 1 - randomIndex);
            visited.add(target);
        }

        return target;
    }

    public int getNeighborCount() {
        return neighbors.size();
    }

    public List<Cell> getNeighbors() {
        return Collections.unmodifiableList(neighbors);
    }

    @Override
    public String toString() {
        return residents.values().stream()
                .filter(organisms -> !organisms.isEmpty())
                .sorted((a, b) -> Integer.compare(b.count(), a.count()))
                .map(Organisms::getLetter)
                .collect(Collectors.joining());
    }
}
