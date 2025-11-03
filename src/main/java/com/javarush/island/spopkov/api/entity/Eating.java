package com.javarush.island.spopkov.api.entity;

import com.javarush.island.spopkov.entity.map.Cell;

public interface Eating {
    boolean eat(Cell currentCell); // returns true if the eating was successful
}
