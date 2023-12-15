package whatsender.application.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Contact {
    
    @CsvBindByName
    @CsvBindByPosition(position = 0)
    private String name;
    
    @CsvBindByName
    @CsvBindByPosition(position = 1)
    private String whatsNumber;
    
    @CsvBindByName
    @CsvBindByPosition(position = 2)
    private String data;
    
    @CsvBindByName
    @CsvBindByPosition(position = 3)
    private String hour;
    
    public Contact() {}

    public Contact(String name, String whatsNumber, String data, String hour) {
        this.name = name;
        this.whatsNumber = whatsNumber;
        this.data = data;
        this.hour = hour;
    }

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

    @Override
    public String toString() {
        return "Contact{" + "name=" + name + ", whatsNumber=" + whatsNumber + ", data=" + data + ", hour=" + hour + '}';
    }
    
    
    
    
}
