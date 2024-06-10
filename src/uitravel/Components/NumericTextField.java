package uitravel.Components;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumericTextField extends JTextField {

    public NumericTextField() {
        this(10); // Default to 10 columns
    }

    public NumericTextField(int columns) {
        super(columns);
        // Add a key listener to ensure only numeric input is allowed
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignore non-numeric input
                }
            }
        });
    }

    @Override
    public void setText(String t) {
        if (isNumeric(t)) {
            super.setText(t);
        }
    }

    @Override
    public String getText() {
        String text = super.getText();
        return isNumeric(text) ? text : "";
    }

    private boolean isNumeric(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
