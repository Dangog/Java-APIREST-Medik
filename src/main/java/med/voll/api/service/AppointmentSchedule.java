package med.voll.api.service;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import med.voll.api.infra.exceptions.NotNullValidationException;
import med.voll.api.model.*;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.MedicRepository;
import med.voll.api.repository.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PacientRepository pacientRepository;

    public void schedule(@Valid AppointmentDataDTO data){

        if (!pacientRepository.existsById(data.idPacient())){
            throw new NotNullValidationException("Invalid or not found Pacient ID");
        }

        if (data.idMedic() != null && !medicRepository.existsById(data.idMedic())){
            throw new NotNullValidationException("Invalid or not found Medic ID");
        }

        Pacient pacient = pacientRepository.findById(data.idPacient()).get();
        Medic medic = selectRandomAvaliableMedic(data);

        Appointment appointment = new Appointment(null, medic,pacient,data.date());
        appointmentRepository.save(appointment);
    }

    public Medic selectRandomAvaliableMedic(AppointmentDataDTO data){

        if (data.idMedic() != null){
          return medicRepository.getReferenceById(data.idMedic());
        }

        if (data.speciality() == null){
            throw new NotNullValidationException("Speciality attribute required!");
        }

        System.out.println(data.speciality());
        System.out.println(data.date());

        return medicRepository.selectRandomMedicByAvaliabilityAndSpeciality(data.speciality());
    }


}
