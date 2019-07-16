package com.spinyowl.spinygui.core.system;

import com.spinyowl.spinygui.core.Configuration;
import com.spinyowl.spinygui.core.system.service.*;

import java.util.Objects;

import static com.spinyowl.spinygui.core.system.ServiceInitializingUtil.initializeService;

public final class Services {

    private static MonitorService monitorService;
    private static WindowService windowService;
    private static ClipboardService clipboardService;
    private static RendererProviderService rendererProviderService;
    private static TimeService timeService;

    static {
        if (Configuration.INITIALIZE_SERVICES.getState(true)) {

            monitorService =
                initializeService(MonitorService.class,
                    Configuration.MONITOR_SERVICE.getValue());

            windowService =
                initializeService(WindowService.class,
                    Configuration.WINDOW_SERVICE.getValue());

            clipboardService =
                initializeService(ClipboardService.class,
                    Configuration.CLIPBOARD_SERVICE.getValue());

            rendererProviderService =
                initializeService(RendererProviderService.class,
                    Configuration.RENDERER_PROVIDER_SERVICE.getValue());

            timeService =
                initializeService(TimeService.class,
                    Configuration.TIME_SERVICE.getValue());


        }
    }

    private Services() {
    }

    public static MonitorService getMonitorService() {
        return monitorService;
    }

    public static void setMonitorService(MonitorService monitorService) {
        Objects.requireNonNull(monitorService);
        Services.monitorService = monitorService;
    }

    public static WindowService getWindowService() {
        return windowService;
    }

    public static void setWindowService(WindowService windowService) {
        Objects.requireNonNull(windowService);
        Services.windowService = windowService;
    }

    public static ClipboardService getClipboardService() {
        return clipboardService;
    }

    public static void setClipboardService(ClipboardService clipboardService) {
        Objects.requireNonNull(clipboardService);
        Services.clipboardService = clipboardService;
    }

    public static RendererProviderService getRendererProviderService() {
        return rendererProviderService;
    }

    public static void setRendererProviderService(RendererProviderService rendererProviderService) {
        Objects.requireNonNull(rendererProviderService);
        Services.rendererProviderService = rendererProviderService;
    }

    public static TimeService getTimeService() {
        return timeService;
    }

    public static void setTimeService(TimeService timeService) {
        Objects.requireNonNull(timeService);
        Services.timeService = timeService;
    }
}
