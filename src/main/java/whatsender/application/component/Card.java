package whatsender.application.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import whatsender.application.model.ModelCard;

/**
 *
 * @author ALEXANDRE
 */
public class Card extends javax.swing.JPanel {

    private Color colorGradient;
    private GradientPaint gra;

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
    }
    
    public Card() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(112,69,246));
        colorGradient = new Color(255,255,255);
        progBar.setBackground(new Color(255,255,255, 100));
        progBar.setForeground(Color.WHITE);
    }

    public void setData(ModelCard cardData) {
        DecimalFormat df= new DecimalFormat("#, ##0.##");
        lblTitle.setText(cardData.getTitle());
        lblValue.setText(df.format(cardData.getValues()));
        lblIcon.setIcon(cardData.getIcon());
        progBar.setValue(cardData.getPercentage());
        lblPercentage.setText(df.format(cardData.getPercentage()) + "%");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblValue = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();
        progBar = new whatsender.application.swing.ProgressBarCustom();
        lblPercentage = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblValue.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblValue.setForeground(new java.awt.Color(255, 255, 255));
        lblValue.setText("Value");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Title");

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblPercentage.setForeground(new java.awt.Color(255, 255, 255));
        lblPercentage.setText("0%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(progBar, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(lblTitle)
                    .addContainerGap(245, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblValue)
                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPercentage)
                    .addComponent(progBar, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(lblTitle)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gra = new GradientPaint(0, getHeight(), getBackground(), getWidth(), 0 , colorGradient);
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblPercentage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblValue;
    private whatsender.application.swing.ProgressBarCustom progBar;
    // End of variables declaration//GEN-END:variables
}
