package com.spinyowl.spinygui.backend.glfwutil.callback.chain.impl;

import com.spinyowl.spinygui.backend.glfwutil.callback.chain.AbstractChainCallback;
import com.spinyowl.spinygui.backend.glfwutil.callback.chain.IChainWindowIconifyCallback;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowIconifyCallbackI;

/**
 * Chain callback implementation based on {@link AbstractChainCallback}.
 *
 * Instances of this interface may be passed to the {@link GLFW#glfwSetWindowIconifyCallback SetWindowIconifyCallback} method.
 */
public class ChainWindowIconifyCallback extends AbstractChainCallback<GLFWWindowIconifyCallbackI> implements IChainWindowIconifyCallback {
    @Override
    public void invoke(long window, boolean iconified) {
        callbackChain.forEach(c -> c.invoke(window, iconified));
    }
}
