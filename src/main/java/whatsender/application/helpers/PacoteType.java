package whatsender.application.helpers;

/**
 *
 * @author ALEXANDRE ( THE GRAND MASTER )
 */
public enum PacoteType {
    PADRAO("padrao"),
    MEDIO("intermediario-1"),
    SUPER("intermediario-2"),
    ULTRA("ilimitado");
    
    private String value;

    PacoteType(String value) {
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
}
