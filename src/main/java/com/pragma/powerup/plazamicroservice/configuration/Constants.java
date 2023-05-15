package com.pragma.powerup.plazamicroservice.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String RESPONSE_ERROR_KEY = "error";
    public static final String RESTAURANT_CREATED_MESSAGE = "Restaurant created successfully";
    public static final String OWNER_IS_NOT_OVER_18_ERROR = "Owner is not over 18";
    public static final String RESTAURANT_ALREADY_EXIST_ERROR = "Restaurant already exist";
    public static final String RESTAURANT_NAME_IS_INVALID_ERROR = "Restaurant name is invalid";
    public static final String RESTAURANT_NOT_FOUND_ERROR = "Restaurant not found";
    public static final String SWAGGER_TITLE_MESSAGE = "Plaza API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "Plaza microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}
