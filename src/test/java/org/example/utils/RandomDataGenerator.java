package org.example.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import org.example.api.models.AuthorModel;
import org.example.api.models.BookModel;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class RandomDataGenerator {

    private final Faker faker = new Faker();

    public int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public String generatePublishDate() {
        int year = randomInt(2000, LocalDateTime.now().getYear());
        int month = randomInt(1, 12);
        int day = randomInt(1, 28);
        int hour = randomInt(0, 23);
        int minute = randomInt(0, 59);
        int second = randomInt(0, 59);
        OffsetDateTime date = OffsetDateTime.of(year, month, day, hour, minute, second, 0, ZoneOffset.UTC);
        return date.toString();
    }

    public String generateTitle() {
        return faker.book().title();
    }

    public String generateDescription() {
        return faker.lorem().sentence(10);
    }

    public String generateExcerpt() {
        return faker.lorem().paragraph();
    }

    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateLastName() {
        return faker.name().lastName();
    }

    public static BookModel generateRandomBook() {
        return BookModel.builder()
                .id(randomInt(200, 1000))
                .title(generateTitle())
                .description(generateDescription())
                .pageCount(randomInt(50, 1000))
                .excerpt(generateExcerpt())
                .publishDate(generatePublishDate())
                .build();
    }

    public static AuthorModel generateRandomAuthor() {
        return AuthorModel.builder()
                .id(randomInt(200, 1000))
                .idBook(randomInt(1, 200))
                .firstName(generateFirstName())
                .lastName(generateLastName())
                .build();
    }
}
