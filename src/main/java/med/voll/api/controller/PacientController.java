package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.*;
import med.voll.api.repository.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacients")
public class PacientController {

    @Autowired
    private PacientRepository repository;

    @PostMapping("/create")
    public ResponseEntity createPacient(@RequestBody @Valid PacientDTO data, UriComponentsBuilder uriComponentsBuilder){
        var pacient = new Pacient(data);

        repository.save(pacient);

        var uri = uriComponentsBuilder.path("/pacients/create/{id}").buildAndExpand(pacient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacientAllAttributesDTO(pacient));
    }

    @GetMapping
    public ResponseEntity<Page<PacientsReturnData>> getPacients(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable){
        var page = repository.findAllByStatusTrue(pageable).map(PacientsReturnData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity pacientDetails(@PathVariable Long id){
        var pacient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacientAllAttributesDTO(pacient));
    }

    @PutMapping("/edit")
    @Transactional
    public ResponseEntity editPacient(@RequestBody @Valid PacientEditDTO pacientData){
        var pacient = repository.getReferenceById(pacientData.id());
        pacient.updateData(pacientData);
        return ResponseEntity.ok(new PacientAllAttributesDTO(pacient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePacient(@PathVariable Long id){
        var pacient = repository.getReferenceById(id);
        pacient.deletePacient();
        return ResponseEntity.ok(new PacientAllAttributesDTO(pacient));
    }

}
