package med.voll.api.model;

import java.time.LocalDateTime;

public record ScheduledAppointmentData(Long id, Long idMedic, Long idPacient, LocalDateTime dateTime) {

    public ScheduledAppointmentData(Appointment appointment) {
        this(appointment.getId(), appointment.getMedic().getId(), appointment.getPacient().getId(),appointment.getData());
    }
}
