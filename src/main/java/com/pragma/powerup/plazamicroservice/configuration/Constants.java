package com.pragma.powerup.plazamicroservice.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String RESPONSE_ERROR_KEY = "error";
    public static final String DISH_CREATED_MESSAGE = "Dish created successfully";
    public static final String DISH_ALREADY_EXIST_ERROR = "Dish already exist";
    public static final String DISH_NOT_FOUND_ERROR = "Dish not found";
    public static final String DISH_NAME_IS_INVALID_ERROR = "Dish name is invalid";
    public static final String ORDER_CREATED_MESSAGE = "Order created successfully";
    public static final String DISH_UPDATED_MESSAGE = "Dish updated successfully";
    public static final String ORDER_SET_TO_EMPLOYEE_MESSAGE = "Order set to employee successfully";
    public static final String ORDER_NOT_FOUND_ERROR = "Order doesn't exist";
    public static final String ORDER_DELIVERED_MESSAGE = "Order delivered successfully";
    public static final String NULL_PARAMETER_ERROR = "Parameter is null";
    public static final String ORDER_STATUS_CHANGED_MESSAGE = "Order updated successfully";
    public static final String DISHES_ORDERED_CREATED_MESSAGE = "Dishes ordered created successfully";
    public static final String DISH_SET_STATUS_MESSAGE = "Dish status set successfully";
    public static final String CATEGORY_CREATED_MESSAGE = "Category created successfully";
    public static final String RESTAURANT_CREATED_MESSAGE = "Restaurant created successfully";
    public static final String RESTAURANT_ALREADY_EXIST_ERROR = "Restaurant already exist";
    public static final String RESTAURANT_NAME_IS_INVALID_ERROR = "Restaurant name is invalid";
    public static final String RESTAURANT_NOT_FOUND_ERROR = "Restaurant not found";
    public static final String TRACTABILITY_SAVED_MESSAGE = "Tractability saved";
    public static final String MESSAGE_SENT = "Message sent";
    public static final String INITIAL_STATUS = "Pendiente";
    public static final String CANCELLED_STATUS = "Cancelado";
    public static final String DELIVERED_STATUS = "Entregado";
    public static final String ORDER_ID_CANNOT_BE_NULL_ERROR = "Order id cannot be null";
    public static final String PARAMETER_CANNOT_BE_NULL_ERROR = "Parameter cannot be null";
    public static final String SWAGGER_TITLE_MESSAGE = "Plaza API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "Plaza microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}
