package com.spinyowl.spinygui.core.system.service;

import com.spinyowl.spinygui.core.system.render.NodeRenderer;

public interface RendererFactoryService {

    <T> NodeRenderer<T> getRenderer(Class<T> elementClass);
}
