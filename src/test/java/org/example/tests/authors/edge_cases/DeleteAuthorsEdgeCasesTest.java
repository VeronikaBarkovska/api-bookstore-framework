package org.example.tests.authors.edge_cases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.example.api.models.ApiResponse;
import org.example.api.models.ErrorResponse;
import org.example.assertions.ApiAssertions;
import org.example.tests.authors.BaseAuthorsTest;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.*;

@Epic(AUTHORS_API)
@Feature("DELETE Edge Cases")
@Owner(VERONIKA_BARKOVSKA)
public class DeleteAuthorsEdgeCasesTest extends BaseAuthorsTest {

    @Test(description = "DELETE non-existing author should return 404")
    public void deleteNonExistingAuthorShouldReturn404() {
        int nonExistingId = 99999;
        ApiResponse<ErrorResponse> errorResponse = authorService.deleteNonExistingAuthor(nonExistingId);

        ApiAssertions.assertErrorResponse(errorResponse, 404, "Not Found");
    }
}
