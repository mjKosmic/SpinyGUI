package com.spinyowl.spinygui.core.system.service;

import com.spinyowl.spinygui.core.system.render.NodeRenderer;

/**
 * Renderer provider service. Used to obtain renderer for specified element type.
 */
public interface RendererProviderService {

    /**
     * Provides renderer for specified element type.
     *
     * @param elementClass element class.
     * @param <T>          type of element
     * @return renderer for specified element.
     */
    <T> NodeRenderer<T> getRenderer(Class<T> elementClass);

}
