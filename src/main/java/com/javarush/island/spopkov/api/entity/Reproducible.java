package com.javarush.island.spopkov.api.entity;

import com.javarush.island.spopkov.entity.map.Cell;

public interface Reproducible {
    boolean spawn(Cell currentCell); // returns true if the reproduction was successful
}
