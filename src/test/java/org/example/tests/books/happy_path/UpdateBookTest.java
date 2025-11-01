package org.example.tests.books.happy_path;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.example.api.models.ApiResponse;
import org.example.api.models.BookModel;
import org.example.assertions.ApiAssertions;
import org.example.tests.books.BaseBooksTest;
import org.example.utils.RandomDataGenerator;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.BOOKS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;

@Epic(BOOKS_API)
@Feature("Update Book")
@Owner(VERONIKA_BARKOVSKA)
public class UpdateBookTest extends BaseBooksTest {

    @Test(description = "PUT book - should update book successfully")
    public void shouldUpdateBook() {
        int bookId = 1;
        BookModel updatedBookModel = BookModel.builder()
                .id(bookId)
                .title(RandomDataGenerator.generateTitle())
                .description(RandomDataGenerator.generateDescription())
                .pageCount(RandomDataGenerator.randomInt(50, 1000))
                .excerpt(RandomDataGenerator.generateExcerpt())
                .publishDate(RandomDataGenerator.generatePublishDate())
                .build();

        ApiResponse<BookModel> response = bookService.updateBook(bookId, updatedBookModel);

        ApiAssertions.assertStatus(response, 200);
        ApiAssertions.assertBodyNotNull(response);
        ApiAssertions.assertBodyMatches(response, updatedBookModel);
    }

}
