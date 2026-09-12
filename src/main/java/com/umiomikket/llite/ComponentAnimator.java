package com.umiomikket.llite;

import com.umiomikket.llite.property.Animatable;
import com.umiomikket.llite.property.animation.AnimationManager;
import com.umiomikket.llite.property.animation.Animation;
import com.umiomikket.llite.property.stage.Stage;
import com.umiomikket.llite.property.stage.StageFunction;
import com.umiomikket.llite.property.stage.StagesManager;
import com.umiomikket.llite.interpolation.EaseCurve;
import com.umiomikket.llite.property.IContainer;
import com.umiomikket.llite.property.PropertyInterpolatedContainer;

import javax.swing.*;
import java.awt.*;

public class ComponentAnimator {
    private final AnimationManager animations;
    private final StagesManager stages;
    private final Component component;
    private IContainer currentProperties;
    private Timer timer;

    public ComponentAnimator(Component component) {
        this.component = component;
        this.animations = new AnimationManager();
        this.stages = new StagesManager();
    }

    public void dispose() {
        stopAnimate();
    }

    public void playStage(StageFunction function) {
        Stage stage = stages.get(function);
        if (stage == null) return;
        playAnimatable(stage);
    }

    public void playAnimation(String name) {
        Animation animation = animations.get(name);
        if (animation == null) throw new NullPointerException(
                "The animation helper does not have an animation with the name: " + name + "!"
        );
        playAnimatable(animation);
    }

    public void playAnimation(Animation animation) {
        playAnimatable(animation);
    }

    public void playAnimatable(Animatable animatable) {
        playAnimatable(animatable, animatable.getEase(), animatable.getDuration());
    }

    public void playAnimatable(IContainer propertiesTo, EaseCurve curve, long duration) {
        IContainer propertiesOf = currentProperties;
        long started = System.currentTimeMillis();

        stopAnimate();
        timer = new Timer(LuminaLite.getFrameRateDelay(), e -> {
            long time = System.currentTimeMillis() - started;
            boolean isEnd = time >= duration;
            currentProperties = new PropertyInterpolatedContainer(
                propertiesOf, propertiesTo,
                curve, isEnd? duration : time, duration
            );

            component.repaint();

            if (isEnd) stopAnimate();
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public void stopAnimate() {
        if (timer == null || !timer.isRunning()) return;
        timer.stop();
    }

    public AnimationManager getAnimations() {
        return animations;
    }

    public StagesManager getStages() {
        return stages;
    }

    public Component getComponent() {
        return component;
    }

    public void setCurrentProperties(IContainer properties) {
        this.currentProperties = properties;
    }

    public IContainer getCurrentProperties() {
        return currentProperties;
    }
}
