package com.udemy.example.textBox;

import javax.persistence.criteria.CriteriaBuilder;

public class TextBox {
    private String text;

    public TextBox(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextBox{" +
                "text='" + text + '\'' +
                '}';
    }
}
