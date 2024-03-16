package med.voll.api.model;

public record MedicReturnData(String nome, String email, String crm, Especialidade especialidade) {

    public MedicReturnData(Medic medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
