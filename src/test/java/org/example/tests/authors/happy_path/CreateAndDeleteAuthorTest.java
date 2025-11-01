package org.example.tests.authors.happy_path;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.example.api.models.ApiResponse;
import org.example.api.models.AuthorModel;
import org.example.api.models.EmptyResponse;
import org.example.tests.authors.BaseAuthorsTest;
import org.example.utils.RandomDataGenerator;
import org.example.assertions.ApiAssertions;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.AUTHORS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;

@Epic(AUTHORS_API)
@Feature("Create and Delete Author")
@Owner(VERONIKA_BARKOVSKA)
public class CreateAndDeleteAuthorTest extends BaseAuthorsTest {

    @Test(description = "POST + DELETE author - should create and delete author successfully")
    public void shouldCreateAndDeleteAuthorSuccessfully() {
        AuthorModel newAuthor = RandomDataGenerator.generateRandomAuthor();
        ApiResponse<AuthorModel> createResponse = authorService.createAuthor(newAuthor);

        ApiAssertions.assertStatus(createResponse, 200);
        ApiAssertions.assertBodyMatches(createResponse, newAuthor);

        int createdId = createResponse.getBody().getId();

        ApiResponse<EmptyResponse> deleteResponse = authorService.deleteAuthor(createdId);
        ApiAssertions.assertStatus(deleteResponse, 200);
    }
}
