package com.spinyowl.spinygui.core.converter;

import com.spinyowl.spinygui.core.converter.css3.CSS3Lexer;
import com.spinyowl.spinygui.core.converter.css3.CSS3Parser;
import com.spinyowl.spinygui.core.converter.css3.StyleSheetException;
import com.spinyowl.spinygui.core.converter.css3.visitor.StyleSheetVisitor;
import com.spinyowl.spinygui.core.style.StyleSheet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class StyleSheetFactory {
    private static final Log LOGGER = LogFactory.getLog(StyleSheetFactory.class);

    /**
     * @param css
     * @return
     */
    public static StyleSheet createFromCSS(String css) throws StyleSheetException {

        try {
            var charStream = CharStreams.fromString(css);
            var lexer = new CSS3Lexer(charStream);

            var tokenStream = new CommonTokenStream(lexer);
            var parser = new CSS3Parser(tokenStream);

            CSS3Parser.StylesheetContext stylesheet = parser.stylesheet();
            return new StyleSheetVisitor().visit(stylesheet);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new StyleSheetException();
        }
    }
}
