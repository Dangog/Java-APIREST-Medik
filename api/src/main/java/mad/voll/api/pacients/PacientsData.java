package mad.voll.api.pacients;

import mad.voll.api.endereco.DadosEndereco;

public record PacientsData(String nome, String email, String telefone, String cpf, DadosEndereco endereco) {
}
