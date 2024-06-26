package med.voll.api.controller;

import med.voll.api.model.AppointmentDataDTO;
import med.voll.api.model.Especialidade;
import med.voll.api.model.ScheduledAppointmentData;
import med.voll.api.service.AppointmentSchedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AppointmentDataDTO> appointmentDataDTOJacksonJson;

    @Autowired
    private JacksonTester<ScheduledAppointmentData> scheduledAppointmentDataJacksonJson;

    @MockBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Should return 400 status code")
    @WithMockUser
    void scheduleAppointment_Cenary1() throws Exception {
       var response = mockMvc.perform(post("/appointment"))
               .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Should return ScheduledAppointmentData OK and 200 status code")
    @WithMockUser
    void scheduleAppointment_Cenary2() throws Exception {

        var date = LocalDateTime.now().plusHours(1);
        var speciality = Especialidade.CARDIOLOGIA;

        var scheduleData = new ScheduledAppointmentData( null, 2L, 5L, date);

        when(appointmentSchedule.schedule(any())).thenReturn(scheduleData);

        var response = mockMvc.perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentDataDTOJacksonJson.write(
                                new AppointmentDataDTO(2L, 5L, date, speciality))
                                .getJson())
                )
                .andReturn().getResponse();

        var wantedJson = scheduledAppointmentDataJacksonJson.write(scheduleData)
                .getJson();


        assertThat(response.getContentAsString()).isEqualTo(wantedJson);
    }


}
