package mad.voll.api.endereco;
import jakarta.persistence.*;

@Table(name = "medicos")
@Entity
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
}
