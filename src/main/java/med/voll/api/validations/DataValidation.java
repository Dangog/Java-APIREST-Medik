package med.voll.api.validations;

import med.voll.api.infra.exceptions.NotNullValidationException;
import med.voll.api.model.AppointmentDataDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Component
public class DataValidation implements AppointmentSchedulePreValidations {

    public void validation(AppointmentDataDTO data){
        if (data.date().getHour() > 18 || data.date().getHour() < 7 || data.date().getDayOfWeek() == DayOfWeek.SUNDAY ){
            throw new NotNullValidationException("Invalid date, please choose a correct date to schedule");
        }
    }

}
