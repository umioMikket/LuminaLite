package com.umiomikket.llite.property;

import java.util.Map;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface IContainer {
    Object valueProperty(String name);

    <T> T valueProperty(PropertyKey<T> key);
    <T> T valueProperty(String name, Class<T> type);

    boolean addProperty(PropertyKey<?> key, Property<?> property);
    boolean addProperty(String name, Property<?> property);

    boolean putProperty(PropertyKey<?> key, Property<?> property);
    boolean putProperty(String name, Property<?> property);

    boolean removeProperty(PropertyKey<?> key);
    boolean removeProperty(String name);

    <T> Property<T> getProperty(PropertyKey<T> key);
    Property<?> getProperty(String name);

    boolean hasProperty(PropertyKey<?> key);
    boolean hasProperty(String name);

    Map<String, Property<?>> getProperties();
}
