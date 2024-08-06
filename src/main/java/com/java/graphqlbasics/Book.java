package com.java.graphqlbasics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Book(Integer id , String name , Integer pageCount , Integer authorId) {
    public static List<Book> books = new ArrayList<>(List.of(
            new Book(1, "To Kill a Mockingbird", 281, 1),
            new Book(2, "1984", 328, 2),
            new Book(3, "The Great Gatsby", 180,3),
            new Book(4, "The Catcher in the Rye", 214,3),
            new Book(5, "Moby Dick", 635,4),
            new Book(6, "Him ty", 65,5)
    ));

    public static Book getBookById(Integer id) {
        return books.stream().filter( book -> book.id.equals(id))
                .findFirst().orElse(null);
    }
    public static List<Book> getBooksByAuthorId(Integer authorId) {
        return books.stream().filter(book -> book.authorId().equals(authorId))
                .toList();
    }

    public static Book createBook(Book book){
        books.add(book);
        return books.stream().toList().getLast();
    }
}
