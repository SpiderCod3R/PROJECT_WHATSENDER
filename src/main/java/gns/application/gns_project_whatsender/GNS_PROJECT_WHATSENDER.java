/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gns.application.gns_project_whatsender;
import gns.application.config.utilities.Browser;
import gns.application.config.utilities.WhatsAppDriver;

/**
 *
 * @author ALEXANDRE
 */



public class GNS_PROJECT_WHATSENDER{
    
    //https://www.contradodigital.com/2021/02/25/how-to-setup-selenium-using-java-and-apache-netbeans-for-automated-web-browser-testing/
    public static void main(String[] args) {
        System.out.println("GNS_PROJECT_WHATSENDER --- started");

//        ArrayList<String> lista_contatos = new ArrayList<>();
//        lista_contatos.add("22996072173");
//        lista_contatos.add("22992073683");
//        
        
        
//        for (String contato : lista_contatos) {
//           message_sender.localizar_contato(contato);
//           //message_sender.enviar_mensagem_do_whatsappweb(mensagem);
//        }
//        
        String mensagem = "olá eu sou uma nova mensagem";

        WhatsAppDriver whatsapp = new WhatsAppDriver(Browser.CHROME);
        whatsapp.open();
        whatsapp.waitForConnection();
        
        whatsapp.abrir_conversa_com_contato("22996072173");
//        whatsapp.getCurrentConvImg();
//        whatsapp.getCurrentConvName();
//        whatsapp.getVisableMessages();
        whatsapp.sendMsg(mensagem);
        
        System.out.println("GNS_PROJECT_WHATSENDER --- finished");
        
    }
}
