package com.spinyowl.spinygui.core.converter.css3.visitor;

import com.spinyowl.spinygui.core.converter.css3.CSS3BaseVisitor;
import com.spinyowl.spinygui.core.converter.css3.CSS3Parser;
import com.spinyowl.spinygui.core.style.property.Property;
import com.spinyowl.spinygui.core.style.types.Color;

public class PropertyVisitor extends CSS3BaseVisitor<Property> {

    @Override
    public Property visitKnownDeclaration(CSS3Parser.KnownDeclarationContext ctx) {
        var name = ctx.property().getText();
        var value = new ValueVisitor().visit(ctx.expr());
        if (value == null)
            value = parseValue(name, ctx.expr().getText());
        return new Property(name, value);
    }

    private Object parseValue(String name, String value) {
        if (value.equals("none"))
            return null;
        if ("background".equals(name) || "color".equals(name)) {
            return Color.getColorByName(value);
        }
        return null;
    }

}
