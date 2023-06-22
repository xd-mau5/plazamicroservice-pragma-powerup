package com.pragma.powerup.plazamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.TractabilityRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.TractabilityResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.ITractabilityHandler;
import com.pragma.powerup.plazamicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tractability")
@RequiredArgsConstructor
public class TractabilityController {
    private final ITractabilityHandler tractabilityHandler;
    @Operation(summary = "Get all tractabilities",
            description = "Get all tractabilities",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All tractabilities",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "404", description = "Tractabilities not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})

    @GetMapping("/all")
    public ResponseEntity<List<TractabilityResponseDto>> getAllTractabilities(
            @Parameter(description = "Page number", example = "0") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Page size", example = "10") @RequestParam(defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(tractabilityHandler.getAllTractabilities(page, size));
    }
    @Operation(summary = "Get tractabilities from user",
            description = "Get tractabilities from user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tractabilities from user",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "404", description = "Tractabilities not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<TractabilityResponseDto>> findAllByClientId(
            @PathVariable Long clientId
    ){
        List<TractabilityResponseDto> tractabilityResponseDto = tractabilityHandler.findAllByClientId(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(tractabilityResponseDto);
    }
    @Operation(summary = "Save a tractability",
            description = "Save a tractability",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tractability saved",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "404", description = "Tractability not saved",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveTractability(@RequestBody TractabilityRequestDto tractabilityRequestDto) {
        tractabilityHandler.saveTractability(tractabilityRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.TRACTABILITY_SAVED_MESSAGE));
    }
    @Operation(summary = "Calculate duration per order",
            description = "Calculate duration per order",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Duration per order",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                            @ApiResponse(responseCode = "400", description = "Bad request",
                                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                            @ApiResponse(responseCode = "404", description = "Duration per order not found",
                                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                            @ApiResponse(responseCode = "500", description = "Internal server error",
                                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/duration/{orderId}")
    public ResponseEntity<Map<String, String>> calculateDurationPerOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,
                tractabilityHandler.calculateDurationPerOrder(orderId)));
    }
    @Operation(summary = "Get the average duration per employee",
            description = "Get the average duration per employee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Average duration per employee",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                            @ApiResponse(responseCode = "400", description = "Bad request",
                                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                            @ApiResponse(responseCode = "404", description = "Average duration per employee not found",
                                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                            @ApiResponse(responseCode = "500", description = "Internal server error",
                                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/average")
    public ResponseEntity<Map<Long, String>> calculateAverageDurationPerEmployee() {
        return ResponseEntity.ok(tractabilityHandler.calculateAverageDurationPerEmployee());
    }
}
