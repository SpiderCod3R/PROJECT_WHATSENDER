package whatsender.gui.modal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import whatsender.gui.modal.popup.GlassPanePopup;
import whatsender.application.entities.Pacote;

/**
 *
 * @author ALEXANDRE
 */
public class MessageConfirmationForm extends javax.swing.JPanel {

    
    private String messageBox = "Pacote [PACOTE] \n"
            + "Indicado para [INDICADO]\n"
            + "No Valor de [VALOR]";

    public MessageConfirmationForm() {
    }
    
    public MessageConfirmationForm(String title, String messageBox) {
        initComponents();
        setOpaque(false);
        setTxtPacote(messageBox);
        setLblTitle(title);
    }
    
    public MessageConfirmationForm( Pacote pacote) {
        initComponents();
        setOpaque(false);
        txtPacote.setBackground(new Color(0, 0, 0, 0));
        txtPacote.setSelectionColor(new Color(48, 170, 63, 200));
        txtPacote.setOpaque(false);
        
        
        messageBox = messageBox.
                replace("[PACOTE]", pacote.getPacoteNome()).
                replace("[INDICADO]", pacote.getPacoteDescricao()).
                replace("[VALOR]", "R$: " + pacote.getValor());

        
        txtPacote.setText(messageBox);
    }

    public String getLblTitle() {
        return lblTitle.getText();
    }

    public void setLblTitle(String title) {
        this.lblTitle.setText(title);
    }
    
    public String getTxtPacote() {
        return txtPacote.getText();
    }

    public void setTxtPacote(String txtPacote) {
        this.txtPacote.setText(txtPacote);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(grphcs);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPacote = new javax.swing.JTextPane();
        btnCancelar = new whatsender.gui.swing.Button();
        btnOK = new whatsender.gui.swing.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setText("Deseja contratar esse pacote?");

        txtPacote.setText("TESTE");
        jScrollPane1.setViewportView(txtPacote);

        btnCancelar.setBackground(new java.awt.Color(248, 165, 165));
        btnCancelar.setForeground(new java.awt.Color(146, 23, 23));
        btnCancelar.setText("NÃO");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnOK.setBackground(new java.awt.Color(131, 255, 105));
        btnOK.setForeground(new java.awt.Color(3, 75, 27));
        btnOK.setText("SIM");
        btnOK.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 130, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_btnCancelarActionPerformed

     public void eventOK(ActionListener event) {
        btnOK.addActionListener(event);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.gui.swing.Button btnCancelar;
    private whatsender.gui.swing.Button btnOK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextPane txtPacote;
    // End of variables declaration//GEN-END:variables
}
