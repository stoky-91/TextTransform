package com.moneta.textTransform.controller;

import com.moneta.textTransform.service.TextTransformationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class TextTransformControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TextTransformationService transformationService;

    @Test
    void shouldTransformTextAndReturnCorrectResponse() throws Exception {
        // Arrange
        String input = "Ahoj, jak   se máš?";
        String expectedResult = "?šÁm Es kAj ,jOhA";

        // Act & Assert
        mockMvc.perform(post("/api/text/transform")
                        .contentType("application/json")
                        .content(input))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }
}
