package com.javarush.island.spopkov.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OrganismLimitData {
    String name();
    String icon();
    int maxCountInCell();
    double maxWeight();
    int maxSpeed();
    double maxFoodQuantity();
    int packSize() default 1;

}