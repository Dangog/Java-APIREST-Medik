package mad.voll.api.medic;

import mad.voll.api.endereco.DadosEndereco;

public record MedicData(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
