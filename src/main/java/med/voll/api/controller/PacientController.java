package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.Pacient;
import med.voll.api.model.PacientDTO;
import med.voll.api.model.PacientsReturnData;
import med.voll.api.repository.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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

    @GetMapping
    public Page<PacientsReturnData> getPacients(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable){
        return repository.findAll(pageable).map(PacientsReturnData::new);
    }
}
