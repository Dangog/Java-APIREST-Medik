package med.voll.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacients")
@Entity(name = "Pacient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    @Embedded
    private Endereco endereco;

    public Pacient(PacientDTO dados) {
        this.status = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void updateData(PacientEditDTO pacientData) {
        if (pacientData.nome() != null){
            this.nome = pacientData.nome();
        }
        if (pacientData.telefone() != null){
            this.telefone = pacientData.telefone();
        }
        if (pacientData.endereco() != null){
            this.endereco.editAdress(pacientData.endereco());
        }
    }

    public void deletePacient() {
        this.status = false;
    }
}
