package com.umiomikket.llite.property.stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StagesManager {
    private final List<Stage> stages;

    public StagesManager() {
        this.stages = new ArrayList<>();
    }

    public boolean add(Stage stage) {
        if (stages.contains(stage)) return false;
        stages.add(stage);
        return true;
    }

    public boolean remove(Stage stage) {
        return stages.remove(stage);
    }

    public Stage get(
        boolean isFocused, boolean isHovered, boolean isPressed,
        boolean isEnabled, boolean isDisabled
    ) {
        for (Stage s : stages)
            if (s.isStage(isFocused, isHovered, isPressed, isEnabled, isDisabled))
                return s;
        return null;
    }

    public Stage get(StageFunction function) {
        for (Stage s : stages)
            if (s.isStage(function))
                return s;
        return null;
    }

    public boolean has(Stage stage) {
        return stages.contains(stage);
    }

    public boolean has(StageFunction function) {
        for (Stage s : stages)
            if (s.isStage(function))
                return true;
        return false;
    }

    public List<Stage> get() {
        return Collections.unmodifiableList(stages);
    }
}
