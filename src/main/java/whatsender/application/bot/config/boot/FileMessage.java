package whatsender.application.bot.config.boot;

import static whatsender.application.bot.config.helpers.MessageHelper.DEFAULT_MESSAGE;
import whatsender.application.bot.config.logs.SystemLog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class FileMessage extends SystemFile{
    private static SystemLog systemLog = new SystemLog();
    
    public static void CreateFileMessage() {
        successfully_created_dir = new File(getFilePath() + "\\GNSWhatSender\\data").mkdirs();
        if (new File(getFilePath() + "\\GNSWhatSender\\data").exists()) {
            String targetFileStr = getFilePath() + "\\GNSWhatSender\\data\\message.bin";

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
                bw.write(DEFAULT_MESSAGE);
            } catch (IOException e) {
                systemLog.generateMessageLog("Erro", "ERRO ao criar o arquivo: ".concat(e.getMessage()), null, "Arquivo de mensagem padrão");
                System.out.println("ERRO ao criar o arquivo: ".concat(e.getMessage()));
            }
        }
    }

    public static String ReadFileMessage() {
        String targetFileStr = getFilePath() + "\\GNSWhatSender\\data\\message.bin";
        String message = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(targetFileStr))) {
            String line = br.readLine();
            while (line != null) {
            	String[] messageLines = line.split(" \n ");
            	for (int i = 0; i < messageLines.length; i++) {
                    message += messageLines[i] + "\n";
                }
            	
            	line = br.readLine();
            } 
            
        } catch (IOException e) {
            System.out.println("ERROR Reading file: ".concat(e.getMessage()));
        }
        
        return message;
    }
}
