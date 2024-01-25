package whatsender.application.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ALEXANDRE
 */
@Entity
@Table(name = Consulta.TABLE_NAME)
public class Consulta implements Serializable {
    public static final String TABLE_NAME= "tb_consultas";
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String contactName;
    private String contactPhone;
    
    private String data;
    private String hora;
    private String doctor;

    public Consulta(String contactName, String contactPhone, String data, String hora, String doctor) {
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.data = data;
        this.hora = hora;
        this.doctor = doctor;
    }

    public Consulta() {
    }

    public Integer getId() {
        return id;
    }
    
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.contactName);
        hash = 13 * hash + Objects.hashCode(this.contactPhone);
        hash = 13 * hash + Objects.hashCode(this.data);
        hash = 13 * hash + Objects.hashCode(this.hora);
        hash = 13 * hash + Objects.hashCode(this.doctor);
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
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.contactName, other.contactName)) {
            return false;
        }
        if (!Objects.equals(this.contactPhone, other.contactPhone)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.doctor, other.doctor)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    
}
