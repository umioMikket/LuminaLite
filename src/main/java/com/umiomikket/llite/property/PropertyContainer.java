package com.umiomikket.llite.property;

import com.umiomikket.llite.utils.NumberCast;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PropertyContainer implements IContainer {
    private final Map<String, Property<?>> properties;

    public PropertyContainer() {
        this.properties = new HashMap<>();
    }

    @Override
    public Object valueProperty(String name) {
        Property<?> waitingProperty = properties.get(name);
        return waitingProperty != null? waitingProperty.getValue() : null;
    }

    @Override
    public <T> T valueProperty(String name, Class<T> type) {
        Property<?> waitingProperty = properties.get(name);
        if (waitingProperty == null) return null;
        Object value = waitingProperty.getValue();
        return value != null? NumberCast.cast(value, type) : null;
    }

    @Override
    public boolean addProperty(String name, Property<?> property) {
        if (properties.containsKey(name)) return false;
        properties.put(name, property);
        return true;
    }

    @Override
    public boolean putProperty(String name, Property<?> property) {
        boolean existed = properties.containsKey(name);
        properties.put(name, property);
        return existed;
    }

    @Override
    public boolean removeProperty(String name) {
        return properties.remove(name) != null;
    }

    @Override
    public Property<?> getProperty(String name) {
        return properties.get(name);
    }

    @Override
    public boolean hasProperty(String name) {
        return properties.containsKey(name);
    }

    @Override
    public Map<String, Property<?>> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public static class Builder {
        private final Map<String, Property<?>> properties;

        public Builder() {
            this.properties = new HashMap<>();
        }

        public Builder property(String name, Property<?> property) {
            properties.put(name, property);
            return this;
        }

        public Builder property(String name, Object value) {
            properties.put(name, new Property<>(value));
            return this;
        }

        public PropertyContainer build() {
            PropertyContainer container = new PropertyContainer();
            properties.forEach(container::addProperty);
            return container;
        }
    }
}
