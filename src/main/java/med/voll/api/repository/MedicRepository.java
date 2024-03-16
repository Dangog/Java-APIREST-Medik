package med.voll.api.repository;

import med.voll.api.model.Medic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicRepository extends JpaRepository<Medic,Long> {
}
