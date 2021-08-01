package com.udemy.example.textBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "text")
public class TextBoxController {
    private TextBoxService textBoxService;

    @Autowired
    public TextBoxController(TextBoxService textBoxService) {
        this.textBoxService = textBoxService;
    }

    @PutMapping
    public ResponseEntity<Object> changeText(String text) {
        this.textBoxService.changeText(text);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("text").build().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public String displayText() {
        return this.textBoxService.displayText();
    }

    @DeleteMapping
    public void clearText() {
        this.textBoxService.clearText();
    }

}
