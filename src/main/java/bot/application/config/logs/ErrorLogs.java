package bot.application.config.logs;


import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ALEXANDRE
 */

@XmlRootElement(name = "logs")
@XmlType(propOrder = {"name", "logs"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorLogs {
    @XmlElement(name = "log")
    List<Log> logs = new ArrayList<Log>();
    String name;
}
