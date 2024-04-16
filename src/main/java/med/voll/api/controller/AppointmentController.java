package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.service.AppointmentSchedule;
import med.voll.api.model.ScheduledAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule appointmentSchedule;

    @PostMapping
    @Transactional
    public ResponseEntity scheduleAppointment(@RequestBody @Valid AppointmentDataDTO appointmentData){
        System.out.println(appointmentData.idMedic());
        appointmentSchedule.schedule(appointmentData);
        return ResponseEntity.ok(new ScheduledAppointmentData(null,appointmentData.idMedic(), appointmentData.idPacient(), appointmentData.date()));
    }

    @DeleteMapping
    @Transactional
    public String unscheduleAppointment(@RequestBody @Valid AppointmentDataDTO appointmentDataDTO){

        return "teste";
    }



}
