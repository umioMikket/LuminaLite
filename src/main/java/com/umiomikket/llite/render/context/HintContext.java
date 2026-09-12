package com.umiomikket.llite.render.context;

import com.umiomikket.llite.render.IRenderContext;

import java.awt.*;

public interface HintContext extends IRenderContext {
    default void hint(RenderingHints.Key key, Object value) {
        graphics().setRenderingHint(key, value);
    }

    default void enableAntialiasing() {
        Graphics2D g = graphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    default void enableHighQuality() {
        enableAntialiasing();
        graphics().setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics().setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }
}
