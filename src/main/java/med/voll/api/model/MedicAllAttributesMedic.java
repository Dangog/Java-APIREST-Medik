package med.voll.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicAllAttributesMedic(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
    public MedicAllAttributesMedic(Medic medic) {
        this(medic.getId(), medic.getNome(), medic.getEmail(), medic.getTelefone(), medic.getCrm(), medic.getEspecialidade(), medic.getEndereco());
    }

}
