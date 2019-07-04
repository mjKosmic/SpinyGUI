package com.spinyowl.spinygui.backend.opengl32.service;

import com.spinyowl.spinygui.backend.opengl32.renderer.component.DefaultNodeRenderer;
import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.system.render.NodeRenderer;
import com.spinyowl.spinygui.core.system.service.RendererFactoryService;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SpinyGuiOpenGL32RendererFactoryService implements RendererFactoryService {
    private static final SpinyGuiOpenGL32RendererFactoryService INSTANCE = new SpinyGuiOpenGL32RendererFactoryService();

    private Map<Class<?>, NodeRenderer> rendererMap;

    private SpinyGuiOpenGL32RendererFactoryService() {
        rendererMap = new ConcurrentHashMap<>();
        rendererMap.put(Node.class, new DefaultNodeRenderer());
    }

    public static SpinyGuiOpenGL32RendererFactoryService getInstance() {
        return INSTANCE;
    }

    @Override
    public <T> NodeRenderer<T> getRenderer(Class<T> elementClass) {
        if (Node.class.isAssignableFrom(elementClass)) {
            Class<? extends Node> e = (Class<? extends Node>) elementClass;
            return (NodeRenderer<T>) getComponentRenderer(e);
        }
        return null;
    }

    private <C extends Node> NodeRenderer<C> getComponentRenderer(Class<C> componentClass) {
        return this.cycledSearch(componentClass, Node.class, rendererMap, null);
    }

    private <A, B extends A, C> C cycledSearch(Class<B> classToSearch, Class<A> rootClass, Map map, C defaultRenderer) {
        Objects.requireNonNull(classToSearch);
        Objects.requireNonNull(rootClass);
        Objects.requireNonNull(map);
        C renderer = null;
        Class cClass = classToSearch;
        while (renderer == null) {
            renderer = ((Map<Class<B>, C>) map).get(cClass);
            if (cClass.isAssignableFrom(rootClass)) {
                break;
            }
            cClass = cClass.getSuperclass();
        }
        if (renderer == null) {
            renderer = defaultRenderer;
        }
        return renderer;
    }

}
