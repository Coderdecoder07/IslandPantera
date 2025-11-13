package com.javarush.island.spopkov.entity;


public record Limit(int maxCountInCell,
                    double maxWeight,
                    int maxSpeed,
                    double maxFoodQuantity,
                    int packSize) {
}
