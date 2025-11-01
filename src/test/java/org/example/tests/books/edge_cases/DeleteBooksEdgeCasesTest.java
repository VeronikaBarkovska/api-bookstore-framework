package org.example.tests.books.edge_cases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.example.api.models.ApiResponse;
import org.example.api.models.ErrorResponse;
import org.example.assertions.ApiAssertions;
import org.example.tests.books.BaseBooksTest;
import org.testng.annotations.Test;

import static org.example.api.constants.AllureConstants.BOOKS_API;
import static org.example.api.constants.AllureConstants.VERONIKA_BARKOVSKA;

@Epic(BOOKS_API)
@Feature("DELETE Edge Cases")
@Owner(VERONIKA_BARKOVSKA)
public class DeleteBooksEdgeCasesTest extends BaseBooksTest {

    @Test(description = "DELETE non-existing book should return 404")
    public void deleteNonExistingBookShouldReturn404() {
        int nonExistingId = 99999;

        ApiResponse<ErrorResponse> errorResponse = bookService.deleteNonExistingBook(nonExistingId);

        ApiAssertions.assertErrorResponse(errorResponse, 404, "Not Found");
    }

}
