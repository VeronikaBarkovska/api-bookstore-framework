package org.example.api.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.example.api.models.ApiResponse;
import org.example.api.models.EmptyResponse;
import org.example.api.models.ErrorResponse;

public abstract class BaseService {

    private final ObjectMapper mapper = new ObjectMapper();

    protected <T> ApiResponse<T> extractAndWrap(Response response, Class<T> responseType) {
        String bodyString = response.getBody() != null ? response.getBody().asString() : "";

        if (bodyString.isBlank()) {
            if (responseType.equals(EmptyResponse.class)) {
                return new ApiResponse<>(response.getStatusCode(), responseType.cast(new EmptyResponse()));
            }
            return new ApiResponse<>(response.getStatusCode(), null);
        }

        try {
            T body = mapper.readValue(bodyString, responseType);
            return new ApiResponse<>(response.getStatusCode(), body);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response to " + responseType.getSimpleName(), e);
        }
    }

    protected ApiResponse<ErrorResponse> extractError(Response response) {
        String bodyString = response.getBody() != null ? response.getBody().asString() : "";

        if (bodyString.isBlank()) {
            ErrorResponse emptyError = new ErrorResponse("No Content", response.getStatusCode(), null);
            return new ApiResponse<>(response.getStatusCode(), emptyError);
        }

        try {
            ErrorResponse error = mapper.readValue(bodyString, ErrorResponse.class);
            return new ApiResponse<>(response.getStatusCode(), error);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ErrorResponse", e);
        }
    }

}
