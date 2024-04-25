package med.voll.api.validations;

import med.voll.api.model.Medic;
import med.voll.api.model.Pacient;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MedicWithAnotherAppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void medicWithAnotherAppointmentSchedule(Medic medic, LocalDateTime date) {
        boolean medicDateValid = appointmentRepository.existsByMedicIdAndData(medic.getId(), date);
        if (medicDateValid) {
            throw new RuntimeException("Medic already has a scheduled appointment");
        }
    }
}