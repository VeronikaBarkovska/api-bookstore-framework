package org.example.tests.books.edge_cases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.example.api.models.ApiResponse;
import org.example.api.models.BookModel;
import org.example.api.models.ErrorResponse;
import org.example.assertions.ApiAssertions;
import org.example.tests.books.BaseBooksTest;
import org.example.utils.RandomDataGenerator;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.BOOKS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;

@Epic(BOOKS_API)
@Feature("PUT Edge Cases")
@Owner(VERONIKA_BARKOVSKA)
public class PutBooksEdgeCasesTest extends BaseBooksTest {

    @Test(description = "PUT book with non-existing ID should return 404")
    public void updateNonExistingBookShouldReturn404() {
        int nonExistingId = 99999;
        BookModel updatedBookModel = BookModel.builder()
                .id(nonExistingId)
                .title(RandomDataGenerator.generateTitle())
                .description(RandomDataGenerator.generateDescription())
                .pageCount(RandomDataGenerator.randomInt(50, 1000))
                .excerpt(RandomDataGenerator.generateExcerpt())
                .publishDate(RandomDataGenerator.generatePublishDate())
                .build();

        ApiResponse<ErrorResponse> errorResponse = bookService.updateBookInvalid(nonExistingId, updatedBookModel);

        ApiAssertions.assertErrorResponse(errorResponse, 404, "Not Found");
    }

    @Test(description = "PUT book update with minimal data")
    public void updateBookWithMinimalData() {
        int bookId = 1;
        BookModel minimalBook = BookModel.builder()
                .id(bookId)
                .title(null)
                .description(null)
                .pageCount(0)
                .excerpt(null)
                .publishDate(RandomDataGenerator.generatePublishDate() )
                .build();

        ApiResponse<ErrorResponse> errorResponse = bookService.updateBookInvalid(bookId, minimalBook);

        ApiAssertions.assertErrorResponse(errorResponse, 400, "Bad Request");
    }

    @Test(description = "PUT book update with maximum data")
    public void updateBookWithMaximumData() {
        int bookId = 1;
        BookModel maxBook = BookModel.builder()
                .id(bookId)
                .title("T".repeat(500))
                .description("D".repeat(1000))
                .pageCount(10000)
                .excerpt("E".repeat(2000))
                .publishDate("2050-12-31T23:59:59Z")
                .build();

        ApiResponse<BookModel> maxBookUpdated = bookService.updateBook(bookId, maxBook);

        ApiAssertions.assertStatus(maxBookUpdated, 200);
        ApiAssertions.assertBodyMatches(maxBookUpdated, maxBook);
    }

    @Test(description = "PUT book with invalid publishDate should return 400 validation error")
    public void updateBookWithInvalidPublishDate() {
        int bookId = 1;

        BookModel invalidBook = BookModel.builder()
                .id(bookId)
                .title("Invalid Date Book")
                .description("Book with wrong date format")
                .pageCount(120)
                .excerpt("Test")
                .publishDate("not-a-date")
                .build();

        ApiResponse<ErrorResponse> errorResponse = bookService.updateBookInvalid(bookId, invalidBook);

        ApiAssertions.assertErrorResponse(errorResponse, 400, "One or more validation errors occurred.");

        soft.assertThat(errorResponse.getBody().getErrors())
                .as("Error details should mention publishDate")
                .containsKey("$.publishDate");

        soft.assertThat(errorResponse.getBody().getErrors().get("$.publishDate").get(0))
                .contains("could not be converted to System.DateTime");

        soft.assertAll();
    }
}
