package com.javarush.island.spopkov.entity;

import com.javarush.island.spopkov.api.entity.Reproducible;
import com.javarush.island.spopkov.entity.map.Cell;


public class Organism implements Cloneable, Reproducible {
    @Override
    public boolean spawn(Cell currentCell) {
        return false;
    }
}
