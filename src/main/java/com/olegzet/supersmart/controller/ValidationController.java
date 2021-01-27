package com.olegzet.supersmart.controller;

import com.olegzet.supersmart.model.Transaction;
import com.olegzet.supersmart.service.ValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

@RestController
@Scope(SCOPE_REQUEST)
@Tag(name = "Validation", description = "REST API for Validations")
@RequestMapping("/validations")
public class ValidationController {
    @Autowired
    ValidationService validationService;

    @Operation(summary = "Make Validation",
            description = "Make Validation for Transaction",
            tags = {"Validation"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            description =
                                    "Validation of Transaction was successful. " +
                                            "Result of Validation was provided with output payload.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Wrong parameters of request.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseError.class))),
                    @ApiResponse(responseCode = "406",
                            description = "Some parameters of request are not applicable.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseError.class)))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Transaction payload", required = true,
                    content = @Content(schema = @Schema(implementation = Transaction.class),
                            examples =
                            @ExampleObject(name = "An example request with the minimum required fields to create.",
                                    value = "{\"items\":[{\"itemType\":\"unit\",\"id\":\"11\",\"barcode\":\"012345678912\",\"weight\":11},{\"itemType\":\"unit\",\"id\":\"12\",\"barcode\":\"012345678912\",\"weight\":11},{\"itemType\":\"unit\",\"id\":\"13\",\"barcode\":\"012345678912\",\"weight\":11},{\"itemType\":\"unit\",\"id\":\"14\",\"barcode\":\"012345678912\",\"weight\":11},{\"itemType\":\"weighted\",\"id\":\"21\",\"barcode\":\"01234567891234\",\"weight\":3},{\"itemType\":\"weighted\",\"id\":\"22\",\"barcode\":\"01234567891234\",\"weight\":3},{\"itemType\":\"weighted\",\"id\":\"23\",\"barcode\":\"01234567891234\",\"weight\":3},{\"itemType\":\"green\",\"id\":\"31\",\"barcode\":\"987654321012\",\"weight\":20},{\"itemType\":\"green\",\"id\":\"32\",\"barcode\":null,\"weight\":20},{\"itemType\":\"green\",\"id\":\"33\",\"barcode\":\"987654321012\",\"weight\":20},{\"itemType\":\"green\",\"id\":\"34\",\"barcode\":\"987654321012\",\"weight\":20},{\"itemType\":\"green\",\"id\":\"35\",\"barcode\":\"987654321012\",\"weight\":20}]}"
                            ))))


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResultResponse calculations(@RequestBody Transaction transaction) {
        return new ResultResponse(validationService.validate(transaction));
    }
}
