package com.javarush.island.spopkov.api.entity;

import com.javarush.island.spopkov.entity.map.Cell;

public interface Movable {
    boolean move(Cell startCell); // returns true if the move was successful
}
