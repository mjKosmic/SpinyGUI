package com.spinyowl.spinygui.backend.glfwutil.callback.chain;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowMaximizeCallbackI;

/**
 * Instances of this interface may be passed to the {@link GLFW#glfwSetWindowMaximizeCallback SetWindowMaximizeCallback} method.
 */
public interface IChainWindowMaximizeCallback extends IChainCallback<GLFWWindowMaximizeCallbackI>, GLFWWindowMaximizeCallbackI {
}
