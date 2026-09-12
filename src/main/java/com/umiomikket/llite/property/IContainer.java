package com.umiomikket.llite.property;

import java.util.Map;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface IContainer {
    Object valueProperty(String name);

    <T> T valueProperty(String name, Class<T> type);

    boolean addProperty(String name, Property<?> property);

    boolean putProperty(String name, Property<?> property);

    boolean removeProperty(String name);

    Property<?> getProperty(String name);

    boolean hasProperty(String name);

    Map<String, Property<?>> getProperties();
}
