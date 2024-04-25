package med.voll.api.validations;

import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.ScheduledAppointmentData;
import med.voll.api.model.UnscheduleAppointmentDataDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Service
public class AdvanceTimeValidation {

    private LocalDateTime localDateTime;

    public void advanceTimeValidation(AppointmentDataDTO data){

        if (localDateTime.now().plusMinutes(30).isAfter(data.date())){
            throw new RuntimeException("Mininum advance time is 30 minutes");
        }
    }

}
