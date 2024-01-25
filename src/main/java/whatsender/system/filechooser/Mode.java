package whatsender.system.filechooser;

import javax.swing.JFileChooser;

/**
 *
 * @author ALEXANDRE
 */
public enum Mode {
    Files(JFileChooser.FILES_ONLY),
    Directories(JFileChooser.DIRECTORIES_ONLY),
    FilesAndDirectories(JFileChooser.FILES_AND_DIRECTORIES);
    private int jFileChooserValue;
    private Mode(int jfcv) {
            this.jFileChooserValue = jfcv;
    }
    public int getJFileChooserValue() {
            return jFileChooserValue;
    }
}
