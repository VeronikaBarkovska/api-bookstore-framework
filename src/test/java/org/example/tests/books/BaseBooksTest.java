package org.example.tests.books;

import org.example.api.clients.BookClient;
import org.example.api.core.config.ConfigurationManager;
import org.example.api.services.BookService;
import org.example.tests.BaseTest;
import org.testng.annotations.BeforeClass;

public class BaseBooksTest extends BaseTest {
    protected BookService bookService;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        BookClient bookClient = new BookClient(ConfigurationManager.getBaseUrl());
        bookService = new BookService(bookClient);
    }
}
