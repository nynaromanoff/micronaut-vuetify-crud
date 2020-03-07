package user.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "editora")
public class Editora implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Nome é Obrigatório!")
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Editora() {

    }

    public Editora(Integer id, String nome, List<Book> books) {
        super();
        this.id = id;
        this.nome = nome;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Editora{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Editora)) return false;
        Editora editora = (Editora) o;
        return getId().equals(editora.getId()) &&
                getNome().equals(editora.getNome()) &&
                getBooks().equals(editora.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getBooks());
    }
}
