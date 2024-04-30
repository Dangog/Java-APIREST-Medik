package med.voll.api.validations;

import med.voll.api.infra.exceptions.NotNullValidationException;
import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.Medic;
import med.voll.api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class ActiveMedicValidation implements AppointmentSchedulePreValidations {

    @Autowired
    MedicRepository medicRepository;

    public void validation(AppointmentDataDTO dataDTO) {

        if (dataDTO.idMedic() == null) {
            return;
        }

        var medic = medicRepository.getReferenceById(dataDTO.idMedic());

        if (medic.getStatus() == false) {
            throw new NotNullValidationException("Current medic isn't avaliable");
        }
    }
}
