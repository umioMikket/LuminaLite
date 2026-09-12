package com.umiomikket.llite.property.animation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AnimationManager {
    private final Map<String, Animation> animations;

    public AnimationManager() {
        this.animations = new HashMap<>();
    }

    public boolean add(Animation animation) {
        String animationName = animation.getName();
        if (animations.containsKey(animationName)) return false;
        animations.put(animationName, animation);
        return true;
    }

    public boolean put(Animation animation) {
        return animations.put(animation.getName(), animation) != null;
    }

    public boolean remove(String name) {
        return animations.remove(name) != null;
    }

    public boolean remove(Animation animation) {
        String animationName = animation.getName();
        return animations.containsKey(animationName) &&
                animations.remove(animationName) != null;
    }

    public Animation get(String name) {
        return animations.get(name);
    }

    public boolean has(String name) {
        return animations.containsKey(name);
    }

    public boolean has(Animation animation) {
        return animations.containsValue(animation);
    }

    public Map<String, Animation> get() {
        return Collections.unmodifiableMap(animations);
    }
}
