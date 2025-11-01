package org.example.tests.authors.edge_cases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.assertj.core.api.Assertions;
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
@Feature("PUT Edge Cases")
@Owner(VERONIKA_BARKOVSKA)
public class PutAuthorsEdgeCasesTest extends BaseAuthorsTest {

    @Test(description = "PUT author with non-existing ID should return 404")
    public void updateNonExistingAuthorShouldReturn404() {
        int nonExistingId = 99999;
        AuthorModel updatedAuthor = AuthorModel.builder()
                .id(nonExistingId)
                .firstName(RandomDataGenerator.generateFirstName())
                .lastName(RandomDataGenerator.generateLastName())
                .build();

        ApiResponse<ErrorResponse> errorResponse = authorService.updateAuthorInvalid(nonExistingId, updatedAuthor);

        ApiAssertions.assertErrorResponse(errorResponse, 404, "Not Found");
    }

    @Test(description = "PUT author update with minimal data")
    public void updateAuthorWithMinimalData() {
        int authorId = 1;
        AuthorModel minimalAuthor = AuthorModel.builder()
                .id(authorId)
                .firstName(null)
                .lastName(null)
                .build();

        ApiResponse<ErrorResponse> errorResponse = authorService.updateAuthorInvalid(authorId, minimalAuthor);

        ApiAssertions.assertErrorResponse(errorResponse, 400, "Bad Request");
    }

    @Test(description = "PUT author update with maximum data")
    public void updateAuthorWithMaximumData() {
        int authorId = 1;
        AuthorModel maxAuthor = AuthorModel.builder()
                .id(authorId)
                .firstName("F".repeat(500))
                .lastName("L".repeat(500))
                .build();

        ApiResponse<AuthorModel> maxAuthorUpdated = authorService.updateAuthor(authorId, maxAuthor);

        ApiAssertions.assertStatus(maxAuthorUpdated, 200);
        ApiAssertions.assertBodyMatches(maxAuthorUpdated, maxAuthor);
    }

}
