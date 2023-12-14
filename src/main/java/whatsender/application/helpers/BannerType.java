package whatsender.application.helpers;

/**
 *
 * @author ALEXANDRE
 */
public enum BannerType {
    SUCCESS("sucesso"),
    INFO("info"),
    ERROR("erro"),
    WARNING("aviso");
    
    private String value;

    BannerType(String value) {
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
}
