package com.javarush.island.spopkov.entity;


import lombok.Getter;


@Getter
public record Limit(int maxCountInCell,
                    double maxWeight,
                    int maxSpeed,
                    double maxFoodQuantity,
                    int packSize) {
}
