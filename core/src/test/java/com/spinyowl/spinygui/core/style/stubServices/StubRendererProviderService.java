package com.spinyowl.spinygui.core.style.stubServices;

import com.spinyowl.spinygui.core.system.render.NodeRenderer;
import com.spinyowl.spinygui.core.system.service.RendererProviderService;

public class StubRendererProviderService implements RendererProviderService {
    @Override
    public <T> NodeRenderer<T> getRenderer(Class<T> nodeClass) {
        return null;
    }
}