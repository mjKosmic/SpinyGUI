package com.spinyowl.spinygui.backend.glfwutil.callback.chain.impl;

import com.spinyowl.spinygui.backend.glfwutil.callback.chain.AbstractChainCallback;
import com.spinyowl.spinygui.backend.glfwutil.callback.chain.IChainKeyCallback;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

/**
 * Chain callback implementation based on {@link AbstractChainCallback}.
 *
 * Instances of this interface may be passed to the {@link GLFW#glfwSetKeyCallback SetKeyCallback} method.
 */
public class ChainKeyCallback extends AbstractChainCallback<GLFWKeyCallbackI> implements IChainKeyCallback {
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        callbackChain.forEach(c -> c.invoke(window, key, scancode, action, mods));
    }
}
