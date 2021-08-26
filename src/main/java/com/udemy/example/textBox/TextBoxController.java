package com.udemy.example.textBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Locale;

@RestController
@RequestMapping(path = "text")
public class TextBoxController {
    @Autowired
    private MessageSource messageSource;

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

    @GetMapping(path = "/international/good-morning")
    public String displayInternationalText() {
//            @RequestHeader(name = "Accept-Language", required = false) Locale locale){

        return messageSource.getMessage(
                "good.morning.message",
                null,
                "Good Morning",
                LocaleContextHolder.getLocale());
//        return this.textBoxService.displayText();
    }

    @DeleteMapping
    public void clearText() {
        this.textBoxService.clearText();
    }

}
