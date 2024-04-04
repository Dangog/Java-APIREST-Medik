package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.ScheduledAppointmentData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @PostMapping
    @Transactional
    public ResponseEntity scheduleAppointment(@RequestBody @Valid AppointmentDataDTO appointmentData){
        return ResponseEntity.ok(new ScheduledAppointmentData(null,appointmentData.idMedic(), appointmentData.idPacient(), appointmentData.date()));
    }

}
