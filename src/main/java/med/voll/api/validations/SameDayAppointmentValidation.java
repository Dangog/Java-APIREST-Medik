package med.voll.api.validations;

import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.Pacient;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
public class SameDayAppointmentValidation implements AppointmentSchedulePreValidations {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validation(AppointmentDataDTO appointmentDataDTO) {

        var firstHour = appointmentDataDTO.date().withHour(7);
        var lastHour = appointmentDataDTO.date().withHour(18);

        boolean pacientDateValid = appointmentRepository.existsByPacientIdAndDataBetween(appointmentDataDTO.idPacient(),firstHour,lastHour);
        if (pacientDateValid) {
            throw new RuntimeException("Pacient already has a scheduled appointment");
        }
    }
}
