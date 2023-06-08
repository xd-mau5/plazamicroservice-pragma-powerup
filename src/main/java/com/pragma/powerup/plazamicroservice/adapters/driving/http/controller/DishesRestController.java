package com.pragma.powerup.plazamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishesRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.UpdateDishesRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.DishesResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IDishesHandler;
import com.pragma.powerup.plazamicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dishes/")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class DishesRestController {
    private final IDishesHandler dishesHandler;
    @Operation(summary = "Create a new dish",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Dish created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "400", description = "Invalid dish name",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "401", description = "User is not a owner",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "409", description = "Dish already exist",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createDish(@RequestBody DishesRequestDto dishesRequestDto) {
        dishesHandler.createDish(dishesRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.DISH_CREATED_MESSAGE));
    }
    @Operation(summary = "Update a dish",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dish updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "400", description = "Invalid dish name",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "401", description = "User is not a owner",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "404", description = "Dish not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PatchMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateDish(@RequestBody UpdateDishesRequestDto updateDishesRequestDto, @PathVariable Long id) {
        dishesHandler.updateDish(id, updateDishesRequestDto);
        return ResponseEntity.ok().body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.DISH_UPDATED_MESSAGE));
    }
    @Operation(summary = "Enable/disable a dish",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dish updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "401", description = "User is not a owner",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "404", description = "Dish not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PatchMapping("/status/{id}")
    public ResponseEntity<Map<String, String>> updateDishStatus(@PathVariable Long id, @RequestParam boolean status) {
        dishesHandler.updateDishStatus(id, status);
        return ResponseEntity.ok().body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.DISH_SET_STATUS_MESSAGE));
    }
    @Operation(summary = "Get all dishes", description = "Get all dishes from a restaurant, you can filter by category",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dishes found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "401", description = "User is not a owner",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/all/{idRestaurant}")
    public ResponseEntity<List<DishesResponseDto>> getAllDishes(
            @PathVariable Long idRestaurant,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<DishesResponseDto> dishes = dishesHandler.getAllDishes(idRestaurant, page, size, category);
        return ResponseEntity.ok().body(dishes);
    }
}
