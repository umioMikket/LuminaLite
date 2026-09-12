package com.umiomikket.llite.render.context;

import com.umiomikket.llite.render.IRenderContext;

import java.awt.*;

public interface TextRenderContext extends IRenderContext {
    default void font(Font font) {
        graphics().setFont(font);
    }

    default FontMetrics fontMetrics() {
        return graphics().getFontMetrics();
    }

    default void drawCenteredString(String text, float x, float y, float width, float height) {
        FontMetrics fm = fontMetrics();
        float textX = x + (width - fm.stringWidth(text)) / 2.0f;
        float textY = y + ((height - fm.getHeight()) / 2.0f) + fm.getAscent();
        graphics().drawString(text, textX, textY);
    }
}
