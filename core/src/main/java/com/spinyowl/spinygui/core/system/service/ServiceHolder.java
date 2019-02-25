package com.spinyowl.spinygui.core.system.service;

import com.spinyowl.spinygui.core.Configuration;
import io.github.classgraph.ClassGraph;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceHolder {
    private static final Logger LOGGER = Logger.getLogger(ServiceHolder.class.getName());

    private static final ServiceProvider SERVICE_PROVIDER;
    private static final MonitorService MONITOR_SERVICE;
    private static final WindowService WINDOW_SERVICE;
    private static final ClipboardService CLIPBOARD_SERVICE;
    private static final RendererFactoryService RENDERER_FACTORY_SERVICE;

    static {
        SERVICE_PROVIDER = initializeService(ServiceProvider.class, Configuration.SERVICE_PROVIDER.getValue());

        if (Configuration.WINDOW_SERVICE.getValue() != null) {
            WINDOW_SERVICE = initializeService(WindowService.class, Configuration.WINDOW_SERVICE.getValue());
        } else {
            WINDOW_SERVICE = SERVICE_PROVIDER.getWindowService();
        }

        if (Configuration.MONITOR_SERVICE.getValue() != null) {
            MONITOR_SERVICE = initializeService(MonitorService.class, Configuration.MONITOR_SERVICE.getValue());
        } else {
            MONITOR_SERVICE = SERVICE_PROVIDER.getMonitorService();
        }

        CLIPBOARD_SERVICE = SERVICE_PROVIDER.getClipboardService();

        RENDERER_FACTORY_SERVICE = SERVICE_PROVIDER.getRendererFactoryService();
    }

    public static MonitorService getMonitorService() {
        return MONITOR_SERVICE;
    }

    public static WindowService getWindowService() {
        return WINDOW_SERVICE;
    }

    public static ClipboardService getClipboardService() {
        return CLIPBOARD_SERVICE;
    }

    public static RendererFactoryService getRendererFactoryService() {
        return RENDERER_FACTORY_SERVICE;
    }

    private static <T> T initializeService(Class<T> serviceClass, String implementationClass) {
        T instance = null;

        try (var scanResult = new ClassGraph().enableAllInfo().scan()) {
            // get all subclasses
            String serviceClassName = serviceClass.getName();

            List<Class<?>> serviceClassRefs;
            if (serviceClass.isInterface()) {
                serviceClassRefs = scanResult.getClassesImplementing(serviceClassName).loadClasses();
            } else {
                serviceClassRefs = scanResult.getSubclasses(serviceClassName).loadClasses();
            }
            // check if found implementations.
            if (serviceClassRefs != null && !serviceClassRefs.isEmpty()) {
                // log existing implementations
                if (LOGGER.isLoggable(Level.INFO)) {
                    for (Class<?> classRef : serviceClassRefs) {
                        LOGGER.log(Level.INFO, String.format("%s class implementation found: %s", serviceClass.getSimpleName(), classRef.getName()));
                    }
                }

                //get property

                if (implementationClass != null) {
                    LOGGER.log(Level.INFO, "Trying to load specified implementation: '" + implementationClass + "'.");
                    for (Class<?> aClass : serviceClassRefs) {
                        if (implementationClass.equals(aClass.getName()) || implementationClass.equals(aClass.getSimpleName())) {
                            instance = createInstance((Class<T>) aClass);
                        }
                    }

                    if (instance == null) {
                        LOGGER.log(Level.INFO, "Specified implementation '" + implementationClass + "' not found.");
                    } else {
                        LOGGER.log(Level.INFO, "Specified implementation '" + implementationClass + "' loaded.");
                    }
                } else {
                    instance = createInstance((Class<T>) serviceClassRefs.get(0));
                }
            } else {
                try {
                    Class<?> aClass = Class.forName(implementationClass);
                    if (serviceClass.isAssignableFrom(aClass)) {
                        instance = createInstance((Class<T>) aClass);
                    }
                } catch (ClassNotFoundException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        }
        return instance;
    }

    private static <T> T createInstance(Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return instance;
    }

    public interface ServiceProvider {

        MonitorService getMonitorService();

        WindowService getWindowService();

        ClipboardService getClipboardService();

        RendererFactoryService getRendererFactoryService();

    }
}