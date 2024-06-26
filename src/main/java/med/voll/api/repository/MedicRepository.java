package med.voll.api.repository;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    Page<Medic> findAllByStatusTrue(Pageable pageable);

    @Query("""
            select m from Medic m
            where
            m.status = true
            and
            m.especialidade = :especialidade
            and
            m.id not in
                    (select a.medic.id from Appointment a
                    where
                    a.data = :data)
            order by rand()
            limit 1
            """)
    Medic selectRandomMedicByAvaliabilityAndSpeciality(Especialidade especialidade, LocalDateTime data);
}
