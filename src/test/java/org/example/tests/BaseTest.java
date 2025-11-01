package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected SoftAssertions soft;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        soft = new SoftAssertions();
        RestAssured.defaultParser = Parser.JSON;
    }
}
