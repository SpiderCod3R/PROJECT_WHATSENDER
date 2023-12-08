package whatsender.application.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JRadioButton;


public class JRadioButtonCustom extends JRadioButton{

    public JRadioButtonCustom() {
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(1,1,1,1));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        Graphics2D g2d =(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int ly=(getHeight() - 16)/2;
        if (isSelected()) {
            if(isEnabled()){
                g2d.setColor(Color.darkGray);
            }else{
                g2d.setColor(Color.GRAY);
            }
            
            g2d.fillOval(1, ly, 16, 16);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(2, ly+1, 14, 14);
            
            if(isEnabled()){
                g2d.setColor(Color.darkGray);
            }else{
                g2d.setColor(Color.GRAY);
            }
            
            g2d.fillOval(5, ly+4, 8, 8);
        } else {
            if(isFocusOwner()) {
                g2d.setColor(getBackground());
            } else {
                g2d.setColor(Color.darkGray);
            }
            g2d.fillOval(1, ly, 16, 16);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(2, ly+1, 14, 14);
        }
    }
}
