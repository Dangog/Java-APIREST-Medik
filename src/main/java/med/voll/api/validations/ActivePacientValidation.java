package med.voll.api.validations;

import med.voll.api.model.Medic;
import med.voll.api.model.Pacient;
import org.springframework.stereotype.Service;

@Service
public class ActivePacientValidation {
    public void activePacientValidation(Pacient pacient){
        if (pacient.getStatus() == false){
            throw new RuntimeException("Current pacient isn't avaliable");
        }
    }

}
