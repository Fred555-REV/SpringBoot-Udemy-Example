package com.udemy.example.textBox;

import org.springframework.stereotype.Service;

@Service
public class TextBoxService {
    private TextBox textBox = new TextBox("Text");

    public void changeText(String text) {
        textBox.setText(text);
    }

    public String displayText() {
        return textBox.getText();
    }

    public void clearText() {
        textBox.setText(null);
    }
}
