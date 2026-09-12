package com.umiomikket.llite.render;

import com.umiomikket.llite.property.IContainer;
import com.umiomikket.llite.render.context.BorderContext;
import com.umiomikket.llite.render.context.ClipContext;
import com.umiomikket.llite.render.context.CompositeContext;
import com.umiomikket.llite.render.context.PaintContext;
import com.umiomikket.llite.render.context.RoundedRectangle;
import com.umiomikket.llite.render.context.TextRenderContext;
import com.umiomikket.llite.render.context.TransformContext;
import com.umiomikket.llite.render.context.HintContext;

import java.awt.*;

public class DefaultRenderContext implements
    // ? default context
    IRenderContext,
    // ? interfaces as tools
    BorderContext,
    ClipContext,
    CompositeContext,
    HintContext,
    PaintContext,
    RoundedRectangle,
    TextRenderContext,
    TransformContext
{
    private final Graphics2D graphics;
    private final Component component;
    private final IContainer properties;

    public DefaultRenderContext(Graphics2D graphics, Component component, IContainer properties) {
        this.graphics = graphics;
        this.component = component;
        this.properties = properties;
    }

    public Graphics2D graphics() {
        return graphics;
    }

    public Component component() {
        return component;
    }

    public IContainer properties() {
        return properties;
    }
}
