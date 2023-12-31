package whatsender.application.splashscreen;

import java.awt.Color;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JDialog;
import whatsender.application.bot.config.utilities.Browser;
import whatsender.application.bot.config.utilities.WhatsAppDriver;
import whatsender.application.entities.Message;
import whatsender.application.helpers.MessageBuilder;
import whatsender.application.main.Application;

/**
 *
 * @author ALEXANDRE
 */
public class SplashScreen extends javax.swing.JDialog {
    private WhatsAppDriver whatsapp;
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public SplashScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        getContentPane().setBackground(new Color(221, 221, 221));
        //  To disable key Alt+F4 to close dialog
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doTask("Verificando dados do cliente ...", 50);
                    doTask("Verificando arquivo de mensagem ...", 75);
                    doTask("Verificando conexão com WhatsApp Web ...", 100);
                    doTask("Concluído ...", 101);
                    dispose();
                    curvesPanel1.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
        }).start();
    }
    
    private void doTask(String taskName, int progress) throws Exception {
        lblProgress.setText(taskName);
        Thread.sleep(2000);
        progressBar.setValue(progress);
        
        switch (progress) {
            case 50:       
                
                
                break;
            case 75:
                this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
                this.em = this.emf.createEntityManager();
                
                if (em.isOpen() ) {
                    //LOCALIZAR O MENSAGEM PADRAO NO DATABASE
                    em.getTransaction().begin();
                    Message message = em.find(Message.class, 1);
                    em.getTransaction().commit();
                    
                    if(message.equals(null)){
                        em.getTransaction().begin();
                        MessageBuilder messageBuilder = new MessageBuilder();
                        Message new_message = new Message(null, messageBuilder.loadDefaultMessage());
                        em.persist(new_message);
                        em.getTransaction().commit();
                    }
                }
                em.close();
                emf.close();
                break;
            case 100:
                this.whatsapp = new WhatsAppDriver(Browser.CHROME);
                this.whatsapp.open();
                this.whatsapp.waitForConnection();
                
                if (this.whatsapp.is_connected()){
                    dispose();
                    Thread.interrupted();
                    curvesPanel1.stop();
                    new Application(this.whatsapp).setVisible(true);
                }
                break;
        } 
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        curvesPanel1 = new whatsender.application.splashscreen.CurvesPanel();
        progressBar = new whatsender.application.splashscreen.ProgressBarCustom();
        lblProgress = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblProgress.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        lblProgress.setForeground(new java.awt.Color(247, 247, 247));
        lblProgress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgress.setText("Progresso");

        javax.swing.GroupLayout curvesPanel1Layout = new javax.swing.GroupLayout(curvesPanel1);
        curvesPanel1.setLayout(curvesPanel1Layout);
        curvesPanel1Layout.setHorizontalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(lblProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        curvesPanel1Layout.setVerticalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, curvesPanel1Layout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProgress)
                .addGap(109, 109, 109))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SplashScreen dialog = new SplashScreen(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.application.splashscreen.CurvesPanel curvesPanel1;
    private javax.swing.JLabel lblProgress;
    private whatsender.application.splashscreen.ProgressBarCustom progressBar;
    // End of variables declaration//GEN-END:variables
}
