package com.javarush.island.spopkov.entity.organisms.animals;
import com.javarush.island.spopkov.api.entity.Eating;
import com.javarush.island.spopkov.api.entity.Movable;
import com.javarush.island.spopkov.entity.Limit;
import com.javarush.island.spopkov.entity.Organism;
import com.javarush.island.spopkov.entity.Organisms;
import com.javarush.island.spopkov.entity.map.Cell;

public abstract class Animal extends Organism implements Eating, Movable {

    public Animal(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

    @Override
    public boolean eat(Cell currentCell) {
        return false;
    }

    @Override
    public boolean move(Cell startCell) {
        int maxSpeed = getLimit().maxSpeed();
        if (maxSpeed <= 0) {
            return false;
        }

        Cell targetCell = startCell.selectRandomNeighbor(maxSpeed);
        if (targetCell == startCell) {
            return false;
        }

        Cell firstLock = startCell.hashCode() < targetCell.hashCode() ? startCell : targetCell;
        Cell secondLock = startCell.hashCode() < targetCell.hashCode() ? targetCell : startCell;

        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {
                String organismType = this.getClass().getSimpleName();
                Organisms targetOrganisms = targetCell.getResidents().getByType(organismType);
                int currentCount = targetOrganisms.count();
                int maxCountInCell = getLimit().maxCountInCell();

                if (currentCount >= maxCountInCell) {
                    return false;
                }

                Organisms startOrganisms = startCell.getResidents().getByType(organismType);
                if (startOrganisms.remove(this)) {
                    targetOrganisms.add(this);
                    return true;
                }

                return false;
            } finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
        }
    }

    @Override
    public boolean spawn(Cell currentCell) {
        // logic
        return false;
    }
}
