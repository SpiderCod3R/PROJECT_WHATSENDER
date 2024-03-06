package whatsender.gui.component.card;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import javax.swing.JLabel;

/**
 *
 * @author ALEXANDRE
 */
public class CardPackage extends javax.swing.JPanel {

    private Color colorGradient;
    private GradientPaint gra;

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
    }
    
    public CardPackage() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(112,69,246));
        colorGradient = new Color(255,255,255);
    }

    public void setData(ModelCard cardData) {
        DecimalFormat df= new DecimalFormat("#, ##0.##");
        lblTitle.setText(cardData.getTitle());
        lblDescricao.setText(cardData.getDescription().stripLeading());
        lblValue.setText("Valor: R$ " + df.format(cardData.getValues()));
        
        if(cardData.getQtdeMessages().contains("9999")){
            lblQtdMensagens.setText("Mensagens: INFINITAS");
        }else{
          lblQtdMensagens.setText("Mensagens: " + cardData.getQtdeMessages());  
        }
    }
    
    

    public String getLblDescricao() {
        return lblDescricao.getText();
    }

    public void setLblDescricao(String lblDescricao) {
        this.lblDescricao.setText(lblDescricao);
    }

    public String getLblTitle() {
        return lblTitle.getText();
    }

    public void setLblTitle(String title) {
        this.lblTitle.setText(title);
    }

    public String getLblValue() {
        return lblValue.getText();
    }

    public void setLblValue(String valorPacote) {
        this.lblValue.setText("Valor: R$ " + valorPacote);
    }

    public String getLblQtdMensagens() {
        return lblQtdMensagens.getText();
    }

    public void setLblQtdMensagens(String QtdMensagens) {
        this.lblQtdMensagens.setText("Mensagens: " + QtdMensagens);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblValue = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblQtdMensagens = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblValue.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblValue.setForeground(new java.awt.Color(102, 102, 102));
        lblValue.setText("Value");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(102, 102, 102));
        lblTitle.setText("Title");

        lblQtdMensagens.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblQtdMensagens.setForeground(new java.awt.Color(102, 102, 102));
        lblQtdMensagens.setText("QtdeMensagens");

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(102, 102, 102));
        lblDescricao.setText("Descrição");
        lblDescricao.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblDescricao.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Indicado para");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQtdMensagens, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 44, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lblValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQtdMensagens)
                .addGap(35, 35, 35))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblQtdMensagens;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblValue;
    // End of variables declaration//GEN-END:variables
}
