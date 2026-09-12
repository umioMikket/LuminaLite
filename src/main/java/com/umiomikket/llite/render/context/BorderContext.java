package com.umiomikket.llite.render.context;

import com.umiomikket.llite.render.IRenderContext;

import java.awt.*;

public interface BorderContext extends IRenderContext {
    default void stroke(Stroke stroke) {
        graphics().setStroke(stroke);
    }

    default void stroke(float width) {
        stroke(new BasicStroke(width));
    }

    default void stroke(float width, int cap, int join) {
        stroke(new BasicStroke(width, cap, join));
    }

    default void dashedStroke(float width, float[] dash, float dashPhase) {
        stroke(new BasicStroke(
            width,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f,
            dash,
            dashPhase
        ));
    }

    default void dashedStroke(float width, float dashLength, float spaceLength) {
        dashedStroke(width, new float[]{dashLength, spaceLength}, 0.0f);
    }

    default void roundStroke(float width) {
        stroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }
}
