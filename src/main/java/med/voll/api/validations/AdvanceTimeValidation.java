package med.voll.api.validations;

import med.voll.api.model.AppointmentDataDTO;
import org.springframework.stereotype.Service;

@Service
public class AdvanceTimeValidation {

    public void advanceTimeValidation(AppointmentDataDTO data){
        System.out.println("A ser validado");
    }

}
