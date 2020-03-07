package user.repositories;


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import user.domain.Editora;

import java.util.List;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer> {

    List<Editora> findAll();
}
