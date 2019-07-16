package com.spinyowl.spinygui.core.style.stubServices;

import com.spinyowl.spinygui.core.api.Monitor;
import com.spinyowl.spinygui.core.system.service.MonitorService;

import java.util.List;

public class StubMonitorService implements MonitorService {
    @Override
    public Monitor getPrimaryMonitor() {
        return null;
    }

    @Override
    public List<Monitor> getMonitors() {
        return null;
    }
}