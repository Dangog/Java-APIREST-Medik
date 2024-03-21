package med.voll.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medics")
@Entity(name = "Medic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private Boolean status;

    @Embedded
    private Endereco endereco;


    public Medic(MedicDTO data) {
        this.status = true;
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.crm = data.crm();
        this.especialidade = data.especialidade();
        this.endereco = new Endereco(data.endereco());
    }

    public void changeMedicData(MedicEditDTO medicData) {
        if (medicData.nome() != null){
            this.nome = medicData.nome();
        }
        if (medicData.telefone() != null){
            this.telefone = medicData.telefone();
        }
        if (medicData.endereco() != null){
            this.endereco.editAdress(medicData.endereco());
        }
    }

    public void deleteMedic() {
        this.status = false;
    }
}
