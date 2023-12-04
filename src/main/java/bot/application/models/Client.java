package bot.application.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Client {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhatsNumber() {
        return whatsNumber;
    }

    public void setWhatsNumber(String whatsNumber) {
        this.whatsNumber = whatsNumber;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_hour() {
        return appointment_hour;
    }

    public void setAppointment_hour(String appointment_hour) {
        this.appointment_hour = appointment_hour;
    }

    @CsvBindByName
    @CsvBindByPosition(position = 0)
    private String name;
    
    @CsvBindByName
    @CsvBindByPosition(position = 1)
    private String whatsNumber;
    
    @CsvBindByName
    @CsvBindByPosition(position = 2)
    private String appointment_date;
    
    @CsvBindByName
    @CsvBindByPosition(position = 3)
    private String appointment_hour;
    
    public Client() {}

    public Client(String name, String whatsNumber, String appointment_date, String appointment_hour) {
        this.name = name;
        this.whatsNumber = whatsNumber;
        this.appointment_date = appointment_date;
        this.appointment_hour = appointment_hour;
    }
    
    public Boolean isNull(){
        return (("".equals(getName())) || ("".equals(getWhatsNumber())));
    }

    @Override
    public String toString() {
        return "Client{" + "name=" + getName() + ", whatsNumber=" + getWhatsNumber() + ", service_date=" + getAppointment_date() + ", service_hour=" + getAppointment_hour() + '}';
    }
    
    
}
