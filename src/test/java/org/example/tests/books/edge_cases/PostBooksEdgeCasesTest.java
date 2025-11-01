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
@Feature("POST Edge Cases")
@Owner(VERONIKA_BARKOVSKA)
public class PostBooksEdgeCasesTest extends BaseBooksTest {

    @Test(description = "POST book with minimal data")
    public void postBookWithMinimalData() {
        BookModel minimalBook = BookModel.builder()
                .id(RandomDataGenerator.randomInt(10000, 11000))
                .title(null)
                .description(null)
                .pageCount(0)
                .excerpt(null)
                .publishDate(RandomDataGenerator.generatePublishDate())
                .build();

        ApiResponse<ErrorResponse> errorResponse = bookService.createInvalidBook(minimalBook);

        ApiAssertions.assertErrorResponse(errorResponse, 400, "Bad Request");
    }

    @Test(description = "POST book with maximum data")
    public void postBookWithMaximumData() {
        BookModel maxBook = BookModel.builder()
                .id(RandomDataGenerator.randomInt(11001, 12000))
                .title("T".repeat(500))
                .description("D".repeat(1000))
                .pageCount(10000)
                .excerpt("E".repeat(2000))
                .publishDate("2050-12-31T23:59:59Z")
                .build();

        ApiResponse<BookModel> createdResponse = bookService.createBook(maxBook);

        ApiAssertions.assertStatus(createdResponse, 200);
        ApiAssertions.assertBodyMatches(createdResponse, maxBook);
    }

    @Test(description = "POST book with extreme publishDate")
    public void postBookWithExtremePublishDate() {
        BookModel oldBook = BookModel.builder()
                .id(RandomDataGenerator.randomInt(12001, 13000))
                .title("Old Book")
                .description("Very old publish date")
                .pageCount(100)
                .excerpt("Excerpt")
                .publishDate("1900-01-01T00:00:00Z")
                .build();

        BookModel futureBook = BookModel.builder()
                .id(RandomDataGenerator.randomInt(13001, 14000))
                .title("Future Book")
                .description("Very future publish date")
                .pageCount(100)
                .excerpt("Excerpt")
                .publishDate("2100-12-31T23:59:59Z")
                .build();

        ApiResponse<BookModel> createdOldBookResponse = bookService.createBook(oldBook);

        ApiAssertions.assertStatus(createdOldBookResponse, 200);
        ApiAssertions.assertBodyMatches(createdOldBookResponse, oldBook);

        ApiResponse<BookModel> createdFutureBookResponse = bookService.createBook(futureBook);

        ApiAssertions.assertStatus(createdFutureBookResponse, 200);
        ApiAssertions.assertBodyMatches(createdFutureBookResponse, futureBook);
    }

    @Test(description = "POST book with invalid publishDate should return 400 validation error")
    public void updateBookWithInvalidPublishDate() {
        BookModel invalidBook = BookModel.builder()
                .id(RandomDataGenerator.randomInt(11001, 12000))
                .title("Invalid Date Book")
                .description("Book with wrong date format")
                .pageCount(120)
                .excerpt("Test")
                .publishDate("not-a-date")
                .build();

        ApiResponse<ErrorResponse> errorResponse = bookService.createInvalidBook(invalidBook);

        ApiAssertions.assertErrorResponse(errorResponse, 400, "One or more validation errors occurred.");

        soft.assertThat(errorResponse.getBody().getErrors())
                .as("Error details should mention publishDate")
                .containsKey("$.publishDate");

        soft.assertThat(errorResponse.getBody().getErrors().get("$.publishDate").get(0))
                .contains("could not be converted to System.DateTime");

        soft.assertAll();
    }
}
