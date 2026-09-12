package com.umiomikket.llite.property.stage;

@FunctionalInterface
public interface StageFunction {
    boolean function(
        boolean isFocused, boolean isHovered, boolean isPressed,
        /*boolean isDefault,*/ boolean isEnabled, boolean isDisabled
    );
}
