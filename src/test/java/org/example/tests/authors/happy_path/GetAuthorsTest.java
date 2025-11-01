package org.example.tests.authors.happy_path;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.assertj.core.api.Assertions;
import org.example.api.models.ApiResponse;
import org.example.api.models.AuthorModel;
import org.example.tests.authors.BaseAuthorsTest;
import org.example.assertions.ApiAssertions;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.AUTHORS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;


@Epic(AUTHORS_API)
@Feature("GET Authors")
@Owner(VERONIKA_BARKOVSKA)
public class GetAuthorsTest extends BaseAuthorsTest {

    @Test(description = "GET all authors - should return non-empty list")
    public void shouldReturnAllAuthors() {
        ApiResponse<AuthorModel[]> response = authorService.getAllAuthors();

        ApiAssertions.assertStatus(response, 200);
        ApiAssertions.assertBodyNotNull(response);

        AuthorModel[] authors = response.getBody();
        Assertions.assertThat(authors.length)
                .as("GET all authors: response should return non-empty list")
                .isGreaterThan(0);
    }

    @Test(description = "GET author by ID - should return valid author")
    public void shouldReturnAuthorsById() {
        int authorId = 1;

        ApiResponse<AuthorModel> response = authorService.getAuthorById(authorId);

        ApiAssertions.assertStatus(response, 200);
        ApiAssertions.assertBodyNotNull(response);

        AuthorModel author = response.getBody();

        soft.assertThat(author.getFirstName())
                .as("First name should not be empty")
                .isNotEmpty();
        soft.assertThat(author.getLastName())
                .as("Last name should not be empty")
                .isNotEmpty();
        soft.assertThat(author.getId())
                .as("Author ID should match requested ID %s", authorId)
                .isEqualTo(authorId);
        soft.assertAll();
    }

}
