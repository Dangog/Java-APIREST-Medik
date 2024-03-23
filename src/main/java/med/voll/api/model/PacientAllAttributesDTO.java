package med.voll.api.model;

public record PacientAllAttributesDTO(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {
    public PacientAllAttributesDTO(Pacient pacient){
        this(pacient.getId(), pacient.getNome(), pacient.getEmail(), pacient.getCpf(), pacient.getTelefone(), pacient.getEndereco());
    }
}

