package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.*;
import med.voll.api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("medics")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity createMedics(@RequestBody @Valid MedicDTO data, UriComponentsBuilder uriBuilder){;
        var medic = new Medic(data);
        repository.save(medic);

        var uri = uriBuilder.path("/medics/create/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicAllAttributesMedic(medic));
    }

    @GetMapping
    public ResponseEntity<Page<MedicReturnData>> list(@PageableDefault(size=10, sort ={"nome"}) Pageable pageable){
        var page = repository.findAllByStatusTrue(pageable).map(MedicReturnData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity medicDetails(@PathVariable Long id){
        var medic = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicAllAttributesMedic(medic));
    }

    @PutMapping("/edit")
    @Transactional
    public ResponseEntity editMedic(@RequestBody @Valid MedicEditDTO data){
       var medic = repository.getReferenceById(data.id());
       medic.changeMedicData(data);
       return ResponseEntity.ok(new MedicAllAttributesMedic(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedic(@PathVariable Long id){
        var medic = repository.getReferenceById(id);
        medic.deleteMedic();
        return ResponseEntity.noContent().build();
    }
}
