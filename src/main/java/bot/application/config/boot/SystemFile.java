package bot.application.config.boot;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public abstract class SystemFile {
    
    private static final String SYSTEM_USER = System.getProperty("user.name");
    private static final String FILE_PATH = "c:\\Users\\".concat(SYSTEM_USER).concat("\\AppData\\Local");
    protected static boolean successfully_created_dir = false;
    private static boolean file_created = false;
    
    
    public static boolean is_directory_exists() {
        return new File(getFilePath() + "\\GNSWhatSender\\data").exists();
    }
    
    public boolean isFile_created() {
        return file_created;
    }
    
    public static boolean isFile_created(String fileName) {
        return new File(getFilePath() + "\\GNSWhatSender\\data\\" + fileName).exists();
    }

    public void setFile_created(boolean file_created) {
        this.file_created = file_created;
    }

    public boolean isSuccessfully_created_dir() {
        return successfully_created_dir;
    }

    public void setSuccessfully_created_dir(boolean successfully_created_dir) {
        this.successfully_created_dir = successfully_created_dir;
    }

    public static String getSystemUser() {
        return SYSTEM_USER;
    }

    public static String getFilePath() {
        return FILE_PATH;
    }
    
    public static String formatXML(String xml) throws TransformerException {

        // write data to xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print by indention
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // add standalone="yes", add line break before the root element
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

        StreamSource source = new StreamSource(new StringReader(xml));
        StringWriter output = new StringWriter();
        transformer.transform(source, new StreamResult(output));

        return output.toString();

    }
}
