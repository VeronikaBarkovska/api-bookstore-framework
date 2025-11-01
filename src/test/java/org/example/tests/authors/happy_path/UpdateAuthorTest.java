package org.example.tests.authors.happy_path;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.example.api.models.ApiResponse;
import org.example.api.models.AuthorModel;
import org.example.tests.authors.BaseAuthorsTest;
import org.example.utils.RandomDataGenerator;
import org.example.assertions.ApiAssertions;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.AUTHORS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;

@Epic(AUTHORS_API)
@Feature("Update Author")
@Owner(VERONIKA_BARKOVSKA)
public class UpdateAuthorTest extends BaseAuthorsTest {
    @Test(description = "PUT author - should update author successfully")
    public void shouldUpdateAuthor() {
        int authorId = 1;

        AuthorModel updatedModel = AuthorModel.builder()
                .id(authorId)
                .idBook(RandomDataGenerator.randomInt(1, 200))
                .firstName(RandomDataGenerator.generateFirstName())
                .lastName(RandomDataGenerator.generateLastName())
                .build();

        ApiResponse<AuthorModel> response = authorService.updateAuthor(authorId, updatedModel);

        ApiAssertions.assertStatus(response, 200);
        ApiAssertions.assertBodyNotNull(response);
        ApiAssertions.assertBodyMatches(response, updatedModel);
    }
}
