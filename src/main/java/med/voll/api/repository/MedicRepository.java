package med.voll.api.repository;

import med.voll.api.model.Medic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MedicRepository extends JpaRepository<Medic,Long> {
    Page<Medic> findAllByStatusTrue(Pageable pageable);
}
