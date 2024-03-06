package whatsender.application.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    
    private String dt_contrato;
    private String hr_contrato;
    private String dt_renovacao_contrato;
    private String hr_renovacao_contrato;
    
    private String dt_expiracao_contrato;

    public PacoteContratado() {}

    public PacoteContratado(Integer id, Pacote pacote, Integer mensagensContratada, String tipoPacote) {
        this.id = id;
        this.pacote = pacote;
        this.mensagensContratada = mensagensContratada;
        this.mensagensDisponiveis = mensagensContratada;
        this.mensagensEnviadas = 0;
        this.tipoPacote = tipoPacote;

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        
        String data_formatada = dateFormat.format(currentDate);
        String hora_formatada = hourFormat.format(currentDate);
        
        this.dt_contrato = data_formatada;
        this.hr_contrato = hora_formatada;
        this.setDt_expiracao_contrato(currentDate);
        
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
    
    public void setMensagensAvulsaContratada(Integer mensagensContratada) {
        this.mensagensContratada = this.mensagensContratada + mensagensContratada;
    }
    
    public void setMensagensAvulsaDisponiveis(Integer mensagensDisponiveis) {
        this.mensagensDisponiveis = this.mensagensDisponiveis + mensagensDisponiveis;
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

    public String getDt_contrato() {
        return dt_contrato;
    }

    public void setDt_contrato(String dt_contrato) {
        this.dt_contrato = dt_contrato;
    }

    public String getHr_contrato() {
        return hr_contrato;
    }

    public void setHr_contrato(String hr_contrato) {
        this.hr_contrato = hr_contrato;
    }

    public String getDt_renovacao_contrato() {
        return dt_renovacao_contrato;
    }

    public void setDt_renovacao_contrato(String dt_renovacao_contrato) {
        this.dt_renovacao_contrato = dt_renovacao_contrato;
    }

    public String getHr_renovacao_contrato() {
        return hr_renovacao_contrato;
    }

    public void setHr_renovacao_contrato(String hr_renovacao_contrato) {
        this.hr_renovacao_contrato = hr_renovacao_contrato;
    }

    public String getDt_expiracao_contrato() {
        return dt_expiracao_contrato;
    }

    public void setDt_expiracao_contrato(Date dt_expiracao) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        cal.setTime(dt_expiracao); 
        cal.add(Calendar.DATE, 30);

        this.dt_expiracao_contrato = dateFormat.format(cal.getTime());
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
        if (!Objects.equals(this.tipoPacote, other.tipoPacote)) {
            return false;
        }
        if (!Objects.equals(this.dt_contrato, other.dt_contrato)) {
            return false;
        }
        if (!Objects.equals(this.hr_contrato, other.hr_contrato)) {
            return false;
        }
        if (!Objects.equals(this.dt_renovacao_contrato, other.dt_renovacao_contrato)) {
            return false;
        }
        if (!Objects.equals(this.hr_renovacao_contrato, other.hr_renovacao_contrato)) {
            return false;
        }
        if (!Objects.equals(this.dt_expiracao_contrato, other.dt_expiracao_contrato)) {
            return false;
        }
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
