package org.example.tests.books.happy_path;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.assertj.core.api.Assertions;
import org.example.api.models.ApiResponse;
import org.example.api.models.BookModel;
import org.example.assertions.ApiAssertions;
import org.example.tests.books.BaseBooksTest;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.BOOKS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;


@Epic(BOOKS_API)
@Feature("GET Books")
@Owner(VERONIKA_BARKOVSKA)
public class GetBooksTest extends BaseBooksTest {

    @Test(description = "GET all books - should return non-empty list")
    public void shouldReturnAllBooks() {
        ApiResponse<BookModel[]> response = bookService.getAllBooks();

        ApiAssertions.assertStatus(response, 200);
        ApiAssertions.assertBodyNotNull(response);

        BookModel[] books = response.getBody();
        Assertions.assertThat(books.length)
                .as("GET all books: response should return non-empty list")
                .isGreaterThan(0);
    }

    @Test(description = "GET book by ID - should return valid book")
    public void shouldReturnBookById() {
        int bookId = 1;

        ApiResponse<BookModel> response = bookService.getBookById(bookId);

        ApiAssertions.assertStatus(response, 200);
        ApiAssertions.assertBodyNotNull(response);

        BookModel book = response.getBody();

        soft.assertThat(book.getTitle())
                .as("Book title should not be empty")
                .isNotEmpty();
        soft.assertThat(book.getId())
                .as("Book ID should match requested ID %s", bookId)
                .isEqualTo(bookId);
        soft.assertAll();
    }
}
