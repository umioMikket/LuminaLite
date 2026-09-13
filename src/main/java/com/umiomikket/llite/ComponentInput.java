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
    private int lastMouseX, lastMouseY;
    private boolean isRespondsToMouseMovement;

    public ComponentInput(Component component, Runnable onInput) {
        this.component = component;
        this.onInput = onInput;
        this.mouseListener = new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { setPressed(true); }
            @Override public void mouseReleased(MouseEvent e) { setPressed(false); }
            @Override public void mouseEntered(MouseEvent e) { setHovered(true); }
            @Override public void mouseExited(MouseEvent e) { setHovered(false); }
            @Override public void mouseMoved(MouseEvent e) { setMousePosition(e); }
            @Override public void mouseDragged(MouseEvent e) { setMousePosition(e); }
        };
        this.propertyListener = e -> onInput.run();
        init();
    }

    private void init() {
        component.addMouseListener(mouseListener);
        component.addMouseMotionListener(mouseListener);
        component.addPropertyChangeListener("focusable", propertyListener);
        component.addPropertyChangeListener("enabled", propertyListener);
    }

    public void dispose() {
        component.removeMouseListener(mouseListener);
        component.removeMouseListener(mouseListener);
        component.removePropertyChangeListener("focusable", propertyListener);
        component.removePropertyChangeListener("enabled", propertyListener);
    }

    private void setMousePosition(MouseEvent event) {
        lastMouseX = event.getX();
        lastMouseY = event.getY();
        if (isRespondsToMouseMovement) onInput.run();
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

    public boolean isRespondsToMouseMovement() {
        return isRespondsToMouseMovement;
    }

    public void setRespondsToMouseMovement(boolean value) {
        isRespondsToMouseMovement = value;
    }

    public boolean isHovered() { return isHovered; }
    public boolean isPressed() { return isPressed; }

    public int getLastMouseX() {
        return lastMouseX;
    }

    public int getLastMouseY() {
        return lastMouseY;
    }
}
