package com.umiomikket.llite.property;

import com.umiomikket.llite.interpolation.EaseCurve;

public class Animatable extends PropertyContainer {
    private final EaseCurve ease;
    private final long duration;

    public Animatable(EaseCurve ease, long duration) {
        this.ease = ease;
        this.duration = duration;
    }

    public EaseCurve getEase() {
        return ease;
    }

    public long getDuration() {
        return duration;
    }
}
