package com.umiomikket.llite.render.context;

import com.umiomikket.llite.render.IRenderContext;

import java.awt.*;

public interface CompositeContext extends IRenderContext {
    default void alpha(float alpha) {
        graphics().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.max(0.0f, Math.min(1.0f, alpha))));
    }

    default void composite(Composite composite) {
        graphics().setComposite(composite);
    }

    default void withAlpha(float alpha, Runnable action) {
        Graphics2D g = graphics();
        Composite oldComposite = g.getComposite();
        alpha(alpha);
        try { action.run(); }
        finally { g.setComposite(oldComposite); }
    }
}
