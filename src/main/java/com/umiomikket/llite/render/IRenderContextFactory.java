package com.umiomikket.llite.render;

import com.umiomikket.llite.property.IContainer;

import java.awt.*;

public interface IRenderContextFactory {
    IRenderContext create(Graphics2D graphics, Component component, IContainer properties);
}
