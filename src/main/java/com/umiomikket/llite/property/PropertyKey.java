package com.umiomikket.llite.property;

public class PropertyKey<Type> {
    private final String name;
    private final Class<Type> type;

    public PropertyKey(String name, Class<Type> type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }
}
