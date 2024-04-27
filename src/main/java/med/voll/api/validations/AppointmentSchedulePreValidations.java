package med.voll.api.validations;

import med.voll.api.model.AppointmentDataDTO;

public interface AppointmentSchedulePreValidations {

    void validation(AppointmentDataDTO appointmentDataDTO);
}
