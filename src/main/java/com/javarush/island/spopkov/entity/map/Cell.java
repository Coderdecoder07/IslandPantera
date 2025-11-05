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

    private final List<Cell> neighbors = new ArrayList<>(4);


    public void initializeNeighbors(GameMap map, int row, int col) {
        neighbors.clear();
        Cell[][] grid = map.getCells();


        if (row > 0) {
            neighbors.add(grid[row - 1][col]);
        }

        if (col > 0) {
            neighbors.add(grid[row][col - 1]);
        }

        if (row < map.getRows() - 1) {
            neighbors.add(grid[row + 1][col]);
        }

        if (col < map.getCols() - 1) {
            neighbors.add(grid[row][col + 1]);
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
