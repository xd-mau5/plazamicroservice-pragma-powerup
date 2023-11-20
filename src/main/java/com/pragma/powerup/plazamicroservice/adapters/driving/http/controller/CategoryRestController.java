package com.pragma.powerup.plazamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.CategoryRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.ICategoryHandler;
import com.pragma.powerup.plazamicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class CategoryRestController {
    private final ICategoryHandler categoryHandler;
    @Operation(summary = "Save a category",
            responses = {
                @ApiResponse(responseCode = "201", description = "Category saved",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                @ApiResponse(responseCode = "400", description = "Invalid category name",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                @ApiResponse(responseCode = "401", description = "User is not a owner",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                @ApiResponse(responseCode = "409", description = "Category already exist",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> saveCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        categoryHandler.saveCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.CATEGORY_CREATED_MESSAGE));
            }

}
