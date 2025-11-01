package org.example.tests.authors.edge_cases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.example.api.models.ApiResponse;
import org.example.api.models.ErrorResponse;
import org.example.assertions.ApiAssertions;
import org.example.tests.authors.BaseAuthorsTest;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.AUTHORS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;

@Epic(AUTHORS_API)
@Feature("GET Edge Cases")
@Owner(VERONIKA_BARKOVSKA)
public class GetAuthorsEdgeCasesTest extends BaseAuthorsTest {

    @Test(description = "GET author by non-existing ID should return 404")
    public void getNonExistingAuthorShouldReturn404() {
        int nonExistingId = 99999;

        ApiResponse<ErrorResponse> errorResponse = authorService.getAuthorByInvalidId(nonExistingId);

        ApiAssertions.assertErrorResponse(errorResponse, 404, "Not Found");
    }
}
