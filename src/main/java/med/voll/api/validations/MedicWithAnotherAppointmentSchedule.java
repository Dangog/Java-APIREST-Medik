package med.voll.api.validations;

import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.Medic;
import med.voll.api.model.Pacient;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
public class MedicWithAnotherAppointmentSchedule implements AppointmentSchedulePreValidations {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validation(AppointmentDataDTO appointmentDataDTO) {

        boolean medicDateValid = appointmentRepository.existsByMedicIdAndData(appointmentDataDTO.idMedic(), appointmentDataDTO.date());
        if (medicDateValid) {
            throw new RuntimeException("Medic already has a scheduled appointment");
        }
    }
}