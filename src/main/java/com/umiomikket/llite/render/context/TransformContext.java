package com.umiomikket.llite.render.context;

import com.umiomikket.llite.render.IRenderContext;

import java.awt.*;
import java.awt.geom.AffineTransform;

public interface TransformContext extends IRenderContext {
    default void translate(float tx, float ty) {
        graphics().translate(tx, ty);
    }

    default void rotate(double theta) {
        graphics().rotate(theta);
    }

    default void rotate(double theta, float anchorX, float anchorY) {
        graphics().rotate(theta, anchorX, anchorY);
    }

    default void scale(double sx, double sy) {
        graphics().scale(sx, sy);
    }

    default void withTransform(Runnable action) {
        Graphics2D g = graphics();
        AffineTransform oldTransform = g.getTransform();
        try { action.run(); }
        finally { g.setTransform(oldTransform); }
    }
}
