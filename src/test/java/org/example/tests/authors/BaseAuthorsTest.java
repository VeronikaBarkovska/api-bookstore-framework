package org.example.tests.authors;

import org.example.api.clients.AuthorClient;
import org.example.api.core.config.ConfigurationManager;
import org.example.api.services.AuthorService;
import org.example.tests.BaseTest;
import org.testng.annotations.BeforeClass;

public class BaseAuthorsTest extends BaseTest {
    protected AuthorService authorService;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        AuthorClient authorClient = new AuthorClient(ConfigurationManager.getBaseUrl());
        authorService = new AuthorService(authorClient);
    }
}
