package com.spinyowl.spinygui.backend.glfwutil.callback.chain;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

/**
 * Instances of this interface may be passed to the {@link GLFW#glfwSetKeyCallback SetKeyCallback} method.
 */
public interface IChainKeyCallback extends IChainCallback<GLFWKeyCallbackI>, GLFWKeyCallbackI {
}
