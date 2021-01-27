package com.olegzet.supersmart.controller;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

@SpringBootTest
@AutoConfigureMockMvc
public class ValidationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validationWithWrongPayloadShouldReturnErrorMessage() throws Exception {
        this.mockMvc.perform(
                post("/validations").content("{\"items\":[]}")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Transaction must have items for validating"));
    }

    @Test
    public void validationSuccessfulResult() throws Exception {
        String successfulJson = IOUtils.toString(this.getClass()
                .getResourceAsStream("/json/successfulTransaction.json"), StandardCharsets.UTF_8);
        this.mockMvc.perform(
                post("/validations").content(successfulJson)
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"validationResult\": true}"));
    }

    @Test
    public void validationUnSuccessfulResult() throws Exception {
        String successfulJson = IOUtils.toString(this.getClass()
                .getResourceAsStream("/json/unsuccessfulTransaction.json"), StandardCharsets.UTF_8);
        this.mockMvc.perform(
                post("/validations").content(successfulJson)
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"validationResult\": false}"));
    }
}
