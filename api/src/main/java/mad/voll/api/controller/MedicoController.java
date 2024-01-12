package mad.voll.api.controller;

import mad.voll.api.medic.MedicData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medic")
public class MedicoController {

    @PostMapping
    public String saveDoctor(@RequestBody MedicData data){
        return("O médico foi cadastrado com sucesso: " + data);
    }

}
