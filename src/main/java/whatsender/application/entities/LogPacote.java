package whatsender.application.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import whatsender.application.helpers.MessageType;
import whatsender.application.helpers.PacoteType;
import whatsender.application.logs.LogType;

/**
 *
 * @author ALEXANDRE ( THE GRAND MASTER )
 */
@Entity
@Table(name = LogPacote.TABLE_NAME)
public class LogPacote implements Serializable {
    public static final String TABLE_NAME= "tb_log_de_pacotes";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_log")
    private String data;
    
    @Column(name = "hora_log")
    private String hour;
    
    @Column(name = "log_type")
    private LogType logType;
    
    @Column(name = "pacote_type")
    private PacoteType pacoteType;
    
    @Column(name = "data_ativacao_contrato")
    private String data_ativacao_contrato;
    
    @Column(name = "data_expiracao_contrato")
    private String data_expiracao_contrato;
    
    @Column(name = "hora_ativacao_contrato")
    private String hora_ativacao_contrato;

    public LogPacote(LogType logType, Pacote pacote, PacoteContratado pacoteContratado) {
        this.id = null;
        this.logType = logType;
        
        switch (pacote.getPacoteNome()) {
            case "Padrão":
                this.pacoteType = PacoteType.PADRAO;
                break;
            case "Intermediário 1":
                this.pacoteType = PacoteType.MEDIO;
                break;
            case "Intermediário 2":
                this.pacoteType = PacoteType.SUPER;
                break;
            case "Ilimitado":
                this.pacoteType = PacoteType.ULTRA;
                break;
        }
        
        this.data_ativacao_contrato = pacoteContratado.getDt_contrato();
        this.data_expiracao_contrato = pacoteContratado.getDt_expiracao_contrato();
        
        pega_data_sistema_para_por_no_log();
    }

    public LogPacote() {}
    
    public void pega_data_sistema_para_por_no_log(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        
        this.data = dateFormat.format(currentDate);;
        this.hour = hourFormat.format(currentDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.data);
        hash = 53 * hash + Objects.hashCode(this.logType);
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
        final LogPacote other = (LogPacote) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "LogPacote{" + "id=" + id + ", logType=" + logType + ", pacoteType=" + pacoteType + '}';
    }

    
 
}
