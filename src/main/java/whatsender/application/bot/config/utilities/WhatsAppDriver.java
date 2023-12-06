/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsender.application.bot.config.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author ALEXANDRE
 */
public class WhatsAppDriver {
    ChromeDriver chrome_driver;
    ChromeOptions options = new ChromeOptions();
    
    WebDriver driver;
    Wait<WebDriver> longWait;
    Wait<WebDriver> shortWait;
    boolean connected;
    
    /**
     * 
     * @param browser
     *            takes a {@link Browser} Enumeration to determine which browser
     *            to use (e.g. {@link Browser.CHROME}, {@link Browser.FIREFOX},
     *            {@link Browser.PHANTOMJS}, etc.)
     */
    public WhatsAppDriver(Browser browser) {
        if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("lang=pt-br");
            options.setExperimentalOption("detach", true);
            driver = new ChromeDriver(service, options);
        } else if (browser == Browser.FIREFOX) {
                driver = new FirefoxDriver();
        } 
        else {
            // Default Browser
            // driver = new ChromeDriver();
            WebDriverManager.chromedriver().setup();
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("lang=pt-br");
            options.setExperimentalOption("detach", true);
            driver = new ChromeDriver(service, options);
        }

        longWait = new WebDriverWait(driver, 30);
        shortWait = new WebDriverWait(driver, 3);

        connected = false;
    }

    /**
     * Will open the WhatApp Web client.
     */
    public void open() {
            driver.get("https://web.whatsapp.com/");

    }

    public void close() {
            driver.close();

    }

    public void quit() {
            driver.quit();

    }

    public void abrir_conversa_com_contato(String contactName) { 
        WebElement searchBar = driver.findElement(By.xpath("//p[contains(@class, \"selectable-text copyable-text\")]"));
        searchBar.clear();
        searchBar.sendKeys(contactName);

        try {
                TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }

        searchBar.click();
        searchBar.sendKeys(Keys.ENTER);
            
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//            try {
//                    shortWait.until(ExpectedConditions.presenceOfElementLocated(By.className("incoming-msgs")));
//                    driver.findElement(By.className("incoming-msgs")).click();
//            } catch (Exception e) {
//                    System.out.println("Can't find Scrolldown button.");
//            }

    }

    public void waitForConnection() throws TimeoutException {
        try {
            longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(@class, \"selectable-text copyable-text\")]")));
            // TimeUnit.SECONDS.sleep(3);
            connected = true;
            System.out.println("Connected.");

        } catch (TimeoutException t) {
            connected = false;
            throw t;
        }

    }

    public String getCurrentConvImg() {
        By byConvImg = By.xpath("//*[@id=\"main\"]/header/div[1]/div/div/img");
        WebElement img = driver.findElement(byConvImg);
        return img.getAttribute("src").replaceFirst("t=s", "t=l");
    }

    public String getCurrentConvName() {
        WebElement chatHeader = driver.findElement(By.cssSelector(".pane-header.pane-chat-header"));
        WebElement title = chatHeader.findElement(By.className("chat-title"));
        WebElement titleText = title.findElement(By.cssSelector(".emojitext.ellipsify"));
        return titleText.getAttribute("title");
    }

    public void sendMsg(String msg) {
        try {
            Robot robot = new Robot();

            WebElement input = driver.findElement(By.xpath("//div[contains(@title, \"Digite uma mensagem\")]"));
            input.click();

            StringSelection stringSelection = new StringSelection(msg);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);

            TimeUnit.SECONDS.sleep(3);
            
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            
            TimeUnit.SECONDS.sleep(3);
        } catch (AWTException e) {
                e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<WebElement> getVisableMessages() {
        List<WebElement> messagesWithHeader = driver.findElements(By.cssSelector(".has-author"));
        List<WebElement> headers = new ArrayList<WebElement>();

        for (WebElement msg : messagesWithHeader) {
            WebElement header = msg.findElement(By.cssSelector(".message-author")).findElement(By.cssSelector(".text-clickable"));
            headers.add(header);
        }

        return headers;
    }

    public WebElement getNewMessageChat() {
        try {
            WebElement newMsgNotification = driver.findElement(By.cssSelector(".icon-meta.unread-count"));
            WebElement chatImg = newMsgNotification.findElement(By.xpath("./../../../../../../.."));	
            return chatImg;
        } catch (Exception e) {
            System.out.println("No new messages.");
            return null;
        }

    }
}
