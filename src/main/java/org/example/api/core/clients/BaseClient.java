package org.example.api.core.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClient {
    protected final String baseUrl;

    public BaseClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected RequestSpecification request() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();
    }

    protected Response get(String endpoint) {
        return request().get(endpoint);
    }

    protected Response get(String endpoint, Object id) {
        return request().get(endpoint + "/" + id);
    }

    protected Response post(String endpoint, Object body) {
        return request().body(body).post(endpoint);
    }

    protected Response put(String endpoint, Object id, Object body) {
        return request().body(body).put(endpoint + "/" + id);
    }

    protected Response delete(String endpoint, Object id) {
        return request().delete(endpoint + "/" + id);
    }
}
