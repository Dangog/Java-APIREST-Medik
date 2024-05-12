package med.voll.api.controller;

import lombok.SneakyThrows;
import med.voll.api.model.*;
import med.voll.api.repository.MedicRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Medic> medicJacksonJson;

    @MockBean
    private Endereco endereco;

    @MockBean
    private EnderecoDTO enderecoDTO;

    @MockBean
    private MedicRepository medicRepository;

    @Autowired
    private JacksonTester<MedicAllAttributesMedic> medicAllAttributesMedicJson;

    @Test
    @DisplayName("Should return 200 staus code, as well as the Jorge Medic attributes")
    @WithMockUser
    void createMedic_Cenary1() throws Exception {

        var enderecoDTO = new EnderecoDTO("rua 1","parque oratorio","12345688","Sao Paulo","SP","Nao ha","108");

        var endereco = new Endereco(enderecoDTO);
        var medicAllAtributes = new MedicAllAttributesMedic(null,"Jorge Ben Junior","jorgebenjor@gmail.com","(11) 94001-0198","131029", Especialidade.CARDIOLOGIA, endereco);
        var medic = new Medic(null,"Jorge Ben Junior","jorgebenjor@gmail.com","(11) 94001-0198","131029", Especialidade.CARDIOLOGIA,true, endereco);

        when(medicRepository.save(any())).thenReturn(medic);

        var response = mockMvc.perform(post("/medics/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medicJacksonJson.write(medic)
                .getJson())
                )
                .andReturn().getResponse();

        var wantedResponse = medicAllAttributesMedicJson.write(medicAllAtributes).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(wantedResponse);
    }

    @Test
    @DisplayName("Should return 400 staus code")
    @WithMockUser
    void createMedic_Cenary2() throws Exception {

        var response = mockMvc.perform(post("/medics/create")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
}