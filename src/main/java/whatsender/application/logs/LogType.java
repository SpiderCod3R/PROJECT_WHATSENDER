package whatsender.application.logs;

/**
 *
 * @author ALEXANDRE ( THE GRAND MASTER )
 */
public enum LogType {
    SUCCESS("sucesso"),
    INFO("info"),
    ERROR("erro"),
    WARNING("aviso"),
    PACKAGE_HIRED("pacote contratado"),
    PACKAGE_CANCELLED("cliente cancelado"),
    PACKAGE_RENEWED("pacote renovado");
    
    private String value;

    LogType(String value) {
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
}
