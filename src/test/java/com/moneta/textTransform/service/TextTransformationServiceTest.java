package com.moneta.textTransform.service;

import com.moneta.textTransform.transformation.RemoveExtraSpacesStrategy;
import com.moneta.textTransform.transformation.ReverseTextWithRulesStrategy;
import com.moneta.textTransform.transformation.TextTransformationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextTransformationServiceTest {

    private TextTransformationService transformationService;

    @BeforeEach
    void setUp() {
        // Initialize service with both strategies
        transformationService = new TextTransformationService(
                List.of(new RemoveExtraSpacesStrategy(), new ReverseTextWithRulesStrategy())
        );
    }

    @Test
    void shouldTransformTextCorrectly() {
        // Arrange
        TextTransformationStrategy strategyMock = Mockito.mock(TextTransformationStrategy.class);
        Mockito.when(strategyMock.apply("Ahoj, jak se máš?")).thenReturn("šÁM es kaj ,jOha");

        TextTransformationService service = new TextTransformationService(Arrays.asList(strategyMock));

        // Act
        String result = service.transform("Ahoj, jak se máš?");

        // Assert
        assertEquals("šÁM es kaj ,jOha", result);
    }

    @Test
    void testBasicScenario() {
        String input = "Ahoj, jak se máš?";
        String expected = "?šÁm Es kAj ,jOhA";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testMultipleSpaces() {
        String input = "Je     mi   fajn.";
        String expected = ".njAf Im Ej";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testTextWithDiacritics() {
        String input = "Příliš žluťoučký kůň úpěl ďábelské ódy";
        String expected = "YdÓ ÉkslEbÁď lĚpÚ ňŮk ÝkčUOťUlž šIlÍřp";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testUpperCaseText() {
        String input = "HELLO World";
        String expected = "dlrOw OllEh";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testEmptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testOnlySpaces() {
        String input = "     ";
        String expected = "";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testSpecialCharactersAndNumbers() {
        String input = "1234 !!! ABC def.";
        String expected = ".fEd cbA !!! 4321";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testTextWithoutVowels() {
        String input = "Trn, čtvrť.";
        String expected = ".ťrvtč ,nrt";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testOnlyVowels() {
        String input = "a e i o u á é í ó ú";
        String expected = "Ú Ó Í É Á U O I E A";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testTextWithTabsAndNewLines() {
        String input = "Line1\tLine2\nLine3";
        String expected = "3EnIl 2EnIl 1EnIl";
        assertEquals(expected, transformationService.transform(input));
    }

    @Test
    void testNonEnglishCharacters() {
        String input = "Привет мир";
        String expected = "рим тевирп";
        assertEquals(expected, transformationService.transform(input));
    }
}
