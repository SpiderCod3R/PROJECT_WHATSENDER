import whatsender.application.bot.config.utilities.Browser;
import whatsender.application.bot.config.utilities.WhatsAppDriver;
import whatsender.application.bot.test.GNS_PROJECT_WHATSENDER;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author ALEXANDRE
 */
public class WhatsSenderSelenium {
    private WebDriver driver;
    private ChromeDriver navegador;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    
    
    public WhatsSenderSelenium() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
       
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeDriverService service = new ChromeDriverService.Builder().build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("lang=pt-br");
        options.setExperimentalOption("detach", true);
        WebDriver navegador = new ChromeDriver(service, options);
        navegador.get("https://web.whatsapp.com");
        
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(GNS_PROJECT_WHATSENDER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
         driver.quit();
    }
    
    @Test 
    public void send_message_from_whatsappweb() {
        ArrayList<String> lista_contatos = new ArrayList<>();
        lista_contatos.add("22996072173");
        lista_contatos.add("22992073683");
        
        String mensagem = "olá eu sou uma mensagem de teste enviada por um robo";
        
        WhatsAppDriver whatsapp = new WhatsAppDriver(Browser.CHROME);
        whatsapp.open();
        whatsapp.waitForConnection();
        
        for (String contato : lista_contatos) {
           whatsapp.abrir_conversa_com_contato(contato);
           whatsapp.sendMsg(mensagem);
        }
    }
}
