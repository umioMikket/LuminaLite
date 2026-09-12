package com.umiomikket.llite.property.animation;

import com.umiomikket.llite.property.Animatable;
import com.umiomikket.llite.interpolation.EaseCurve;
import com.umiomikket.llite.property.Property;

import java.util.HashMap;
import java.util.Map;

public class Animation extends Animatable {
    private final String name;

    public Animation(String name, EaseCurve ease, long duration) {
        super(ease, duration);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<String, Property<?>> properties = new HashMap<>();
        private String name = "default";
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

        public Builder name(String name) {
            this.name = name;
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

        public Animation build() {
            Animation returned = new Animation(name, ease, duration);
            properties.forEach(returned::putProperty);
            return returned;
        }
    }
}
