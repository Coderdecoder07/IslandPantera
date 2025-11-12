package com.javarush.island.spopkov.entity;
import com.javarush.island.spopkov.api.entity.Reproducible;
import com.javarush.island.spopkov.entity.map.Cell;
import com.javarush.island.spopkov.util.Random;
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
        this.weight = Random.nextDouble(limit.maxWeight() / 2.0, limit.maxWeight());
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return icon;
    }

    @Override
    public boolean spawn(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            String organismType = this.getClass().getSimpleName();
            Organisms organisms = currentCell.getResidents().getByType(organismType);

            int currentCount = organisms.count();
            int maxCountInCell = getLimit().maxCountInCell();

            if (currentCount >= maxCountInCell) {
                return false;
            }

            if (currentCount < 2) {
                return false;
            }

            if (!Random.checkProbability(50)) {
                return false;
            }

            Organism offspring = this.cloneOrganism();

            if (currentCount + 1 <= maxCountInCell) {
                organisms.add(offspring);
                return true;
            }

            return false;
        } finally {
            currentCell.getLock().unlock();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Organism clone = (Organism) super.clone();
        clone.weight = Random.nextDouble(limit.maxWeight() / 2.0, limit.maxWeight());
        return clone;
    }


    @SuppressWarnings("unchecked")
    public <T extends Organism> T cloneOrganism() {
        try {
            return (T) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Organism cloning error", e);
        }
    }
}
