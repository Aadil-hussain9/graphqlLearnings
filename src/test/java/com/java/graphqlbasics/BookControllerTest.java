package com.java.graphqlbasics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.HashMap;
import java.util.Map;

@GraphQlTest(BookController.class)
class BookControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;
    @Test
    void canGetBooks() {
        graphQlTester.documentName("books")
                .execute().path("books")
                .entityList(Book.class)
                .hasSize(6);
    }

    @Test
    void shouldCreateBookTest(){
        Map<String, Object> bookInput = new HashMap<>();
        bookInput.put("name", "New Book");
        bookInput.put("pageCount", 123);
        bookInput.put("authorId", 1);

        graphQlTester.documentName("createBook")
                .operationName("CreateBook")
                .variable("bookInput",bookInput)
                .execute()
                .path("createBook.name").entity(String.class).isEqualTo("New Book");
    }
}