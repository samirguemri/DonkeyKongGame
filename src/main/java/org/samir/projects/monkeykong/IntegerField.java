package org.samir.projects.monkeykong;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;

public class IntegerField extends TextField {

    public IntegerField(){
        this.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),
                                                0,
                                                    integerFilter));
    }

    private UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("-?([1-9][0-9]*)?")) {
            return change;
        }
        return null;
    };

}
