package com.spinyowl.spinygui.backend.glfwutil.callback.chain.impl;

import com.spinyowl.spinygui.backend.glfwutil.callback.chain.AbstractChainCallback;
import com.spinyowl.spinygui.backend.glfwutil.callback.chain.IChainWindowContentScaleCallback;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowContentScaleCallbackI;

/**
 * Chain callback implementation based on {@link AbstractChainCallback}.
 *
 * Instances of this interface may be passed to the {@link GLFW#glfwSetWindowContentScaleCallback SetWindowContentScaleCallback} method.
 */
public class ChainWindowContentScaleCallback extends AbstractChainCallback<GLFWWindowContentScaleCallbackI> implements IChainWindowContentScaleCallback {
    /**
     * Will be called when the window content scale changes.
     *
     * @param window the window whose content scale changed
     * @param xscale the new x-axis content scale of the window
     * @param yscale the new y-axis content scale of the window
     */
    @Override
    public void invoke(long window, float xscale, float yscale) {
        callbackChain.forEach(c -> c.invoke(window, xscale, yscale));
    }
}
