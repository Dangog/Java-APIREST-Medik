package med.voll.api.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDataDTO(

    Long idMedic,

    @NotNull
    Long idPacient,

    @NotNull
    @Future
    LocalDateTime date) {
}
