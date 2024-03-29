package med.voll.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicEditDTO(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        EnderecoDTO endereco) {
}
