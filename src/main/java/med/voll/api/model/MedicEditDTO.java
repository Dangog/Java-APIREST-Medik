package med.voll.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MedicEditDTO(Long id, String nome, String email , String telefone) {
}
