package com.java.graphqlbasics;

import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Controller
public class BookController {

    @QueryMapping("books")
    public List<Book> books(){
        return Book.books;
    }

    @QueryMapping("bookById")
    public Book bookById(@Argument Integer id){
        return Book.getBookById(id);
    }

    @QueryMapping("authorById")
    public Author author(@Argument Integer id) {
        return Author.authorById(id);
    }

    @QueryMapping
    public List<Author> allAuthors() {
        return Author.authors;
    }

    @SchemaMapping(typeName = "Author", field = "books")
    public List<Book> getBooksByAuthor(Author author) {
        return Book.getBooksByAuthorId(author.id());
    }

    @SchemaMapping(typeName = "Book", field = "author")
    public Author getAuthorByBook(Book book) {
        return Author.authorById(book.authorId());
    }

    @MutationMapping("createBook")
    public Book createBook(@Argument BookInput bookInput){
        int id = Book.books.size() + 1;
        System.out.println("sout : "+bookInput.name);
        Book book = new Book(id , bookInput.name , bookInput.pageCount,bookInput.authorId);
        sendMessage(bookInput.name);
        return Book.createBook(book);
    }

    @MutationMapping
    public Author createAuthor(@Argument String authorName){
        int id = Author.authors.size() + 1;
        System.out.println("id :"+id + "sss "+authorName);
        Author author = new Author(id , authorName);
        return Author.createAuthor(author);
    }

    private final Sinks.Many<String> messageSink = Sinks.many().multicast().onBackpressureBuffer();
    @SubscriptionMapping("newMessage")
    public Flux<String> newMessage() {
        return Flux.interval(Duration.ofSeconds(1)).
                map(tick -> String.valueOf(new Random().nextLong()));
    }

    public void sendMessage(String message) {
        messageSink.tryEmitNext(message);
    }

    static class BookInput {
        String name;
        Integer pageCount;
        Integer authorId;

        public String getName() {
            return name;
        }

        public Integer getPageCount() {
            return pageCount;
        }

        public Integer getAuthorId() {
            return authorId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
        }

        public void setAuthorId(Integer authorId) {
            this.authorId = authorId;
        }

        public BookInput(String name, Integer pageCount, Integer authorId) {
            this.name = name;
            this.pageCount = pageCount;
            this.authorId = authorId;
        }
    }
}

