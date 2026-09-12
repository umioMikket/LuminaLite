package com.umiomikket.llite.interpolation;

import java.awt.*;

public abstract class InterpolationObjectHandler {
    public abstract Object getDefaultValue();
    public abstract boolean isHandle(Object a, Object b);
    public abstract Object handle(Object a, Object b, float ratio);

    public static final InterpolationObjectHandler
        NUMBER_INTERPOLATION = build(
            0d,
            (a, b) -> a instanceof Number && b instanceof Number,
            (a, b, r) -> {
                double va = ((Number) a).doubleValue(),
                       vb = ((Number) b).doubleValue();
                return va + (vb - va) * r;
            }
        ),
        COLOR_INTERPOLATION = build(
            new Color(0, 0, 0, 0),
            (a, b) -> a instanceof Color && b instanceof Color,
            (a, b, r) -> {
                Color va = (Color) a, vb = (Color) b;
                return new Color(
                    (int) (va.getRed() + (vb.getRed() - va.getRed()) * r),
                    (int) (va.getGreen() + (vb.getGreen() - va.getGreen()) * r),
                    (int) (va.getBlue() + (vb.getBlue() - va.getBlue()) * r),
                    (int) (va.getAlpha() + (vb.getAlpha() - va.getAlpha()) * r)
                );
            }
        );

    @FunctionalInterface
    public interface CheckFunction {
        boolean isHandle(Object a, Object b);
    }

    @FunctionalInterface
    public interface InterpolationFunction {
        Object interpolate(Object a, Object b, float ratio);
    }

    public static Builder builder() {
        return new InterpolationObjectHandler.Builder();
    }

    public static InterpolationObjectHandler build(
        Object defaultValue,
        CheckFunction checkFunction,
        InterpolationFunction interpolationFunction
    ) {
        return builder()
            .defaultValue(defaultValue)
            .check(checkFunction)
            .interpolation(interpolationFunction)
            .build();
    }

    public static class Builder {
        private CheckFunction checkFunction;
        private InterpolationFunction interpolationFunction;
        private Object defaultValue;

        public Builder defaultValue(Object defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder check(CheckFunction checkFunction) {
            this.checkFunction = checkFunction;
            return this;
        }

        public Builder interpolation(InterpolationFunction interpolationFunction) {
            this.interpolationFunction = interpolationFunction;
            return this;
        }

        public InterpolationObjectHandler build() {
            return new InterpolationObjectHandler() {
                @Override
                public Object getDefaultValue() {
                    return defaultValue;
                }

                @Override
                public boolean isHandle(Object a, Object b) {
                    return checkFunction.isHandle(a, b);
                }

                @Override
                public Object handle(Object a, Object b, float ratio) {
                    return interpolationFunction.interpolate(a, b, ratio);
                }
            };
        }
    }
}
