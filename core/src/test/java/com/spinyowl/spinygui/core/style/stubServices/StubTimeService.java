package com.spinyowl.spinygui.core.style.stubServices;

import com.spinyowl.spinygui.core.system.service.TimeService;

public class StubTimeService implements TimeService {
    @Override
    public double getTime() {
        return 0;
    }
}
