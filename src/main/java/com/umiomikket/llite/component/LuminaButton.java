package com.umiomikket.llite.component;

import com.umiomikket.llite.ComponentHelper;
import com.umiomikket.llite.ILuminaComponent;
import com.umiomikket.llite.LuminaLite;
import com.umiomikket.llite.render.IRenderContext;
import com.umiomikket.llite.render.RenderFunction;

import javax.swing.*;
import java.awt.*;

public class LuminaButton extends JButton implements ILuminaComponent {
    private final ComponentHelper helper;
    private final RenderFunction renderFunction;

    public LuminaButton(RenderFunction renderFunction) {
        helper = new ComponentHelper(this);
        this.renderFunction = renderFunction;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        IRenderContext renderContext = LuminaLite.getRenderContextFactory()
            .create(g2d, this, helper.getAnimator().getCurrentProperties());
        renderFunction.render(renderContext);
        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    public ComponentHelper getHelper() {
        return helper;
    }

    @Override
    public RenderFunction getRenderFunction() {
        return renderFunction;
    }
}
