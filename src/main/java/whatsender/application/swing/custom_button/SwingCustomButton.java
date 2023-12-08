package whatsender.application.swing.custom_button;

import whatsender.application.swing.shadow.ShadowRenderer;
import whatsender.application.swing.effect.RippleEffect;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class SwingCustomButton extends JButton{
    private int ROUND=5;
    private Color shadowColor = new Color(170,170,170);
    private BufferedImage imageShadow;
    private final Insets shadowSize = new Insets(2,5,8,5);
    private final RippleEffect rippleEffect = new RippleEffect(this);
        
    public SwingCustomButton() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10, 12,15,12));
        setContentAreaFilled(false);
        setBackground(new Color(255,255,255));
        setForeground(new Color(80,80,80));
        rippleEffect.setRippleColor(new Color(220,220,220));
    }
    
    public int getROUND() {
        return ROUND;
    }

    public void setROUND(int ROUND) {
        this.ROUND = ROUND;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }
    
    public void setRippleColor( Color color ){
        rippleEffect.setRippleColor(color);
    }
    
    public void getRippleColor(){
        rippleEffect.getRippleColor();
    }
    
    
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;
        //  Create Shadow Image
        g2.drawImage(imageShadow, 0, 0, null);
        //  Create Background Color
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, getROUND(), getROUND()));
        g2.fill(area);
        rippleEffect.reder(grphcs, area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageShadow();
    }

    private void createImageShadow() {
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            BufferedImage img = createShadow();
            if (img != null) {
                g2.drawImage(createShadow(), 0, 0, null);
            }
            g2.dispose();
        }
    }

    private BufferedImage createShadow() {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        if (width > 0 && height > 0) {
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Double(0, 0, width, height, getROUND(), getROUND()));
            g2.dispose();
            return new ShadowRenderer(5, 0.3f, getShadowColor()).createShadow(img);
        } else {
            return null;
        }
    }
 
}
