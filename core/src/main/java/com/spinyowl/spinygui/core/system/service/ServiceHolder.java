package com.spinyowl.spinygui.core.system.service;

import com.spinyowl.spinygui.core.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import io.github.classgraph.ClassGraph;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ServiceHolder {
    private static final Log LOGGER = LogFactory.getLog(ServiceHolder.class);

    private static final ServiceProvider SERVICE_PROVIDER;
    private static final MonitorService MONITOR_SERVICE;
    private static final WindowService WINDOW_SERVICE;
    private static final ClipboardService CLIPBOARD_SERVICE;
    private static final RendererFactoryService RENDERER_FACTORY_SERVICE;

    static {
        SERVICE_PROVIDER = initializeService(ServiceProvider.class, Configuration.SERVICE_PROVIDER.getValue());

        WINDOW_SERVICE = SERVICE_PROVIDER.getWindowService();
        MONITOR_SERVICE = SERVICE_PROVIDER.getMonitorService();
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
                for (Class<?> classRef : serviceClassRefs) {
                    LOGGER.info( String.format("%s class implementation found: %s", serviceClass.getSimpleName(), classRef.getName()));
                }

                //get property

                if (implementationClass != null) {
                    LOGGER.info( "Trying to load specified implementation: '" + implementationClass + "'.");
                    for (Class<?> aClass : serviceClassRefs) {
                        if (implementationClass.equals(aClass.getName()) || implementationClass.equals(aClass.getSimpleName())) {
                            instance = createInstance((Class<T>) aClass);
                        }
                    }

                    if (instance == null) {
                        LOGGER.info( "Specified implementation '" + implementationClass + "' not found.");
                        throw new ExceptionInInitializerError("Could not initialize " + serviceClass.getSimpleName() + " service.");
                    } else {
                        LOGGER.info( "Specified implementation '" + implementationClass + "' loaded.");
                    }
                } else {
                    Class<T> clazz = (Class<T>) serviceClassRefs.get(0);
                    instance = createInstance(clazz);
                    LOGGER.info( String.format("%s class implementation loaded: %s", serviceClass.getSimpleName(), clazz.getName()));
                }
            } else {
                try {
                    if (implementationClass != null) {
                        Class<?> aClass = Class.forName(implementationClass);
                        if (serviceClass.isAssignableFrom(aClass)) {
                            instance = createInstance((Class<T>) aClass);
                        }
                    } else {
                        throw new ExceptionInInitializerError("Could not initialize " + serviceClass.getSimpleName() + " service.");
                    }
                } catch (ClassNotFoundException e) {
                    LOGGER.error(e.getMessage());
                    throw new ExceptionInInitializerError("Could not initialize " + serviceClass.getSimpleName() + " service.");
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
            LOGGER.warn(e.getMessage());
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
