//package com.java.graphqlbasics;
//
//import graphql.kickstart.tools.GraphQLMutationResolver;
//import graphql.kickstart.tools.GraphQLQueryResolver;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class BookResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
//
//    @QueryMapping("allAuthors")
//    public List<Author> getAllAuthors() {
//        return Author.authors;
//    }
//
//    public Author getAuthorById(Integer id) {
//        return Author.authorById(id).orElseThrow(() -> new RuntimeException("Author not found"));
//    }
//}
//
