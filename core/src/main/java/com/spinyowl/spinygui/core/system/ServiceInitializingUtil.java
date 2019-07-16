package com.spinyowl.spinygui.core.system;

import io.github.classgraph.ClassGraph;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public final class ServiceInitializingUtil {
    private static final Log LOGGER = LogFactory.getLog(ServiceInitializingUtil.class);

    private ServiceInitializingUtil() {
    }

    public static <T> T initializeService(Class<T> serviceClass, String implementationClass) {
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
                    LOGGER.info(String.format("%s class implementation found: %s", serviceClass.getSimpleName(), classRef.getName()));
                }

                //get property

                if (implementationClass != null) {
                    LOGGER.info("Trying to load specified implementation: '" + implementationClass + "'.");
                    for (Class<?> aClass : serviceClassRefs) {
                        if (implementationClass.equals(aClass.getName()) || implementationClass.equals(aClass.getSimpleName())) {
                            instance = createInstance((Class<T>) aClass);
                        }
                    }

                    if (instance == null) {
                        LOGGER.info("Specified implementation '" + implementationClass + "' not found.");
                        throw new ExceptionInInitializerError("Could not initialize " + serviceClass.getSimpleName() + " service.");
                    } else {
                        LOGGER.info("Specified implementation '" + implementationClass + "' loaded.");
                    }
                } else {
                    Class<T> clazz = (Class<T>) serviceClassRefs.get(0);
                    instance = createInstance(clazz);
                    LOGGER.info(String.format("%s class implementation loaded: %s", serviceClass.getSimpleName(), clazz.getName()));
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
        try {
            for (Method method : clazz.getDeclaredMethods()) {
                if ("getInstance".equals(method.getName()) && Modifier.isStatic(method.getModifiers())) {
                    return (T) method.invoke(null);
                }
            }

            return clazz.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }
}
