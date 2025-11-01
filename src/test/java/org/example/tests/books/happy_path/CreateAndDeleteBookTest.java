package org.example.tests.books.happy_path;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.example.api.models.ApiResponse;
import org.example.api.models.BookModel;
import org.example.api.models.EmptyResponse;
import org.example.assertions.ApiAssertions;
import org.example.tests.books.BaseBooksTest;
import org.example.utils.RandomDataGenerator;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.BOOKS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;

@Epic(BOOKS_API)
@Feature("Create and Delete Book")
@Owner(VERONIKA_BARKOVSKA)
public class CreateAndDeleteBookTest extends BaseBooksTest {

    @Test(description = "POST + DELETE book - should create and delete book successfully")
    public void shouldCreateAndDeleteBookSuccessfully() {
        BookModel newBook = RandomDataGenerator.generateRandomBook();
        ApiResponse<BookModel> createResponse = bookService.createBook(newBook);

        ApiAssertions.assertStatus(createResponse, 200);
        ApiAssertions.assertBodyMatches(createResponse, newBook);

        int createdId = createResponse.getBody().getId();

        ApiResponse<EmptyResponse> deleteResponse = bookService.deleteBook(createdId);
        ApiAssertions.assertStatus(deleteResponse, 200);
    }
}
