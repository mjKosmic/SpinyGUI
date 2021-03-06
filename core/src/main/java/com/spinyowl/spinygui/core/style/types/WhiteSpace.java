package com.spinyowl.spinygui.core.style.types;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * CSS white-space.
 */
public final class WhiteSpace {
    private static final Map<String, WhiteSpace> VALUES = new ConcurrentHashMap<>();

    /**
     * Sequences of whitespace will collapse into a single whitespace. Text will wrap when necessary. This is default.
     */
    public final static WhiteSpace NORMAL = WhiteSpace.of("normal");
    /**
     * Sequences of whitespace will collapse into a single whitespace. Text will never wrap to the next line. The text continues on the same line until a <br> tag is encountered.
     */
    public final static WhiteSpace NOWRAP = WhiteSpace.of("nowrap");
    /**
     * Whitespace is preserved by the browser. Text will only wrap on line breaks. Acts like the <pre> tag in HTML.
     */
    public final static WhiteSpace PRE = WhiteSpace.of("pre");
    /**
     * Sequences of whitespace will collapse into a single whitespace. Text will wrap when necessary, and on line breaks.
     */
    public final static WhiteSpace PRE_LINE = WhiteSpace.of("pre-line");
    /**
     * Whitespace is preserved by the browser. Text will wrap when necessary, and on line breaks.
     */
    public final static WhiteSpace PRE_WRAP = WhiteSpace.of("pre-wrap");
    /**
     * Sets this property to its default value.
     */
    public final static WhiteSpace INITIAL = WhiteSpace.of("initial");
    /**
     * Inherits this property from its parent element.
     */
    public final static WhiteSpace INHERIT = WhiteSpace.of("inherit");


    /**
     * Name of white-space type (should be same as in css specification)
     */
    private final String name;

    /**
     * Creates white-space element with specified name.
     *
     * @param name name of white-space type (should be same as in css specification)
     */
    private WhiteSpace(String name) {
        this.name = name;
    }

    /**
     * Used to create new white-space element with specified name.
     * Note that name will be converted to lower case
     * and it should be the same as names of css white-space property in css specification.
     *
     * @param name name of white-space element.
     * @return new white-space element (or existing one).
     */
    public static WhiteSpace of(String name) {
        Objects.requireNonNull(name);
        return VALUES.computeIfAbsent(name.toLowerCase(), WhiteSpace::new);
    }

    /**
     * Returns set of all available white-space values.
     *
     * @return set of all available white-space values.
     */
    public static Set<WhiteSpace> values() {
        return Set.copyOf(VALUES.values());
    }

    /**
     * Returns true there is a white-space value wth specified name.
     *
     * @param name white-space name.
     * @return true there is a white-space value wth specified name.
     */
    public static boolean contains(String name) {
        if (name == null) {
            return false;
        }
        return values().stream().map(WhiteSpace::getName).collect(Collectors.toSet()).contains(name.toLowerCase());
    }

    /**
     * Name of white-space.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WhiteSpace whiteSpace = (WhiteSpace) o;
        return Objects.equals(name, whiteSpace.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WhiteSpace.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
