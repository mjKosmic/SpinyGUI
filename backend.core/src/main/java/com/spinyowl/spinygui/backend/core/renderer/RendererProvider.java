package com.spinyowl.spinygui.backend.core.renderer;

public final class RendererProvider {
    private static CoreRenderer renderer;

    private RendererProvider() {
    }

    public static CoreRenderer getRenderer() {
        return renderer;
    }

    public static void setRenderer(CoreRenderer renderer) {
        if (renderer != null) {
            RendererProvider.renderer = renderer;
        }
    }
}
