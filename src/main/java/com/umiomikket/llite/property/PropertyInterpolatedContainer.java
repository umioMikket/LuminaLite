package com.umiomikket.llite.property;

import com.umiomikket.llite.interpolation.EaseCurve;
import com.umiomikket.llite.interpolation.InterpolationManager;
import com.umiomikket.llite.utils.NumberCast;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PropertyInterpolatedContainer implements IContainer {
    private final IContainer interpolatedOf, interpolatedTo;
    private final Map<String, Property<?>> cachedProperties;
    private final Map<String, Object> cachedValues;
    private final float ratio;

    public PropertyInterpolatedContainer(
        IContainer interpolatedOf, IContainer interpolatedTo,
        EaseCurve curve, long time, long duration
    ) {
        this.interpolatedOf = interpolatedOf;
        this.interpolatedTo = interpolatedTo;
        this.ratio = curve.ease((float) time / duration);
        this.cachedProperties = new HashMap<>();
        this.cachedValues = new HashMap<>();
    }

    @Override
    public Object valueProperty(String name) {
        Object waitingCached = cachedValues.get(name);
        if (waitingCached != null) return waitingCached;
        Property<?> propertyOff = interpolatedOf.getProperty(name);
        Property<?> propertyTo = interpolatedTo.getProperty(name);
        Object off = propertyOff != null? propertyOff.getValue() : null;
        Object to = propertyTo != null? propertyTo.getValue() : null;
        Object returned = InterpolationManager.interpolate(off, to, ratio);
        cachedValues.put(name, returned);
        return returned;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T valueProperty(PropertyKey<T> key) {
        Object value = valueProperty(key.getName());
        return value != null? (T) NumberCast.cast(value, key.getType()) : null;
    }

    @Override
    public <T> T valueProperty(String name, Class<T> type) {
        Object value = valueProperty(name);
        return value != null? NumberCast.cast(value, type) : null;
    }

    @Deprecated
    @Override
    public boolean addProperty(PropertyKey<?> key, Property<?> property) {
        return false;
    }

    @Deprecated
    @Override
    public boolean addProperty(String name, Property<?> property) {
        return false;
    }

    @Deprecated
    @Override
    public boolean putProperty(PropertyKey<?> key, Property<?> property) {
        return false;
    }

    @Deprecated
    @Override
    public boolean putProperty(String name, Property<?> property) {
        return false;
    }

    @Deprecated
    @Override
    public boolean removeProperty(PropertyKey<?> key) {
        return false;
    }

    @Deprecated
    @Override
    public boolean removeProperty(String name) {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Property<T> getProperty(PropertyKey<T> key) {
        return (Property<T>) getProperty(key.getName());
    }

    @Override
    public Property<?> getProperty(String name) {
        Property<?> waitingCached = cachedProperties.get(name);
        if (waitingCached != null) return waitingCached;
        Object value = valueProperty(name);
        Property<?> property = value != null
            ? new Property<>(value)
            : null;
        cachedProperties.put(name, property);
        return property;
    }

    @Override
    public boolean hasProperty(PropertyKey<?> key) {
        return hasProperty(key.getName());
    }

    @Override
    public boolean hasProperty(String name) {
        return cachedProperties.containsKey(name) ||
               interpolatedOf.hasProperty(name) ||
               interpolatedTo.hasProperty(name);
    }

    @Override
    public Map<String, Property<?>> getProperties() {
        Set<String> propertiesNames = new HashSet<>();
        propertiesNames.addAll(interpolatedOf.getProperties().keySet());
        propertiesNames.addAll(interpolatedTo.getProperties().keySet());
        for (String name : propertiesNames) getProperty(name);
        return Collections.unmodifiableMap(cachedProperties);
    }
}
