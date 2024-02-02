package whatsender.application.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ALEXANDRE
 */
@Entity
@Table(name = PacoteContratado.TABLE_NAME)
public class PacoteContratado implements Serializable{
    public static final String TABLE_NAME= "tb_pacote_contratado";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;
    
    private Integer mensagensContratada;
    private Integer mensagensDisponiveis;
    private Integer mensagensEnviadas;
    private String tipoPacote;
    

    public PacoteContratado() {}

    public PacoteContratado(Integer id, Pacote pacote, Integer mensagensContratada, String tipoPacote) {
        this.id = id;
        this.pacote = pacote;
        this.mensagensContratada = mensagensContratada;
        this.tipoPacote = tipoPacote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public Integer getMensagensContratada() {
        return mensagensContratada;
    }

    public void setMensagensContratada(Integer mensagensContratada) {
        this.mensagensContratada = mensagensContratada;
    }

    public Integer getMensagensDisponiveis() {
        return mensagensDisponiveis;
    }

    public void setMensagensDisponiveis(Integer mensagensDisponiveis) {
        this.mensagensDisponiveis = mensagensDisponiveis;
    }

    public Integer getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public void setMensagensEnviadas(Integer mensagensEnviadas) {
        this.mensagensEnviadas = mensagensEnviadas;
    }

    public String getTipoPacote() {
        return tipoPacote;
    }

    public void setTipoPacote(String tipoPacote) {
        this.tipoPacote = tipoPacote;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.pacote);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PacoteContratado other = (PacoteContratado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.pacote, other.pacote)) {
            return false;
        }
        if (!Objects.equals(this.mensagensContratada, other.mensagensContratada)) {
            return false;
        }
        if (!Objects.equals(this.mensagensDisponiveis, other.mensagensDisponiveis)) {
            return false;
        }
        return Objects.equals(this.mensagensEnviadas, other.mensagensEnviadas);
    }

    @Override
    public String toString() {
        return "PacoteContratado{" + "id=" + id + ", pacote=" + pacote + ", mensagensContratada=" + mensagensContratada + ", mensagensDisponiveis=" + mensagensDisponiveis + ", mensagensEnviadas=" + mensagensEnviadas + '}';
    }

    
}
