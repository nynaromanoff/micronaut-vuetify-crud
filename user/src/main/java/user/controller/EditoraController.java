package user.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import user.domain.Editora;
import user.repositories.EditoraRepository;

import javax.inject.Inject;
import java.util.List;

@Controller("/editora")
public class EditoraController {

    @Inject
    private EditoraRepository editoraRepository;

    @Get
    @Consumes(value = MediaType.APPLICATION_JSON)
    public List<Editora> findAll() {
        return editoraRepository.findAll();
    }

    @Post("new")
    @Status(HttpStatus.CREATED)
    @Produces(value = MediaType.TEXT_JSON)
    public Editora save (@Body Editora editora){
       return editoraRepository.save(editora);
    }
}
