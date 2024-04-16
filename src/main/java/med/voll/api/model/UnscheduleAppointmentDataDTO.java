package med.voll.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record UnscheduleAppointmentDataDTO(@NotNull
                                           @JsonAlias("idAppointment")
                                           Long id,

                                           @NotNull
                                           Justification justification) {
}
