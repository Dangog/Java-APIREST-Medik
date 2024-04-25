package med.voll.api.repository;

import med.voll.api.model.Appointment;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

       Boolean existsByMedicIdAndData(Long pacientId, LocalDateTime data);

       Boolean existsByPacientIdAndDataBetween(Long pacientId, LocalDateTime firstHour, LocalDateTime lastHour);

}
