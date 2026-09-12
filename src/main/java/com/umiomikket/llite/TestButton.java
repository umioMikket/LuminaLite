package com.umiomikket.llite;

import com.umiomikket.llite.component.LuminaButton;
import com.umiomikket.llite.interpolation.Ease;
import com.umiomikket.llite.property.stage.Stage;
import com.umiomikket.llite.render.DefaultRenderContext;

import javax.swing.*;
import java.awt.*;

public class TestButton {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestButton::init);
    }

    public static void init() {
        JFrame frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width / 3, screenSize.height / 3);
        frame.setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());
        frame.setContentPane(contentPanel);
        contentPanel.add(getButton(), BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static JButton getButton() {
        LuminaButton button = new LuminaButton(
            ic -> {
                DefaultRenderContext c = (DefaultRenderContext) ic;
                c.enableAntialiasing();
                float strokeWidth = 1.0f;
                float halfStroke = strokeWidth / 2.0f;
                float x = halfStroke, y = halfStroke;
                float w = c.x(1f) - strokeWidth;
                float h = c.y(1f) - strokeWidth;
                c.color(c.value("background", Color.class));
                c.fillRoundedRect(x, y, w, h, 3.4f);
                c.color(c.value("border", Color.class));
                c.stroke(strokeWidth);
                c.drawRoundedRect(x, y, w, h, 3.4f);
            }
        );

        button.setText("LLite button!");
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));

        ComponentAnimator animator = button.getHelper().getAnimator();
        Stage defaultStage = Stage.builder()
            .function((isFocused, isHovered, isPressed, isEnabled, isDisabled) -> !(isHovered || isPressed || isDisabled))
            .property("background", Color.decode("#5cb85c"))
            .property("border", Color.decode("#5cb85c"))
            .ease(Ease.EASE_IN_OUT_QUAD)
            .duration(150)
            .build();
        animator.getStages().add(defaultStage);
        animator.setCurrentProperties(defaultStage);
        animator.getStages().add(
            Stage.builder()
            .function((isFocused, isHovered, isPressed, isEnabled, isDisabled) -> isHovered)
            .property("background", Color.decode("#4e9c4e"))
            .property("border", Color.decode("#4a934a"))
            .ease(Ease.EASE_IN_OUT_QUAD)
            .duration(150)
            .build()
        );

        return button;
    }
}
