package org.example.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.example.api.models.ApiResponse;
import org.example.api.models.ErrorResponse;

public class ApiAssertions {

    public static <T> void assertStatus(ApiResponse<T> response, int expectedStatus) {
        Assertions.assertThat(response.getStatusCode())
                .as("Check response status")
                .isEqualTo(expectedStatus);
    }

    public static void assertErrorResponse(ApiResponse<ErrorResponse> error, int expectedStatus, String expectedTitle) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(error.getStatusCode())
                    .as("Status code should be %d", expectedStatus)
                    .isEqualTo(expectedStatus);

            softly.assertThat(error.getBody().getTitle())
                    .as("Error title should be '%s'", expectedTitle)
                    .isEqualTo(expectedTitle);
        });
    }

    public static <T> void assertBodyNotNull(ApiResponse<T> response) {
        Assertions.assertThat(response.getBody())
                .as("Response body should not be null")
                .isNotNull();
    }

    public static <T> void assertBodyMatches(ApiResponse<T> response, T expected) {
        Assertions.assertThat(response.getBody())
                .as("Check response body matches expected")
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
