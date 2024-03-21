package med.voll.api.repository;

import med.voll.api.model.Pacient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacientRepository extends JpaRepository <Pacient,Long> {
    Page<Pacient> findAllByStatusTrue(Pageable pageable);
}
