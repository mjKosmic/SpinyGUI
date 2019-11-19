package com.spinyowl.spinygui.core.style.css;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class Property {
    public static final String INHERIT = "inherit";
    public static final String INITIAL = "initial";

    /**
     * Name of css property.
     */
    protected final String name;

    /**
     * Default value of css property. Could be null if there is no default value.
     */
    protected final String defaultValue;

    /**
     * Defines if css property for element should be inherited from parent element.
     * <br>
     * When no value for an inherited property has been specified on an element,
     * the element gets the computed value of that property on its parent element.
     * Only the root element of the document gets the initial value given in the property's summary.
     * <br>
     * When no value for a non-inherited property has been specified on an element,
     * the element gets the initial value of that property (as specified in the property's summary).
     * <br>
     * The <b>inherit</b> keyword allows authors to explicitly specify inheritance.
     * It works on both inherited and non-inherited properties.
     */
    protected final boolean inherited;

    /**
     * Defines if css property could be animated.
     */
    protected final boolean animatable;

    /**
     * Current value of css property. Could not be null.
     */
    protected String value;

    private PropertyHandler propertyHandler;

    public Property(String name) {
        this.name = name;
        this.inherited = false;
        this.animatable = false;
        this.defaultValue = null;
    }

    /**
     * Constructor that should be used by implementations to initialize css property.
     * All implementations should provide at least no-argument constructor.
     *
     * @param name         name of css property.
     * @param defaultValue default value of css property. Could be null if there is no default value.
     * @param inherited    inheritance property. Defines if css property for element should be inherited from parent element.
     *                     <br>
     *                     When no value for an inherited property has been specified on an element,
     *                     the element gets the computed value of that property on its parent element.
     *                     Only the root element of the document gets the initial value given in the property's summary.
     *                     <br>
     *                     When no value for a non-inherited property has been specified on an element,
     *                     the element gets the initial value of that property (as specified in the property's summary).
     *                     <br>
     *                     The <b>inherit</b> keyword allows authors to explicitly specify inheritance.
     *                     It works on both inherited and non-inherited properties.
     * @param animatable   defines if css property could be animated.
     */
    protected Property(String name, String defaultValue, boolean inherited, boolean animatable) {
        this(name, defaultValue, inherited, animatable, null);
    }

    /**
     * Constructor that should be used by implementations to initialize css property.
     * All implementations should provide at least no-argument constructor.
     *
     * @param name         name of css property.
     * @param defaultValue default value of css property. Could be null if there is no default value.
     * @param inherited    inheritance property. Defines if css property for element should be inherited from parent element.
     *                     <br>
     *                     When no value for an inherited property has been specified on an element,
     *                     the element gets the computed value of that property on its parent element.
     *                     Only the root element of the document gets the initial value given in the property's summary.
     *                     <br>
     *                     When no value for a non-inherited property has been specified on an element,
     *                     the element gets the initial value of that property (as specified in the property's summary).
     *                     <br>
     *                     The <b>inherit</b> keyword allows authors to explicitly specify inheritance.
     *                     It works on both inherited and non-inherited properties.
     * @param animatable   defines if css property could be animated.
     * @param value        current property value.
     */
    protected Property(String name, String defaultValue, boolean inherited, boolean animatable, String value) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.inherited = inherited;
        this.animatable = animatable;
        this.value = value;
    }

    /**
     * Returns true if {@code INHERIT.equals(value)}.
     *
     * @return true if {@code INHERIT.equals(value)}.
     */
    public boolean equalsInherit() {
        return INHERIT.equals(value);
    }

    /**
     * Returns true if {@code INITIAL.equals(value)}.
     *
     * @return true if {@code INITIAL.equals(value)}.
     */
    public boolean equalsInitial() {
        return INITIAL.equals(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        Objects.requireNonNull(value);
        this.value = value.toLowerCase();
    }


    /**
     * Returns a set of constant property values allowed to use or an empty set (if there are no static values for propery).
     *
     * @return set of constant values or null.
     */
    public Set<String> allowedValues() {
        return Collections.emptySet();
    }


    public PropertyHandler getPropertyHandler() {
        return propertyHandler;
    }

    public void setPropertyHandler(PropertyHandler propertyHandler) {
        this.propertyHandler = propertyHandler;
    }
}