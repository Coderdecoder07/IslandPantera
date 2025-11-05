package com.javarush.island.spopkov.entity;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class Organisms {
    private final LinkedHashSet<Organism> organismsSet;
    private Organism sampleOrganism;

    public Organisms() {
        this.organismsSet = new LinkedHashSet<>();
    }

    public int count() {
        return organismsSet.size();
    }

    public boolean isEmpty() {
        return organismsSet.isEmpty();
    }

    public boolean add(Organism organism) {
        if (sampleOrganism == null && organism != null) {
            sampleOrganism = organism;
        }
        return organismsSet.add(organism);
    }

    public boolean remove(Organism organism) {
        return organismsSet.remove(organism);
    }

    public boolean contains(Organism organism) {
        return organismsSet.contains(organism);
    }

    public void addAll(Set<Organism> organisms) {
        if (!organisms.isEmpty() && sampleOrganism == null) {
            sampleOrganism = organisms.iterator().next();
        }
        organismsSet.addAll(organisms);
    }

    public void forEach(Consumer<? super Organism> action) {
        organismsSet.forEach(action);
    }

    public Iterator<Organism> iterator() {
        return organismsSet.iterator();
    }

    public Stream<Organism> stream() {
        return organismsSet.stream();
    }

    public Limit getLimit() {
        return sampleOrganism != null ? sampleOrganism.getLimit() : null;
    }

    public String getIcon() {
        return sampleOrganism != null ? sampleOrganism.getIcon() : "?";
    }

    public String getLetter() {
        return sampleOrganism != null ? sampleOrganism.getLetter() : "?";
    }

    /**
     * Calculates the effective collection size based on the weight and size of the pack
     */
    public double getEffectiveSize() {
        if (isEmpty() || sampleOrganism == null) {
            return 0;
        }

        Limit limit = sampleOrganism.getLimit();
        if (limit.packSize() <= 1) {
            return count();
        }

        double totalWeight = organismsSet.stream()
                .mapToDouble(Organism::getWeight)
                .sum();

        double weightRatio = totalWeight / limit.maxWeight();
        return weightRatio * limit.packSize();
    }
}
