package com.spinyowl.spinygui.backend.opengl32.renderer;

import com.spinyowl.spinygui.backend.core.context.Context;
import com.spinyowl.spinygui.backend.core.renderer.AbstractRenderer;
import com.spinyowl.spinygui.backend.core.renderer.RendererProvider;
import org.lwjgl.nanovg.NanoVGGL2;
import org.lwjgl.nanovg.NanoVGGL3;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.glGetInteger;

public class NvgRenderer extends AbstractRenderer {
    private boolean isVersionNew;
    private long nvgContext;

    @Override
    public void initialize() {
        isVersionNew = (glGetInteger(GL30.GL_MAJOR_VERSION) > 3) || (glGetInteger(GL30.GL_MAJOR_VERSION) == 3 && glGetInteger(GL30.GL_MINOR_VERSION) >= 2);

        if (isVersionNew) {
            int flags = NanoVGGL3.NVG_STENCIL_STROKES | NanoVGGL3.NVG_ANTIALIAS;
            nvgContext = NanoVGGL3.nvgCreate(flags);
        } else {
            int flags = NanoVGGL2.NVG_STENCIL_STROKES | NanoVGGL2.NVG_ANTIALIAS;
            nvgContext = NanoVGGL2.nvgCreate(flags);
        }
//        imageReferenceManager = new NvgLoadableImageReferenceManager();
//        RendererProvider.getInstance().getComponentRenderers().forEach(ComponentRenderer::initialize);
    }

    @Override
    protected void preRender(Context c) {

    }


    @Override
    protected void postRender(Context c) {

    }

    @Override
    public void destroy() {
        if (isVersionNew) {
            NanoVGGL3.nnvgDelete(nvgContext);
        } else {
            NanoVGGL2.nnvgDelete(nvgContext);
        }
//        RendererProvider.getInstance().getComponentRenderers().forEach(ComponentRenderer::destroy);
//        imageReferenceManager.destroy();
    }
}
