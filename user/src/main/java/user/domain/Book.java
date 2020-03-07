package user.domain;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Nome é Obrigatório!")
    private String nome;

    private Integer pages;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;


    public Book(Integer id,String nome, Integer pages, Editora editora) {
        super();
        this.id = id;
        this.nome = nome;
        this.pages = pages;
        this.editora = editora;
    }

    public Book() {
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", pages=" + pages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId().equals(book.getId()) &&
                getNome().equals(book.getNome()) &&
                getPages().equals(book.getPages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getPages());
    }
}