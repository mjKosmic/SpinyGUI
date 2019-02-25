package com.spinyowl.spinygui.backend.core.renderer;

public final class RendererProvider {
    private static Renderer renderer;

    private RendererProvider() {
    }

    public static Renderer getRenderer() {
        return renderer;
    }

    public static void setRenderer(Renderer renderer) {
        if (renderer != null) {
            RendererProvider.renderer = renderer;
        }
    }
}
