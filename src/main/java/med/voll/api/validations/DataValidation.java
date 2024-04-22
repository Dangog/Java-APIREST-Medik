package med.voll.api.validations;

import med.voll.api.model.AppointmentDataDTO;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class DataValidation {

    public void dataValidation(AppointmentDataDTO data){
        if (data.date().getHour() > 18 || data.date().getHour() < 7 || data.date().getDayOfWeek() == DayOfWeek.SUNDAY ){
            throw new RuntimeException("Invalid date, please choose a correct date to schedule");
        }
    }

}
