/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package whatsender.bot.test_bot;
import whatsender.bot.driver.Browser;
import whatsender.bot.driver.WhatsAppDriver;

/**
 *
 * @author ALEXANDRE
 */



public class GNS_PROJECT_WHATSENDER{
    
    //https://www.contradodigital.com/2021/02/25/how-to-setup-selenium-using-java-and-apache-netbeans-for-automated-web-browser-testing/
    public static void main(String[] args) {
        System.out.println("GNS_PROJECT_WHATSENDER --- started");
     
        String mensagem = "Olá boa tarde eu sou uma mensagem enviada por um Bot --- \nEnviada pelo WhatSender ";

        WhatsAppDriver whatsapp = new WhatsAppDriver(Browser.CHROME);
        whatsapp.open();
        whatsapp.waitForConnection();
        
        whatsapp.abrir_conversa_com_contato("5522996072173");
//        whatsapp.getCurrentConvImg();
//        whatsapp.getCurrentConvName();
//        whatsapp.getVisableMessages();
        whatsapp.sendMsg(mensagem);
        
        System.out.println("GNS_PROJECT_WHATSENDER --- finished");
        
    }
}
