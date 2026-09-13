package com.umiomikket.llite.render;

import com.umiomikket.llite.ILuminaComponent;
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

    default int mouseX() {
        return component() instanceof ILuminaComponent c
            ? c.getHelper().getInput().getLastMouseX()
            : component().getWidth() / 2;
    }

    default int mouseY() {
        return component() instanceof ILuminaComponent c
            ? c.getHelper().getInput().getLastMouseY()
            : component().getHeight() / 2;
    }

    default float ratioX(int x) {
        return (float) x / component().getHeight();
    }

    default float ratioY(int y) {
        return (float) y / component().getHeight();
    }

    Graphics2D graphics();
    Component component();
    IContainer properties();
}
