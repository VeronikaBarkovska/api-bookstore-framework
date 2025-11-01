package org.example.api.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.api.clients.BookClient;
import org.example.api.core.services.BaseService;
import org.example.api.models.ApiResponse;
import org.example.api.models.BookModel;
import org.example.api.models.EmptyResponse;
import org.example.api.models.ErrorResponse;

public class BookService extends BaseService {
    private final BookClient bookClient;

    public BookService(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @Step("Create book")
    public ApiResponse<BookModel> createBook(BookModel book) {
        Response response = bookClient.createBook(book);
        return extractAndWrap(response, BookModel.class);
    }

    @Step("Create invalid book")
    public ApiResponse<ErrorResponse> createInvalidBook(BookModel book) {
        Response response = bookClient.createBook(book);
        return extractError(response);
    }

    @Step("Get book by ID")
    public ApiResponse<BookModel> getBookById(int id) {
        Response response = bookClient.getBookById(id);
        return extractAndWrap(response, BookModel.class);
    }

    @Step("Get book by invalid ID")
    public ApiResponse<ErrorResponse> getBookByInvalidId(int id) {
        Response response = bookClient.getBookById(id);
        return extractError(response);
    }

    @Step("Update book")
    public ApiResponse<BookModel> updateBook(int id, BookModel book) {
        Response response = bookClient.updateBook(id, book);
        return extractAndWrap(response, BookModel.class);
    }

    @Step("Update book with invalid data")
    public ApiResponse<ErrorResponse> updateBookInvalid(int id, BookModel book) {
        Response response = bookClient.updateBook(id, book);
        return extractError(response);
    }

    @Step("Delete book")
    public ApiResponse<EmptyResponse> deleteBook(int id) {
        Response response = bookClient.deleteBook(id);
        return extractAndWrap(response, EmptyResponse.class);
    }

    @Step("Delete non-existing book")
    public ApiResponse<ErrorResponse> deleteNonExistingBook(int id) {
        Response response = bookClient.deleteBook(id);
        return extractError(response);
    }

    @Step("Get all books")
    public ApiResponse<BookModel[]> getAllBooks() {
        Response response = bookClient.getAllBooks();
        return extractAndWrap(response, BookModel[].class);
    }
}
