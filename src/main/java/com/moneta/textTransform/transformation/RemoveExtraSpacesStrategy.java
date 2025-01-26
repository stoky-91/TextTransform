package com.moneta.textTransform.transformation;

import org.springframework.stereotype.Component;

@Component
public class RemoveExtraSpacesStrategy implements TextTransformationStrategy {

    @Override
    public String apply(String input) {
        return input.replaceAll("\\s+", " ").trim();
    }
}
