package com.umiomikket.llite.utils;

public class NumberCast {
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object value, Class<T> type) {
        if (value == null) return null;
        if (type.isInstance(value)) return type.cast(value);
        if (Number.class.isAssignableFrom(wrap(type))) return (T) castNumber(value, wrap(type));
        return type.cast(value);
    }

    private static Class<?> wrap(Class<?> type) {
        if (!type.isPrimitive()) return type;
        if (type == int.class) return Integer.class;
        if (type == float.class) return Float.class;
        if (type == double.class) return Double.class;
        if (type == long.class) return Long.class;
        if (type == byte.class) return Byte.class;
        if (type == short.class) return Short.class;
        if (type == boolean.class) return Boolean.class;
        return type;
    }

    private static Number castNumber(Object value, Class<?> targetType) {
        Number number = (value instanceof Number n) ? n : Double.parseDouble(value.toString());

        if (targetType == Integer.class) return number.intValue();
        if (targetType == Float.class)   return number.floatValue();
        if (targetType == Double.class)  return number.doubleValue();
        if (targetType == Long.class)    return number.longValue();
        if (targetType == Byte.class)    return number.byteValue();
        if (targetType == Short.class)   return number.shortValue();

        throw new IllegalArgumentException("Cannot convert " + value.getClass() + " to " + targetType);
    }
}
