package com.spinyowl.spinygui.backend.core.renderer;

import com.spinyowl.spinygui.backend.core.context.Context;
import com.spinyowl.spinygui.core.api.Frame;
import com.spinyowl.spinygui.core.api.Layer;
import com.spinyowl.spinygui.core.api.LayerContainer;

public abstract class AbstractCoreRenderer implements CoreRenderer {

    protected abstract void preRender(Context c);

    @Override
    public void render(Frame f, Context c) {
        if (f == null) return;

        preRender(c);
        for (Layer layer : f.getAllLayers()) {
            LayerContainer container = layer.getContainer();
            container.getRenderer().render(container);
        }
        postRender(c);
    }

    protected abstract void postRender(Context c);
}
