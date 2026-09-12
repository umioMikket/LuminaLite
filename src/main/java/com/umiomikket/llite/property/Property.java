package com.umiomikket.llite.property;

import java.util.Objects;

public class Property<Type> {
    private final Type value;
    private final Class<?> type;

    public Property(Type value) {
        this.value = value;
        this.type = value.getClass();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        return other instanceof Property<?> property &&
               Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Type getValue() {
        return value;
    }

    public Class<?> getType() {
        return type;
    }
}
