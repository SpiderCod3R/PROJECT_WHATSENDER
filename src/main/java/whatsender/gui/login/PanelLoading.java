package whatsender.gui.login;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import org.jdesktop.animation.timing.Animator;


public class PanelLoading extends javax.swing.JPanel {

    //private final Animator animator;
    private boolean slideLeft;
    private float animate;
    
    public PanelLoading() {
        initComponents();
        setOpaque(false);
    }
    
    public void setAnimate(boolean slideLeft, float animate) {
        this.slideLeft = slideLeft;
        this.animate = animate;
    }
    
    public void addEventBack(ActionListener event){
        btnBack.addActionListener(event);
    }
    
    @Override
    public void paint(Graphics grphcs) {
        
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());

        int width = getWidth();
        int height = getHeight();
        //float x =easeOutQuint(animate) * width;
        int x =0;
        int y =0;
        
        if(slideLeft){
            int centerY = height/2;
            Path2D.Float p = new Path2D.Float();
            p.moveTo(x, y);
            p.lineTo(width, y);
            p.lineTo(width, height);
            p.lineTo(x, height);
            p.curveTo(x, height, easeOutBounce(animate)*width, centerY, x, y);
            
            g2.fill(p);
        }else {
            g2.fillRect(x, y, width, height);
        }
        g2.dispose();
        super.paint(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loading = new javax.swing.JLabel();
        btnBack = new whatsender.gui.custom_button.CustomButton();

        loading.setBackground(new java.awt.Color(158, 158, 224));
        loading.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loading.setForeground(new java.awt.Color(247, 247, 248));
        loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loading.setText("Carregando ...");
        loading.setOpaque(true);

        btnBack.setBackground(new java.awt.Color(255, 102, 51));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Voltar");
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private float easeOutBounce(float x) {
        float n1 = 7.5625f;
        float d1 = 2.75f;
        double v;
        if (x < 1 / d1) {
            v=  n1 * x * x;
        } else if (x < 2 / d1) {
            v = n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            v =  n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            v= n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
        
        if(slideLeft){
            return (float)(1f - v);
        } else {
            return (float)v;
        }
    }
    
    private float easeOutQuint(float x) {
        double v =  1 - Math.pow(1 - x, 5);
        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.gui.custom_button.CustomButton btnBack;
    private javax.swing.JLabel loading;
    // End of variables declaration//GEN-END:variables
}
