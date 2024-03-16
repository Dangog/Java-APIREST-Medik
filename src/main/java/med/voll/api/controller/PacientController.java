package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.Pacient;
import med.voll.api.model.PacientDTO;
import med.voll.api.repository.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacients")
public class PacientController {

    @Autowired
    private PacientRepository repository;

    @PostMapping("/create")
    public String createPacient(@RequestBody @Valid PacientDTO data){
        repository.save(new Pacient(data));
        return "Paciente salvo com sucesso";
    }
}
