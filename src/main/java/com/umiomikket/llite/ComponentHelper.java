package com.umiomikket.llite;

import com.umiomikket.llite.property.stage.Stage;

import java.awt.*;
import java.util.Objects;

public class ComponentHelper {
    private final Component component;

    private final ComponentInput input;
    private final ComponentAnimator animator;

    public ComponentHelper(Component component) {
        this.component = Objects.requireNonNull(component, "Component cannot be null!");
        this.input = new ComponentInput(component, this::update);
        this.animator = new ComponentAnimator(component);
    }

    public void dispose() {
        animator.dispose();
        input.dispose();
    }

    public void update() {
        Stage selected = animator
            .getStages()
            .get(component.isFocusOwner(),
                input.isHovered(),
                input.isPressed(),
                component.isEnabled(),
                !component.isEnabled()
            );

        if (selected == null) return;
        if (selected == animator.getCurrentProperties()) return;
        animator.playAnimatable(selected, selected.getEase(), selected.getDuration());
    }

    public Component getComponent() {
        return component;
    }

    public ComponentInput getInput() {
        return input;
    }

    public ComponentAnimator getAnimator() {
        return animator;
    }
}
