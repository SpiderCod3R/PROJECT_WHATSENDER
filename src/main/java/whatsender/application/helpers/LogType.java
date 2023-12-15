/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package whatsender.application.helpers;

/**
 *
 * @author ALEXANDRE
 */
public enum LogType {
    SUCCESS("sucesso"),
    INFO("info"),
    ERROR("erro"),
    WARNING("aviso");
    
    private String value;

    LogType(String value) {
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
}
