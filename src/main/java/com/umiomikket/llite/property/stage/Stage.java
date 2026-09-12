package com.umiomikket.llite.property.stage;

import com.umiomikket.llite.property.Animatable;
import com.umiomikket.llite.interpolation.EaseCurve;
import com.umiomikket.llite.property.Property;

import java.util.HashMap;
import java.util.Map;

public class Stage extends Animatable {
    private final StageFunction function;

    public Stage(
        StageFunction function,
        EaseCurve ease, long duration
    ) {
        super(ease, duration);
        this.function = function;
    }

    public boolean isStage(
        boolean isFocused, boolean isHovered, boolean isPressed,
        boolean isEnabled, boolean isDisabled
    ) {
        return function.function(isFocused, isHovered, isPressed, isEnabled, isDisabled);
    }

    public boolean isStage(StageFunction function) {
        if (function == null) return false;

        for (int i = 0; i < 64; i++) {
            boolean isFocused = (i & 1) != 0,
                    isHovered = (i & 2) != 0,
                    isPressed = (i & 4) != 0,
                    isEnabled = (i & 8) != 0,
                    isDisabled = (i & 16) != 0;
            if (
                this.function.function(isFocused, isHovered, isPressed, isEnabled, isDisabled) !=
                function.function(isFocused, isHovered, isPressed, isEnabled, isDisabled)
            ) return false;
        }

        return true;
    }

    public StageFunction getFunction() {
        return function;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<String, Property<?>> properties = new HashMap<>();
        private StageFunction function;
        private EaseCurve ease;
        private long duration;

        public Builder property(String name, Property<?> property) {
            properties.put(name, property);
            return this;
        }

        public Builder property(String name, Object value) {
            properties.put(name, new Property<>(value));
            return this;
        }

        public Builder function(StageFunction function) {
            this.function = function;
            return this;
        }

        public Builder ease(EaseCurve ease) {
            this.ease = ease;
            return this;
        }

        public Builder duration(long duration) {
            this.duration = duration;
            return this;
        }

        public Stage build() {
            Stage returned = new Stage(function, ease, duration);
            properties.forEach(returned::putProperty);
            return returned;
        }
    }
}
