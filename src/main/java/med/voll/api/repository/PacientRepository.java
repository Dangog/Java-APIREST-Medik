package med.voll.api.repository;

import med.voll.api.model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository <Pacient,Long> {
}
