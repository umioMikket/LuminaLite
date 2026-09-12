package com.umiomikket.llite.interpolation;

import com.umiomikket.llite.utils.NumberCast;

import java.util.ArrayList;
import java.util.List;

public class InterpolationManager {
    private static final List<InterpolationObjectHandler> handlers;

    static {
        handlers = new ArrayList<>();
        handlers.add(InterpolationObjectHandler.NUMBER_INTERPOLATION);
        handlers.add(InterpolationObjectHandler.COLOR_INTERPOLATION);
    }

    @SuppressWarnings({"unchecked", "unused"})
    public static Object interpolate(Object a, Object b, float ratio, Class<?> type) {
        Object interpolated = interpolate(a, b, ratio);
        if (type == Number.class && interpolated instanceof Number)
            return NumberCast.cast(interpolated, (Class<? extends Number>) type);
        return type.cast(interpolated);
    }

    public static Object interpolate(Object a, Object b, float ratio) {
        if (a == null && b == null) return null;
        for (InterpolationObjectHandler h : handlers) {
            if (!h.isHandle(a, b)) continue;
            Object va = a == null? h.getDefaultValue() : a,
                   vb = b == null? h.getDefaultValue() : b;
            return h.handle(va, vb, ratio);
        }
        return ratio < 0.5f? a : b;
    }

    @SuppressWarnings("unused")
    public static boolean addHandler(InterpolationObjectHandler handler) {
        if (hasHandler(handler)) return false;
        handlers.add(handler);
        return true;
    }

    @SuppressWarnings("unused")
    public static boolean removeHandler(InterpolationObjectHandler handler) {
        return handlers.remove(handler);
    }

    public static boolean hasHandler(InterpolationObjectHandler handler) {
        return handlers.contains(handler);
    }

    @SuppressWarnings("unused")
    public static List<InterpolationObjectHandler> getHandlers() {
        return handlers;
    }
}
