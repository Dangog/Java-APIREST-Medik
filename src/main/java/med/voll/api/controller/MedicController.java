package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.Medic;
import med.voll.api.model.MedicDTO;
import med.voll.api.model.MedicEditDTO;
import med.voll.api.model.MedicReturnData;
import med.voll.api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medics")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping("/create")
    @Transactional
    public String createMedics(@RequestBody @Valid MedicDTO data){
        repository.save(new Medic(data));
        return "Médico cadastrado com sucesso";
    }

    @GetMapping
    public Page<MedicReturnData> list(@PageableDefault(size=10, sort ={"nome"}) Pageable pageable){
        return repository.findAllByStatusTrue(pageable).map(MedicReturnData::new);
    }

    @PutMapping("/edit")
    @Transactional
    public String editMedic(@RequestBody @Valid MedicEditDTO data){
       var medic = repository.getReferenceById(data.id());
       medic.changeMedicData(data);
       return "Dados do médico alterados com sucesso";
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String deleteMedic(@PathVariable Long id){
        var medic = repository.getReferenceById(id);
        medic.deleteMedic();
        return "Medico deletado com sucesso!";
    }
}
