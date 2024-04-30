package med.voll.api.validations;

import med.voll.api.infra.exceptions.NotNullValidationException;
import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.ScheduledAppointmentData;
import med.voll.api.model.UnscheduleAppointmentDataDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class AdvanceTimeValidation implements AppointmentSchedulePreValidations {

    private LocalDateTime localDateTime;

    public void validation(AppointmentDataDTO data) {

        if (localDateTime.now().plusMinutes(30).isAfter(data.date())) {
            throw new NotNullValidationException("Mininum advance time is 30 minutes");
        }
    }
}
