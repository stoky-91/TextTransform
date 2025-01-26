package com.moneta.textTransform.controller;

import com.moneta.textTransform.service.TextTransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/text")
class TextTransformController {

    private final TextTransformationService transformationService;

    @Autowired
    public TextTransformController(TextTransformationService transformationService) {
        this.transformationService = transformationService;
    }

    /**
     * Transforms input text by applying predefined transformation strategies.
     *
     * @param input The text to be transformed, provided in the request body.
     * @return The transformed text.
     */
    @PostMapping("/transform")
    public String transformText(@RequestBody String input) {
        return transformationService.transform(input);
    }
}
