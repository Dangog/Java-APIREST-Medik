package med.voll.api.validations;

import med.voll.api.model.Pacient;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SameDayAppointmentValidation {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void sameDayAppointmentValidation(Pacient pacient, LocalDateTime date) {

        var firstHour = date.withHour(7);
        var lastHour = date.withHour(18);

        boolean pacientDateValid = appointmentRepository.existsByPacientIdAndDataBetween(pacient.getId(),firstHour,lastHour);
        if (pacientDateValid) {
            throw new RuntimeException("Pacient already has a scheduled appointment");
        }
    }
}
