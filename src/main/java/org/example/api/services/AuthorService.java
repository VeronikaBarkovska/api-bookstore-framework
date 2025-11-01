package org.example.api.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.api.clients.AuthorClient;
import org.example.api.core.services.BaseService;
import org.example.api.models.ApiResponse;
import org.example.api.models.AuthorModel;
import org.example.api.models.EmptyResponse;
import org.example.api.models.ErrorResponse;

public class AuthorService extends BaseService {
    private final AuthorClient authorClient;

    public AuthorService(AuthorClient authorClient) {
        this.authorClient = authorClient;
    }

    @Step("Create author")
    public ApiResponse<AuthorModel> createAuthor(AuthorModel author) {
        Response response = authorClient.createAuthor(author);
        return extractAndWrap(response, AuthorModel.class);
    }

    @Step("Create invalid author")
    public ApiResponse<ErrorResponse> createInvalidAuthor(AuthorModel author) {
        Response response = authorClient.createAuthor(author);
        return extractError(response);
    }

    @Step("Get author by ID")
    public ApiResponse<AuthorModel> getAuthorById(int id) {
        Response response = authorClient.getAuthorById(id);
        return extractAndWrap(response, AuthorModel.class);
    }

    @Step("Get author by invalid ID")
    public ApiResponse<ErrorResponse> getAuthorByInvalidId(int id) {
        Response response = authorClient.getAuthorById(id);
        return extractError(response);
    }

    @Step("Update author")
    public ApiResponse<AuthorModel> updateAuthor(int id, AuthorModel author) {
        Response response = authorClient.updateAuthor(id, author);
        return extractAndWrap(response, AuthorModel.class);
    }

    @Step("Update author with invalid data")
    public ApiResponse<ErrorResponse> updateAuthorInvalid(int id, AuthorModel author) {
        Response response = authorClient.updateAuthor(id, author);
        return extractError(response);
    }

    @Step("Delete author")
    public ApiResponse<EmptyResponse> deleteAuthor(int id) {
        Response response = authorClient.deleteAuthor(id);
        return extractAndWrap(response, EmptyResponse.class);
    }

    @Step("Delete non-existing author")
    public ApiResponse<ErrorResponse> deleteNonExistingAuthor(int id) {
        Response response = authorClient.deleteAuthor(id);
        return extractError(response);
    }

    @Step("Get all authors")
    public ApiResponse<AuthorModel[]> getAllAuthors() {
        Response response = authorClient.getAllAuthors();
        return extractAndWrap(response, AuthorModel[].class);
    }
}
