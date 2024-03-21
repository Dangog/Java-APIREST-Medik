package med.voll.api.model;

import jakarta.validation.constraints.NotNull;

public record PacientEditDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco) {
}
