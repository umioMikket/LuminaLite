package com.umiomikket.llite.render.context;

import com.umiomikket.llite.render.IRenderContext;

import java.awt.geom.Path2D;

public interface RoundedRectangle extends IRenderContext {
    default Path2D createRoundedRectPath(float x, float y, float width, float height,
                                         float topLeft, float topRight, float bottomRight, float bottomLeft) {
        Path2D.Float path = new Path2D.Float();

        float maxRadius = Math.min(width, height) / 2.0f;
        topLeft = Math.min(Math.max(0, topLeft), maxRadius);
        topRight = Math.min(Math.max(0, topRight), maxRadius);
        bottomRight = Math.min(Math.max(0, bottomRight), maxRadius);
        bottomLeft = Math.min(Math.max(0, bottomLeft), maxRadius);

        path.moveTo(x + topLeft, y);

        path.lineTo(x + width - topRight, y);
        if (topRight > 0) path.quadTo(x + width, y, x + width, y + topRight);
        else path.lineTo(x + width, y);

        path.lineTo(x + width, y + height - bottomRight);
        if (bottomRight > 0) path.quadTo(x + width, y + height, x + width - bottomRight, y + height);
        else path.lineTo(x + width, y + height);

        path.lineTo(x + bottomLeft, y + height);
        if (bottomLeft > 0) path.quadTo(x, y + height, x, y + height - bottomLeft);
        else path.lineTo(x, y + height);

        path.lineTo(x, y + topLeft);
        if (topLeft > 0) path.quadTo(x, y, x + topLeft, y);
        else path.lineTo(x, y);

        path.closePath();
        return path;
    }

    default void fillRoundedRect(float x, float y, float width, float height,
                                 float topLeft, float topRight, float bottomRight, float bottomLeft) {
        graphics().fill(createRoundedRectPath(x, y, width, height, topLeft, topRight, bottomRight, bottomLeft));
    }

    default void fillRoundedRect(float x, float y, float width, float height, float radius) {
        fillRoundedRect(x, y, width, height, radius, radius, radius, radius);
    }

    default void drawRoundedRect(float x, float y, float width, float height,
                                 float topLeft, float topRight, float bottomRight, float bottomLeft) {
        graphics().draw(createRoundedRectPath(x, y, width, height, topLeft, topRight, bottomRight, bottomLeft));
    }

    default void drawRoundedRect(float x, float y, float width, float height, float radius) {
        drawRoundedRect(x, y, width, height, radius, radius, radius, radius);
    }
}
