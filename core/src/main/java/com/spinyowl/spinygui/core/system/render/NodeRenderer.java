package com.spinyowl.spinygui.core.system.render;

import com.spinyowl.spinygui.core.system.context.Context;

public interface NodeRenderer<T> {

    void render(T element, Context context);

}
