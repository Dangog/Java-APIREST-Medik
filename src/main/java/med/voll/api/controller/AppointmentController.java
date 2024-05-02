package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.UnscheduleAppointmentDataDTO;
import med.voll.api.service.AppointmentSchedule;
import med.voll.api.model.ScheduledAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule appointmentSchedule;

    @PostMapping
    @Transactional
    public ResponseEntity scheduleAppointment(@RequestBody @Valid AppointmentDataDTO appointmentData){
        appointmentSchedule.schedule(appointmentData);
        return ResponseEntity.ok(new ScheduledAppointmentData(null,appointmentData.idMedic(), appointmentData.idPacient(), appointmentData.date()));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity unscheduleAppointment(@RequestBody @Valid UnscheduleAppointmentDataDTO unscheduleAppointmentDataDTO){
        appointmentSchedule.unschedule(unscheduleAppointmentDataDTO);
        return ResponseEntity.noContent().build();
    }

}
