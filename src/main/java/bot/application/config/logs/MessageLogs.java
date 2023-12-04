package bot.application.config.logs;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "logs")
@XmlType(propOrder = {"name", "logs"})
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageLogs {
    @XmlElement(name = "log")
    List<Log> logs = new ArrayList<Log>();
    String name;  
}
