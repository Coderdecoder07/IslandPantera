package com.javarush.island.spopkov.config;

import com.javarush.island.spopkov.api.annotation.OrganismLimitData;
import com.javarush.island.spopkov.entity.Limit;
import com.javarush.island.spopkov.entity.Organism;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Scanner for searching all classes of organisms with the OrganismLimitData annotation
 */
public class OrganismScanner {
    public static List<Class<? extends Organism>> findOrganismClasses() {
        List<Class<? extends Organism>> classes = new ArrayList<>();

        String[] classNames = {
                // Predators
                "com.javarush.island.spopkov.entity.organisms.animals.predators.Wolf",
                "com.javarush.island.spopkov.entity.organisms.animals.predators.Boa",
                "com.javarush.island.spopkov.entity.organisms.animals.predators.Fox",
                "com.javarush.island.spopkov.entity.organisms.animals.predators.Bear",
                "com.javarush.island.spopkov.entity.organisms.animals.predators.Eagle",

                // Herbivores
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Horse",
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Deer",
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Rabbit",
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Mouse",
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Goat",
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Sheep",
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Boar",
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Buffalo",
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Duck",
                "com.javarush.island.spopkov.entity.organisms.animals.herbivores.Caterpillar",

                // Plants
                "com.javarush.island.spopkov.entity.organisms.plants.Grass"
        };

        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if (Organism.class.isAssignableFrom(clazz) &&
                        clazz.isAnnotationPresent(OrganismLimitData.class)) {
                    @SuppressWarnings("unchecked")
                    Class<? extends Organism> organismClass = (Class<? extends Organism>) clazz;
                    classes.add(organismClass);
                }
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found: " + className);
            }
        }

        return classes;
    }

    public static List<Organism> createPrototypes() {
        List<Organism> prototypes = new ArrayList<>();
        List<Class<? extends Organism>> classes = findOrganismClasses();

        for (Class<? extends Organism> organismClass : classes) {
            try {
                Organism prototype = createPrototype(organismClass);
                if (prototype != null) {
                    prototypes.add(prototype);
                }
            } catch (Exception e) {
                System.err.println("Prototype creating error " + organismClass.getSimpleName() + ": " + e.getMessage());
            }
        }

        return prototypes;
    }


    private static Organism createPrototype(Class<? extends Organism> organismClass) throws Exception {
        OrganismLimitData annotation = organismClass.getAnnotation(OrganismLimitData.class);
        if (annotation == null) {
            return null;
        }

        // Extracting data from annotations
        String name = annotation.name();
        String icon = annotation.icon();
        int maxCountInCell = annotation.maxCountInCell();
        double maxWeight = annotation.maxWeight();
        int maxSpeed = annotation.maxSpeed();
        double maxFoodQuantity = annotation.maxFoodQuantity();
        int packSize = annotation.packSize();

        // Create a Limit based on the pack size
        Limit limit = new Limit(
                maxCountInCell / packSize,
                maxWeight * packSize,
                maxSpeed,
                maxFoodQuantity * packSize,
                packSize
        );

        Constructor<? extends Organism> constructor = organismClass.getConstructor(
                String.class, String.class, Limit.class
        );

        return constructor.newInstance(name, icon, limit);
    }
}
