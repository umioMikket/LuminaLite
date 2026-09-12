package com.umiomikket.llite.interpolation;

@FunctionalInterface
public interface EaseCurve {
    float ease(float ratio);
}
