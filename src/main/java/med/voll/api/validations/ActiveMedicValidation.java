package med.voll.api.validations;

import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.Medic;
import med.voll.api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveMedicValidation {
    public void activeMedicValidation(Medic medic){
            if (medic.getStatus() == false){
                throw new RuntimeException("Current medic isn't avaliable");
            }
    }
}
