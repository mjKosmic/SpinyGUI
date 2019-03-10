package com.spinyowl.spinygui.core.style.types;

import java.util.Objects;

public class Length<T> {

    private final T value;
    private final Type<T> type;

    public Length(Type<T> type, T value) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(type);
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public Type<T> getType() {
        return type;
    }

    public static final class Type<T> {
        public static final Type<Float> PIXEL = new Type<>("PIXEL", Float.class);
        public static final Type<Float> PERCENT = new Type<>("PERCENT", Float.class);

        private final String typeName;
        private final Class type;

        private Type(String typeName, Class<T> type) {
            this.typeName = typeName;
            this.type = type;
        }

        public Class getType() {
            return type;
        }

        public String getTypeName() {
            return typeName;
        }
    }
}
