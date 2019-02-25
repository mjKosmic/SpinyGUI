package com.spinyowl.spinygui.core;

/**
 * Configuration class that used to define configuration for GUI system.
 *
 * @param <T> type of configuration value.
 */
public final class Configuration<T> {

    public static final Configuration<String> WINDOW_SERVICE = new Configuration<>("spinygui.window.service", Initializer.STRING);
    public static final Configuration<String> MONITOR_SERVICE = new Configuration<>("spinygui.monitor.service", Initializer.STRING);
    public static final Configuration<String> SERVICE_PROVIDER = new Configuration<>("spinygui.service.porvider", Initializer.STRING);

    private String name;
    private T value;

    private Configuration(String name, Initializer<T> initializer) {
        this.name = name;
        this.value = initializer.init(name);
    }

    /**
     * Returns configuration name.
     *
     * @return configuration name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns configuration value.
     *
     * @return configuration value
     */
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getState(T defaultValue) {
        T state = this.value;
        if (state == null) {
            state = defaultValue;
        }

        return state;
    }

    protected interface Initializer<T> {

        Initializer<Boolean> BOOLEAN = configuration -> {
            String value = System.getProperty(configuration);
            return value == null ? null : Boolean.parseBoolean(value);
        };
        Initializer<Integer> INT = Integer::getInteger;
        Initializer<String> STRING = System::getProperty;

        T init(String configuration);
    }
}
