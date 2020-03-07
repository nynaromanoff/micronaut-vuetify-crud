package user.repositories;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import user.domain.Book;
import user.domain.Editora;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book>  findAll();

    Optional<Book> findById(Integer id);

    void deleteById(Integer id);

    void updateBook(@Valid @Id Integer id, @NotBlank String nome, Integer pages, Editora editora);

}
