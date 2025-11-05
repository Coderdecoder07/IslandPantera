package com.javarush.island.spopkov.entity;

import com.javarush.island.spopkov.api.entity.Reproducible;
import com.javarush.island.spopkov.entity.map.Cell;
import lombok.Getter;


public abstract class Organism implements Cloneable, Reproducible {
    private final String type = this.getClass().getSimpleName();
    @Getter
    private final String letter = type.substring(0, 1);
    @Getter
    private double weight;
    @Getter
    private Limit limit;
    @Getter
    private String name;
    @Getter
    private String icon;

    protected Organism(String name, String icon, Limit limit) {
        this.name = name;
        this.icon = icon;
        this.limit = limit;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean spawn(Cell currentCell) {
        return false;
    }
}
