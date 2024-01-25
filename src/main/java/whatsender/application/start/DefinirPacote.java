package whatsender.application.start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import whatsender.application.component.popup.GlassPanePopup;
import whatsender.application.entities.Pacote;
import whatsender.application.entities.PacoteContratado;
import whatsender.gui.modal.MessageConfirmationForm;
import whatsender.gui.modal.MensagemModal;
import whatsender.gui.component.card.ModelCard;
import whatsender.application.splashscreen.SplashScreen;

/**
 *
 * @author ALEXANDRE
 */
public class DefinirPacote extends javax.swing.JFrame {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Pacote pacote1, pacote2, pacote3, pacote4;
    
    
    public DefinirPacote() {
        initComponents();
        
        GlassPanePopup.install(this);
        
        this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        this.em = this.emf.createEntityManager();
        
        this.em.getTransaction().begin();
        this.pacote1 = this.em.find(Pacote.class, 1);
        this.pacote2 = this.em.find(Pacote.class, 2);
        this.pacote3 = this.em.find(Pacote.class, 3);
        this.pacote4 = this.em.find(Pacote.class, 4);
        
        this.em.getTransaction().commit();
        
        if( (this.pacote1 == null) && (this.pacote2 == null) && (this.pacote3 == null) && (this.pacote4 == null)){
            this.em.getTransaction().begin();
            this.pacote1 = new Pacote(null, "Standard", "Indicado para pequenas\n empresas.", 20, 500, "101,01", "1 Usuário");
            this.pacote2 = new Pacote(null, "Intermediário 1", "Indicado para \npequenas/média empresas.", 59, 1300, "251,96", "2 Usuários");
            this.pacote3 = new Pacote(null, "Intermediário 2", "Indicado para \nmédia empresas.", 104, 2300, "349,90", "2 Usuários");
            this.pacote4 = new Pacote(null, "Ilimitado", "Indicado para \ngrandes empresas.", 9999, 999999, "510,99", "4 Usuários");
            
            this.em.persist(this.pacote1);
            this.em.persist(this.pacote2);
            this.em.persist(this.pacote3);
            this.em.persist(this.pacote4);
            
            this.em.getTransaction().commit();
        }
        
        initCardData();
    }
    
    private void initCardData(){
        /*Icon iconMessage = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MESSAGE, 40, Color.WHITE);
        Icon iconLote = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.EMAIL, 40, Color.CYAN);
        Icon iconErrors = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ERROR_OUTLINE, 40, Color.PINK);
        */
        
        cardPackage1.setData(new ModelCard(this.pacote1.getPacoteNome(), this.pacote1.getPacoteDescricao(), this.pacote1.getQtdeMensagensMensais(), this.pacote1.getValor() , null));
        cardPackage2.setData(new ModelCard(this.pacote2.getPacoteNome(), this.pacote2.getPacoteDescricao(), this.pacote2.getQtdeMensagensMensais(), this.pacote2.getValor() , null));
        cardPackage3.setData(new ModelCard(this.pacote3.getPacoteNome(), this.pacote3.getPacoteDescricao(), this.pacote3.getQtdeMensagensMensais(), this.pacote3.getValor() , null));
        cardPackage4.setData(new ModelCard(this.pacote4.getPacoteNome(), this.pacote4.getPacoteDescricao(), this.pacote4.getQtdeMensagensMensais(), this.pacote4.getValor() , null));
   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPacoteStandard = new whatsender.gui.swing.Button();
        btnPacoteInter2 = new whatsender.gui.swing.Button();
        btnPacoteInter1 = new whatsender.gui.swing.Button();
        btnPacoteInfinito = new whatsender.gui.swing.Button();
        jLabel1 = new javax.swing.JLabel();
        cardPackage4 = new whatsender.gui.component.card.CardPackage();
        cardPackage1 = new whatsender.gui.component.card.CardPackage();
        cardPackage2 = new whatsender.gui.component.card.CardPackage();
        cardPackage3 = new whatsender.gui.component.card.CardPackage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnPacoteStandard.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteStandard.setForeground(new java.awt.Color(232, 232, 232));
        btnPacoteStandard.setText("Contratar");
        btnPacoteStandard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteStandard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteStandardActionPerformed(evt);
            }
        });

        btnPacoteInter2.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteInter2.setForeground(new java.awt.Color(232, 232, 232));
        btnPacoteInter2.setText("Contratar");
        btnPacoteInter2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteInter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteInter2ActionPerformed(evt);
            }
        });

        btnPacoteInter1.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteInter1.setForeground(new java.awt.Color(232, 232, 232));
        btnPacoteInter1.setText("Contratar");
        btnPacoteInter1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteInter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteInter1ActionPerformed(evt);
            }
        });

        btnPacoteInfinito.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteInfinito.setForeground(new java.awt.Color(232, 232, 232));
        btnPacoteInfinito.setText("Contratar");
        btnPacoteInfinito.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteInfinito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteInfinitoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(82, 121, 239));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELECIONE O PACOTE DESEJADO PARA PROSSEGUIR");

        cardPackage4.setBackground(new java.awt.Color(255, 186, 229));

        cardPackage1.setBackground(new java.awt.Color(183, 211, 253));

        cardPackage2.setBackground(new java.awt.Color(172, 238, 252));

        cardPackage3.setBackground(new java.awt.Color(255, 233, 170));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(cardPackage1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cardPackage3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(cardPackage4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btnPacoteStandard, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(btnPacoteInter1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(btnPacoteInter2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addComponent(btnPacoteInfinito, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(324, 324, 324)
                    .addComponent(cardPackage2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(588, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cardPackage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPackage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPackage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPacoteInter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteInter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteInfinito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteStandard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(107, 107, 107)
                    .addComponent(cardPackage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(144, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void aderir_ou_renovar_contrato(Pacote pacote){
        this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        this.em = this.emf.createEntityManager();
        
        this.em.getTransaction().begin();
        PacoteContratado pacoteContratado = new PacoteContratado(null, pacote, pacote.getQtdeMensagensMensais());        
        this.em.persist(pacoteContratado);
        this.em.getTransaction().commit();
        
        /***
         * Verificar funcionalidade de Logs para pacote contratado
         */

        this.em.close();
        this.emf.close();
    }
    
    public static void setTimeOut(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
    
    private MensagemModal showMensagemModal(Pacote pacote){
        MensagemModal mensagemModal = new MensagemModal();
        mensagemModal.setMenssagemContrato(pacote);
        mensagemModal.setTitle("Aviso!");
        mensagemModal.loadProgressBar();
        
        return mensagemModal;
    }
    
    public void salvaContrato(Pacote pacote){
        aderir_ou_renovar_contrato(pacote);
        GlassPanePopup.closePopupLast();                

        MensagemModal mensagemModal = showMensagemModal(pacote);

        setTimeOut(() -> GlassPanePopup.showPopup(mensagemModal), 2000);
        /***
         * Fechar janela e voltar para o SplashScreen
         */

        setTimeOut(() -> dispose(), 5000);
        setTimeOut(() -> new SplashScreen(null, true).setVisible(true), 7000);
    }
    
    private void btnPacoteStandardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteStandardActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote1);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote1);
            }
        });
        GlassPanePopup.showPopup(obj);
        
    }//GEN-LAST:event_btnPacoteStandardActionPerformed

    private void btnPacoteInter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteInter1ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(this.pacote2);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote2);
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteInter1ActionPerformed

    private void btnPacoteInter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteInter2ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(this.pacote3);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote3);
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteInter2ActionPerformed

    private void btnPacoteInfinitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteInfinitoActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(this.pacote4);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote4);
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteInfinitoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DefinirPacote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DefinirPacote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DefinirPacote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DefinirPacote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DefinirPacote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.gui.swing.Button btnPacoteInfinito;
    private whatsender.gui.swing.Button btnPacoteInter1;
    private whatsender.gui.swing.Button btnPacoteInter2;
    private whatsender.gui.swing.Button btnPacoteStandard;
    private whatsender.gui.component.card.CardPackage cardPackage1;
    private whatsender.gui.component.card.CardPackage cardPackage2;
    private whatsender.gui.component.card.CardPackage cardPackage3;
    private whatsender.gui.component.card.CardPackage cardPackage4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
