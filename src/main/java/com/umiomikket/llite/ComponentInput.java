package com.umiomikket.llite;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

public class ComponentInput {
    private final Component component;
    private final Runnable onInput;
    private final MouseAdapter mouseListener;
    private final PropertyChangeListener propertyListener;
    private boolean isHovered, isPressed;

    public ComponentInput(Component component, Runnable onInput) {
        this.component = component;
        this.onInput = onInput;
        this.mouseListener = new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { setPressed(true); }
            @Override public void mouseReleased(MouseEvent e) { setPressed(false); }
            @Override public void mouseEntered(MouseEvent e) { setHovered(true); }
            @Override public void mouseExited(MouseEvent e) { setHovered(false); }
        };
        this.propertyListener = e -> onInput.run();
        init();
    }

    private void init() {
        component.addMouseListener(mouseListener);
        component.addPropertyChangeListener("focusable", propertyListener);
        component.addPropertyChangeListener("enabled", propertyListener);
    }

    public void dispose() {
        component.removeMouseListener(mouseListener);
        component.removePropertyChangeListener("focusable", propertyListener);
        component.removePropertyChangeListener("enabled", propertyListener);
    }

    private void setHovered(boolean value) {
        if (this.isHovered != value) {
            this.isHovered = value;
            onInput.run();
        }
    }

    private void setPressed(boolean value) {
        if (this.isPressed != value) {
            this.isPressed = value;
            onInput.run();
        }
    }

    public boolean isHovered() { return isHovered; }
    public boolean isPressed() { return isPressed; }
}
