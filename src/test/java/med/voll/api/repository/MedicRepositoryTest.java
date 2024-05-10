package med.voll.api.repository;
import med.voll.api.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import med.voll.api.repository.MedicRepositoryTest.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicRepositoryTest {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Medic is not avalliable, should medic return must be null")
    void selectRandomMedicByAvaliabilityAndSpecialityCenary1() {

    var nextMondayAt10Am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                    .atTime(10, 0);

    var medic = createMedic("Danilo Medico ","cajedanilo@gmail.com","121059",Especialidade.CARDIOLOGIA);
    var pacient = createPacient("Diego Paciente","diegocaje@gmail.com","33333333333");
    scheduleAppointment(medic,pacient,nextMondayAt10Am);

    var avaliableMedic = medicRepository.selectRandomMedicByAvaliabilityAndSpeciality(Especialidade.CARDIOLOGIA, nextMondayAt10Am);

    assertThat(avaliableMedic).isNull();
    }

    @Test
    @DisplayName("Medic is not avalliable, should medic return must be null")
    void selectRandomMedicByAvaliabilityAndSpecialityCenary2() {

        var nextMondayAt10Am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medic = createMedic("Danilo Medico ","cajedanilo@gmail.com","121059",Especialidade.CARDIOLOGIA);

        var avaliableMedic = medicRepository.selectRandomMedicByAvaliabilityAndSpeciality(Especialidade.CARDIOLOGIA,nextMondayAt10Am);

        assertThat(avaliableMedic).isEqualTo(medic);
    }

    private void scheduleAppointment(Medic medic, Pacient pacient, LocalDateTime data) {
        testEntityManager.persist(new Appointment(null, medic,pacient,data));
    }

    private Medic createMedic(String name, String email, String crm, Especialidade especialidade) {
        var medic = new Medic(medicDTO(name, email, crm, especialidade));
        testEntityManager.persist(medic);
        return medic;
    }

    private Pacient createPacient(String name, String email, String cpf) {
        var pacient = new Pacient(pacientDTO(name, email, cpf));
        testEntityManager.persist(pacient);
        return pacient;
    }

    private MedicDTO medicDTO(String name, String email, String crm, Especialidade especialidade) {
        return new MedicDTO(
                name,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private PacientDTO pacientDTO(String name, String email, String cpf) {
        return new PacientDTO(
                name,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}