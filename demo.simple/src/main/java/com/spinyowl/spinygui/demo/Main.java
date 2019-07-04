package com.spinyowl.spinygui.demo;

import com.spinyowl.spinygui.core.api.Monitor;
import com.spinyowl.spinygui.core.api.Window;
import com.spinyowl.spinygui.core.node.*;
import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.node.base.Text;
import com.spinyowl.spinygui.core.converter.dom.NodeMarshaller;
import com.spinyowl.spinygui.core.event.listener.impl.DefaultWindowCloseEventListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Created by ShchAlexander on 09.08.2018.
 */
public class Main {
    private static final Log LOGGER = LogFactory.getLog(Main.class);

    public static void main(String[] args) throws Exception {

        LOGGER.info("a core message");
        Monitor monitor = Monitor.getPrimaryMonitor();
        System.out.println(monitor);

        Window window = Window.createWindow(800, 600, "Example");
        window.addWindowCloseEventListener(new DefaultWindowCloseEventListener());

        Input input = new Input();
        input.setName("password");
        input.setValue("PASS_@!@#&");

        Button button = new Button();
        Text buttonText = new Text("\n\n\tFOrmantted text\n\t\n\n asdfasdfa\n");
        Panel buttonPanel = new Panel();
        Text buttonPanelText = new Text("Bold");
        Pre buttonPre = new Pre();
        Text buttonPreText = new Text("\n\n\tFOrmantted text\n\t\n\n asdfasdfa\n");
        RadioButton radioButton = new RadioButton();
        Node element = new Panel()
            .add(button
                .add(buttonText)
                .add(buttonPanel.add(buttonPanelText))
                .add(buttonPre.add(buttonPreText))
            )
            .add(input)
            .add(radioButton);
        element.setPosition(100, 100);
        window.getContainer().add(element);

        String xml = NodeMarshaller.marshal(element, false);
        System.out.println(xml);

        String xml2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<div>\n" +
            "  <button>asdfasdfasd</button>" +
            "  <button>\n" +
            "    \n\n\n\n\n\n" +
            "    s\n" +
            "    \n" +
            "    Hello World\n\n\n\n\n" +
            "    <pre>\n" +
            "    \n\n\n\n\n\n" +
            "    s\n" +
            "    \n" +
            "    Hello World\n" +
            "    </pre>\n" +
            "    <div>Bold</div>\n" +
            "  </button>\n" +
            "  <input name=\"password\" value=\"PASS_@!@#&amp;\" />\n" +
            "  <RadioButton />\n" +
            "</div>";
        Node unmarshal = NodeMarshaller.unmarshal(xml2);
        System.out.println(NodeMarshaller.marshal(unmarshal));
        System.out.println(NodeMarshaller.marshal(unmarshal, false));
        window.setVisible(true);
    }

}
