package org.example.tests.authors.edge_cases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.example.api.models.ApiResponse;
import org.example.api.models.AuthorModel;
import org.example.api.models.ErrorResponse;
import org.example.assertions.ApiAssertions;
import org.example.tests.authors.BaseAuthorsTest;
import org.example.utils.RandomDataGenerator;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.AUTHORS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;

@Epic(AUTHORS_API)
@Feature("POST Edge Cases")
@Owner(VERONIKA_BARKOVSKA)
public class PostAuthorsEdgeCasesTest extends BaseAuthorsTest {

    @Test(description = "POST author with minimal data")
    public void postAuthorWithMinimalData() {
        AuthorModel minimalAuthor = AuthorModel.builder()
                .id(RandomDataGenerator.randomInt(10000, 11000))
                .firstName(null)
                .lastName(null)
                .build();

        ApiResponse<ErrorResponse> errorResponse = authorService.createInvalidAuthor(minimalAuthor);

        ApiAssertions.assertErrorResponse(errorResponse, 400, "Bad Request");
    }

    @Test(description = "POST author with maximum data")
    public void postAuthorWithMaximumData() {
        AuthorModel maxAuthor = AuthorModel.builder()
                .id(RandomDataGenerator.randomInt(11001, 12000))
                .firstName("F".repeat(500))
                .lastName("L".repeat(500))
                .build();

        ApiResponse<AuthorModel> createdMaxAuthorResponse = authorService.createAuthor(maxAuthor);

        ApiAssertions.assertStatus(createdMaxAuthorResponse, 200);
        ApiAssertions.assertBodyMatches(createdMaxAuthorResponse, maxAuthor);
    }

}
