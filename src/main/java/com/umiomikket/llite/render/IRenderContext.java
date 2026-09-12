package com.umiomikket.llite.render;

import com.umiomikket.llite.property.IContainer;
import com.umiomikket.llite.property.Property;

import java.awt.*;

public interface IRenderContext {
    default Property<?> property(String name) {
        return properties().getProperty(name);
    }

    default Object value(String name) {
        return properties().getProperty(name).getValue();
    }

    default <T> T value(String name, Class<T> type) {
        return properties().valueProperty(name, type);
    }

    default float x(float ratio) {
        return component().getWidth() * ratio;
    }

    default float y(float ratio) {
        return component().getHeight() * ratio;
    }

    Graphics2D graphics();
    Component component();
    IContainer properties();
}
