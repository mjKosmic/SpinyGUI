package com.spinyowl.spinygui.core.event.processor;

import com.spinyowl.spinygui.core.api.Layer;
import com.spinyowl.spinygui.core.api.Window;
import com.spinyowl.spinygui.core.event.Event;
import com.spinyowl.spinygui.core.event.listener.Listener;
import com.spinyowl.spinygui.core.node.base.Container;
import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.system.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DefaultEventProcessor extends EventProcessor {
    private Queue<Event> eventQueue = new LinkedBlockingQueue<>();

    @Override
    public void pushEvent(Event event) {
        eventQueue.add(event);
    }

    @Override
    public void processEvents() {
        List<Event> events = new ArrayList<>(eventQueue);
        for (Event event : events) {
            processEvent(event);
        }
        eventQueue.removeAll(events);
    }

    private void processEvent(Event event) {
        Node target = event.target;
        if (target != null) {
            List<? extends Listener<? extends Event>> listeners = target.getListenersFor(event.getClass());
            for (Listener listener : listeners) {
                listener.process(event);
            }
        } else {
            for (Window window : Services.getWindowService().getWindows()) {
                if (window.isClosed()) continue;
                for (Layer layer : window.getFrame().getAllLayers()) {
                    Container container = layer.getContainer();
                    processContainerEvent(event, container);
                }
            }
        }

//        if (event instanceof WindowCloseEvent) {
//            WindowCloseEvent wce = (WindowCloseEvent) event;
//            Window target = wce.target;
//            for (Listener<WindowCloseEvent> listener : target.getWindowCloseEventListeners()) {
//                listener.process(wce);
//            }
//        } else if (event instanceof MouseEvent) {
//            MouseEvent mouseEvent = (MouseEvent) event;
//            Node target = mouseEvent.target;
////            List<Listener<MouseEvent>> listeners = target.getMouseEventListeners();
//        }
    }

    private void processContainerEvent(Event event, Container container) {
        processEvent(event, container);
        for (Node node : container.getChildNodes()) {
            if (node instanceof Container) processContainerEvent(event, (Container) node);
            else processEvent(event, node);
        }
    }

    private void processEvent(Event event, Node container) {
        if (container.hasListenersFor(event.getClass())) {
            for (Listener listener : container.getListenersFor(event.getClass())) {
                listener.process(event);
            }
        }
    }
}
