package com.umiomikket.llite.render.context;

import com.umiomikket.llite.render.IRenderContext;

import java.awt.*;

public interface PaintContext extends IRenderContext {
    default void color(Color color) {
        graphics().setColor(color);
    }

    default void paint(Paint paint) {
        graphics().setPaint(paint);
    }

    default void linearGradient(float x1, float y1, float x2, float y2, Color[] colors, float[] fractions) {
        paint(new LinearGradientPaint(x1, y1, x2, y2, fractions, colors));
    }

    default void linearGradient(float x1, float y1, float x2, float y2, Color start, Color end) {
        linearGradient(x1, y1, x2, y2, new Color[]{start, end}, new float[]{0.0f, 1.0f});
    }

    default void relativeLinearGradient(float x, float y, float width, float height,
                                        float relX1, float relY1, float relX2, float relY2,
                                        Color start, Color end) {
        linearGradient(
            x + width * relX1, y + height * relY1,
            x + width * relX2, y + height * relY2,
            start, end
        );
    }

    default void radialGradient(float cx, float cy, float radius, Color[] colors, float[] fractions) {
        paint(new RadialGradientPaint(cx, cy, radius, fractions, colors));
    }

    default void radialGradient(float cx, float cy, float radius, Color inner, Color outer) {
        radialGradient(cx, cy, radius, new Color[]{inner, outer}, new float[]{0.0f, 1.0f});
    }
}
