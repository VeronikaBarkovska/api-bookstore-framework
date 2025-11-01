package org.example.api.clients;

import io.restassured.response.Response;
import org.example.api.constants.Endpoints;
import org.example.api.core.clients.BaseClient;
import org.example.api.models.AuthorModel;

public class AuthorClient extends BaseClient {
    public AuthorClient(String url) {
        super(url);
    }

    public Response getAllAuthors() {
        return get(Endpoints.AUTHORS);
    }

    public Response getAuthorById(int id) {
        return get(Endpoints.AUTHORS, id);
    }

    public Response createAuthor(AuthorModel author) {
        return post(Endpoints.AUTHORS, author);
    }

    public Response updateAuthor(int id, AuthorModel author) {
        return put(Endpoints.AUTHORS, id, author);
    }

    public Response deleteAuthor(int id) {
        return delete(Endpoints.AUTHORS, id);
    }
}
