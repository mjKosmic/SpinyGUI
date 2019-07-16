package com.spinyowl.spinygui.core.style.stubServices;

import com.spinyowl.spinygui.core.system.service.ClipboardService;

public class StubClipboardService implements ClipboardService {
    @Override
    public String getClipboardString() {
        return null;
    }

    @Override
    public void setClipboardString(String string) {

    }
}