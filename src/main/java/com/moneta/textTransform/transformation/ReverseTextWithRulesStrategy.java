package com.moneta.textTransform.transformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.Normalizer;

@Component
public class ReverseTextWithRulesStrategy implements TextTransformationStrategy{

    private static final Logger logger = LoggerFactory.getLogger(ReverseTextWithRulesStrategy.class);

    /**
     * Reverses the input text and applies transformation rules:
     * - Vowels (a, e, i, o, u, y, á, é, í, ó, ú, ý) are converted to uppercase.
     * - All other characters are converted to lowercase.
     * Logs the input text and the resulting reversed text for debugging.
     *
     * @param input The text to be reversed and transformed.
     * @return The transformed text, reversed with rules applied.
     */
    @Override
    public String apply(String input) {
        logger.debug("Reversing text with rules: {}", input);
        StringBuilder result = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            if (isVowel(c)) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        String reversedText = result.toString();
        logger.debug("Reversed text: {}", reversedText);
        return reversedText;
    }

    /**
     * Determines whether a given character is a vowel.
     * Vowels include: a, e, i, o, u, y (and their Czech variants: á, é, í, ó, ú, ý).
     * Diacritical marks are stripped before checking.
     *
     * @param c The character to check.
     * @return True if the character is a vowel; otherwise false.
     */
    private boolean isVowel(char c) {
        String normalizedChar = Normalizer.normalize(String.valueOf(c), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return "aeiouyáéíóúý".contains(normalizedChar.toLowerCase());
    }
}
