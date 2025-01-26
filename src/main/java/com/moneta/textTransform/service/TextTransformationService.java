package com.moneta.textTransform.service;

import com.moneta.textTransform.transformation.TextTransformationStrategy;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;

@Service
public class TextTransformationService {

    private final List<TextTransformationStrategy> strategies;
    private static final Logger logger = LoggerFactory.getLogger(TextTransformationService.class);

    public TextTransformationService(List<TextTransformationStrategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * Transforms the input text by sequentially applying all registered transformation strategies.
     * Logs the input text, intermediate results after each transformation, and the final result.
     *
     * @param input The text to be transformed.
     * @return The transformed text after applying all strategies.
     */
    public String transform(String input) {
        logger.info("Transforming input text: {}", input);
        String result = input;
        for (TextTransformationStrategy strategy : strategies) {
            result = strategy.apply(result);
            logger.info("Intermediate result after {}: {}", strategy.getClass().getSimpleName(), result);
        }
        logger.info("Final transformed text: {}", result);
        return result;
    }
}
