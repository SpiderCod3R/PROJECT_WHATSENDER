package whatsender.application.forms;

import java.awt.Color;
import javax.swing.Icon;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import whatsender.application.model.ModelCard;

/**
 *
 * @author ALEXANDRE
 */
public class FormHome extends javax.swing.JPanel {

    public FormHome() {
        initComponents();
        setOpaque(false);
        initData();
    }

    private void initData(){
        initCardData();
    }
    
    private void initCardData(){
        Icon iconMessage = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MESSAGE, 40, Color.WHITE);
        Icon iconLote = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.EMAIL, 40, Color.CYAN);
        Icon iconErrors = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ERROR_OUTLINE, 40, Color.PINK);

        cardMessageSended.setData(new ModelCard("Mensagens", 5100, 20, iconMessage));
        cardMultiMessagesSended.setData(new ModelCard("Mensagens em Lote", 10000, 200, iconLote));
        cardApplicationErrors.setData(new ModelCard("Erros", 100, 5, iconErrors));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cardMessageSended = new whatsender.application.component.Card();
        cardMultiMessagesSended = new whatsender.application.component.Card();
        cardApplicationErrors = new whatsender.application.component.Card();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Painel / Página inicial");

        cardMessageSended.setBackground(new java.awt.Color(43, 83, 204));
        cardMessageSended.setColorGradient(new java.awt.Color(94, 179, 221));

        cardMultiMessagesSended.setBackground(new java.awt.Color(43, 83, 204));
        cardMultiMessagesSended.setColorGradient(new java.awt.Color(153, 204, 255));

        cardApplicationErrors.setBackground(new java.awt.Color(255, 51, 51));
        cardApplicationErrors.setColorGradient(new java.awt.Color(51, 0, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cardMessageSended, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(cardMultiMessagesSended, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(cardApplicationErrors, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardMessageSended, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardMultiMessagesSended, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardApplicationErrors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(445, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.application.component.Card cardApplicationErrors;
    private whatsender.application.component.Card cardMessageSended;
    private whatsender.application.component.Card cardMultiMessagesSended;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
