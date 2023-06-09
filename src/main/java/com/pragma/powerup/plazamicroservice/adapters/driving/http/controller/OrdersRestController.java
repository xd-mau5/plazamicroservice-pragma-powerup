package com.pragma.powerup.plazamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishesOrderedRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IDishesOrderedHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IOrdersHandler;
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
@RequestMapping("/order/")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class OrdersRestController {
    private final IOrdersHandler ordersHandler;
    private final IDishesOrderedHandler dishesOrderedHandler;
    @Operation(summary = "Save order", description = "This endpoint saves the information of the order",
            responses = {
            @ApiResponse(responseCode = "201", description = "Order saved",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "400", description = "Invalid order",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "401", description = "User is not a client",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "409", description = "Order already exist",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))})
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveOrder(
            @RequestBody OrderRequestDto orderRequestDto
    ) {
        ordersHandler.saveOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.ORDER_CREATED_MESSAGE));
    }
    @Operation(summary = "Save the dishes ordered", description = "This endpoint saves the dishes ordered, this is used after the order is created",
            responses = {
            @ApiResponse(responseCode = "201", description = "Dishes ordered saved",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "400", description = "Invalid dishes ordered"
                    , content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "401", description = "User is not a client",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "409", description = "Dishes ordered already exist",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))})
    @PostMapping("/save/dishes")
    public ResponseEntity<Map<String, String>> saveDishesOrdered(
            @RequestBody DishesOrderedRequestDto dishesOrderedRequestDto
    ) {
        dishesOrderedHandler.saveDishesOrdered(dishesOrderedRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.DISHES_ORDERED_CREATED_MESSAGE));
    }
    @Operation(summary = "Set a order to a employee", description = "This endpoint set a order to a employee",
            responses = {
            @ApiResponse(responseCode = "200", description = "Order set to employee",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "400", description = "Invalid order or employee",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "401", description = "User is not a employee",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "404", description = "Order or employee not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))})
    @PutMapping("/set/employee/{idOrder}")
    public ResponseEntity<Map<String, String>> setOrderToEmployee(
            @PathVariable Long idOrder,
            @RequestParam Long idEmployee
    ) {
        ordersHandler.setOrderToEmployee(idOrder, idEmployee);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.ORDER_SET_TO_EMPLOYEE_MESSAGE));
    }
    @Operation(summary = "Change the status of a order", description = "This endpoint change the status of a order",
            responses = {
            @ApiResponse(responseCode = "200", description = "Order status changed",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "400", description = "Invalid order or status",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "401", description = "User is not a employee",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))})
    @PutMapping("/change/status/{idOrder}")
    public ResponseEntity<Map<String, String>> changeOrderStatus(
            @PathVariable Long idOrder,
            @RequestParam String status
    ) {
        ordersHandler.updateOrder(idOrder, status);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.ORDER_STATUS_CHANGED_MESSAGE));
    }
    @Operation(summary = "Get all orders", description = "This endpoint get all orders filtered by status",
            responses = {
            @ApiResponse(responseCode = "200", description = "Orders found",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "401", description = "User is not a employee",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))})
    @PutMapping("/get/all/{status}")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(
            @PathVariable String status,
            @RequestParam Long restaurantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<OrderResponseDto> orders = ordersHandler.getAllOrdersByStatus(restaurantId, status, page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(orders);
    }
}