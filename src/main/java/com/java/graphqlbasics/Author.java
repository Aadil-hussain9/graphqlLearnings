package com.java.graphqlbasics;

import java.util.ArrayList;
import java.util.List;

public record Author(Integer id , String name) {
    public static List<Author> authors = new ArrayList<>(List.of(
            new Author(1, "Harper Lee"),
            new Author(2, "George Orwell"),
            new Author(3, "F. Scott Fitzgerald"),
            new Author(4, "J.D. Salinger"),
            new Author(5, "Herman Melville")
    ));

    public static Author authorById(Integer id){
        return authors.stream().filter(author -> author.id.equals(id))
                .findFirst().orElse(null);
    }

    public static Author createAuthor(Author author){
        authors.add(author);
        return authors.stream().toList().getLast();
    }
}
