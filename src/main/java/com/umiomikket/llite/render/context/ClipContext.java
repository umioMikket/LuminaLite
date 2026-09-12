package com.umiomikket.llite.render.context;

import com.umiomikket.llite.render.IRenderContext;

import java.awt.*;

public interface ClipContext extends IRenderContext {
    default void clip(Shape shape) {
        graphics().clip(shape);
    }

    default void withClip(Shape shape, Runnable action) {
        Graphics2D g = graphics();
        Shape oldClip = g.getClip();
        g.clip(shape);
        try {
            action.run();
        } finally {
            g.setClip(oldClip);
        }
    }
}
