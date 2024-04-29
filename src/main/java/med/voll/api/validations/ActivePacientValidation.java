package med.voll.api.validations;

import med.voll.api.infra.exceptions.NotNullValidationException;
import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.Medic;
import med.voll.api.model.Pacient;
import med.voll.api.repository.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ActivePacientValidation implements AppointmentSchedulePreValidations {

    @Autowired
    private PacientRepository pacientRepository;

    public void validation(AppointmentDataDTO dataDTO){

        if (dataDTO.idPacient() == null){
            return;
        }

        var pacient = pacientRepository.getReferenceById(dataDTO.idPacient());

        if (pacient.getStatus() == false){
            throw new NotNullValidationException("Current pacient isn't avaliable");
        }

    }

}
