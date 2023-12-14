package whatsender.application.entities;

import java.util.Objects;

/**
 *
 * @author ALEXANDRE
 */
public class Appointment {
    private Contact contact;
    
    private String data;
    private String hora;
    private String doctor;

    public Appointment(Contact contact, String data, String hora, String doctor) {
        this.contact = contact;
        this.data = data;
        this.hora = hora;
        this.doctor = doctor;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.contact);
        hash = 61 * hash + Objects.hashCode(this.data);
        hash = 61 * hash + Objects.hashCode(this.hora);
        hash = 61 * hash + Objects.hashCode(this.doctor);
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
        final Appointment other = (Appointment) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.doctor, other.doctor)) {
            return false;
        }
        return Objects.equals(this.contact, other.contact);
    }

    
    
    
}
