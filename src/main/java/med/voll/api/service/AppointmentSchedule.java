package med.voll.api.service;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import med.voll.api.infra.exceptions.NotNullValidationException;
import med.voll.api.model.*;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.MedicRepository;
import med.voll.api.repository.PacientRepository;
import med.voll.api.validations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PacientRepository pacientRepository;

    @Autowired
    private List<AppointmentSchedulePreValidations> validationsList;

    public void schedule(@Valid AppointmentDataDTO data){

        if (!pacientRepository.existsById(data.idPacient())){
            throw new NotNullValidationException("Invalid or not found Pacient ID");
        }

        if (data.idMedic() != null && !medicRepository.existsById(data.idMedic())){
            throw new NotNullValidationException("Invalid or not found Medic ID");
        }

        validationsList.forEach(v -> v.validation(data));

        Pacient pacient = pacientRepository.findById(data.idPacient()).get();
        Medic medic = selectRandomAvaliableMedic(data);

        Appointment appointment = new Appointment(null, medic,pacient,true,data.date(),null);
        appointmentRepository.save(appointment);
    }

    public Medic selectRandomAvaliableMedic(AppointmentDataDTO data){
        if (data.idMedic() != null){
          Medic medic =  medicRepository.getReferenceById(data.idMedic());;
          return medic;
        }
        if (data.speciality() == null){
            throw new NotNullValidationException("Speciality attribute required!");
        }
        return medicRepository.selectRandomMedicByAvaliabilityAndSpeciality(data.speciality(), data.date());
    }

    public void unschedule(UnscheduleAppointmentDataDTO data){

        if (!appointmentRepository.existsById(data.id())){
            throw new RuntimeException("Appointment ID not found!");
        }

        Appointment appointment = appointmentRepository.getReferenceById(data.id());

        LocalDateTime requisitionDate = LocalDateTime.now();
        LocalDateTime appointmentData = appointment.getData();

        Duration d = Duration.between(requisitionDate, appointmentData);

        if (d.toHours() < 24) {
            throw new RuntimeException("Minimum 24 hours notice to cancel!");
        }

        appointment.setStatus(false);
        appointment.setJustification(data.justification());
    }


}
