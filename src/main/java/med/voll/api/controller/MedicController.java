package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.Medic;
import med.voll.api.model.MedicDTO;
import med.voll.api.model.MedicReturnData;
import med.voll.api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MedicReturnData> list(){
        return repository.findAll().stream().map(MedicReturnData::new).toList();
    }
}
