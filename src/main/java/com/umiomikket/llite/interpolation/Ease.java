package com.umiomikket.llite.interpolation;

import java.util.Objects;

public class Ease {
    public static final EaseCurve LINEAR = ratio -> ratio;

    public static final EaseCurve EASE_IN_QUAD = ratio -> ratio * ratio;
    public static final EaseCurve EASE_OUT_QUAD = ratio -> 1.0f - (1.0f - ratio) * (1.0f - ratio);
    public static final EaseCurve EASE_IN_OUT_QUAD = ratio ->
        ratio < 0.5f
        ? 2.0f * ratio * ratio
        : 1.0f - (float) Math.pow(-2.0f * ratio + 2.0f, 2) / 2.0f;

    public static final EaseCurve EASE_IN_CUBIC = ratio -> ratio * ratio * ratio;
    public static final EaseCurve EASE_OUT_CUBIC = ratio -> 1.0f - (float) Math.pow(1.0f - ratio, 3);
    public static final EaseCurve EASE_IN_OUT_CUBIC = ratio ->
        ratio < 0.5f
        ? 4.0f * ratio * ratio * ratio
        : 1.0f - (float) Math.pow(-2.0f * ratio + 2.0f, 3) / 2.0f;

    public static final EaseCurve EASE_IN_SINE = ratio ->
        1.0f - (float) Math.cos((ratio * Math.PI) / 2.0);

    public static final EaseCurve EASE_OUT_SINE = ratio ->
        (float) Math.sin((ratio * Math.PI) / 2.0);

    public static final EaseCurve EASE_IN_OUT_SINE = ratio ->
        -((float) Math.cos(Math.PI * ratio) - 1.0f) / 2.0f;

    public static final EaseCurve EASE_IN_EXPO = ratio ->
        ratio == 0.0f ? 0.0f : (float) Math.pow(2.0, 10.0 * ratio - 10.0);

    public static final EaseCurve EASE_OUT_EXPO = ratio ->
        ratio == 1.0f ? 1.0f : 1.0f - (float) Math.pow(2.0, -10.0 * ratio);

    public static final EaseCurve EASE_OUT_ELASTIC = ratio -> {
        if (ratio == 0.0f) return 0.0f;
        if (ratio == 1.0f) return 1.0f;
        double c4 = (2.0 * Math.PI) / 3.0;
        return (float) (Math.pow(2.0, -10.0 * ratio) * Math.sin((ratio * 10.0 - 0.75) * c4) + 1.0);
    };

    public static final EaseCurve EASE_OUT_BOUNCE = ratio -> {
        float n1 = 7.5625f;
        float d1 = 2.75f;

        if (ratio < 1.0f / d1) {
            return n1 * ratio * ratio;
        } else if (ratio < 2.0f / d1) {
            float r = ratio - 1.5f / d1;
            return n1 * r * r + 0.75f;
        } else if (ratio < 2.5f / d1) {
            float r = ratio - 2.25f / d1;
            return n1 * r * r + 0.9375f;
        } else {
            float r = ratio - 2.625f / d1;
            return n1 * r * r + 0.984375f;
        }
    };

    public static EaseCurve easeInPower(double power) {
        return ratio -> (float) Math.pow(ratio, power);
    }

    public static EaseCurve invert(EaseCurve interpolation) {
        Objects.requireNonNull(interpolation);
        return ratio -> 1.0f - interpolation.ease(1.0f - ratio);
    }

    public static float ratioOf(float range, float current) {
        if (range < 0) throw new IllegalArgumentException();
        return current / range;
    }

    public static float ratioOf(float start, float end, float current) {
        if (!(current >= start && current <= end) || !(current >= end && current <= start))
            throw new IllegalArgumentException();
        return current / (start + end);
    }
}
