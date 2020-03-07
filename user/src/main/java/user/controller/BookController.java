package user.controller;

import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import user.domain.Book;
import user.domain.Editora;
import user.repositories.BookRepository;


import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Controller("/book")
public class BookController {

    @Inject
    private BookRepository bookRepository;

    @Get
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Get("/{id}")
    public Optional<Book> findById (Integer id) {
       return bookRepository.findById(id);
    }

    @Post("/new")
    @Status(HttpStatus.CREATED)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Book save(@Body Book book) {
        return bookRepository.save(book);
    }


    @Delete("/{id}")
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Put("/update/{id}")
    @Produces(value = MediaType.TEXT_JSON)
    public void update(@Body Book book, Integer id, String nome, Integer pages, Editora editora){
        bookRepository.updateBook(id ,nome , pages, editora);
    }
}




