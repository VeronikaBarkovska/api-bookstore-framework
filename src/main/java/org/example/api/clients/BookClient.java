package org.example.api.clients;

import io.restassured.response.Response;
import org.example.api.constants.Endpoints;
import org.example.api.core.clients.BaseClient;
import org.example.api.models.BookModel;

public class BookClient extends BaseClient {

    public BookClient(String url) {
        super(url);
    }

    public Response getAllBooks() {
        return get(Endpoints.BOOKS);
    }

    public Response getBookById(int id) {
        return get(Endpoints.BOOKS, id);
    }

    public Response createBook(BookModel book) {
        return post(Endpoints.BOOKS, book);
    }

    public Response updateBook(int id, BookModel book) {
        return put(Endpoints.BOOKS, id, book);
    }

    public Response deleteBook(int id) {
        return delete(Endpoints.BOOKS, id);
    }
}
