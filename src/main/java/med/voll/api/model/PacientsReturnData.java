package med.voll.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PacientsReturnData(String nome, String email, String cpf) {

    public PacientsReturnData(Pacient pacient) {
        this(pacient.getNome(), pacient.getEmail(), pacient.getCpf());
    }
}
