/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package whatsender.application.splashscreen2;

import java.awt.Color;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JDialog;
import whatsender.application.bot.config.utilities.Browser;
import whatsender.application.bot.config.utilities.WhatsAppDriver;
import whatsender.application.entities.Message;
import whatsender.application.entities.PacoteContratado;
import whatsender.application.helpers.MessageBuilder;
import whatsender.application.main.Application;
import whatsender.application.main.DefinirPacote;

/**
 *
 * @author ALEXANDRE
 */
public class SplashScreen extends javax.swing.JDialog {
    private static WhatsAppDriver WHATSAPP;
    private EntityManagerFactory emf;
    private EntityManager em;
    private static boolean CONNECTED = true;
    private static int TIMER = 300; //5 MINUTOS
    private static DefinirPacote definirPacote;
    
    public SplashScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        getContentPane().setBackground(new Color(221, 221, 221));
        //  To disable key Alt+F4 to close dialog
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        lblConectionTime.setVisible(false);
    }
    
    private void doTask(String taskName, int progress) throws Exception {
        lblProgressoMensagem.setText(taskName);
        Thread.sleep(2000);
        progressBar.setValue(progress);
        this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        this.em = this.emf.createEntityManager();
        
        switch (progress) {
            case 25:
                
                //LOCALIZAR O PACOTE CONTRATADO NO DATABASE
                definirPacote = new DefinirPacote();
                
                this.em.getTransaction().begin();
                PacoteContratado pacote = this.em.find(PacoteContratado.class, 1);
                this.em.getTransaction().commit();
                
                if(pacote == null){
                    Thread.interrupted();
                    dispose();
                    definirPacote.setVisible(true);
                }
                
                this.em.close();
                this.emf.close();
                
                break;
            case 50:
                //LOCALIZAR O MENSAGEM PADRAO NO DATABASE
                if (!definirPacote.isActive() ) {
                    this.em.getTransaction().begin();
                    if (this.em.isOpen() ) {
                        Message message = this.em.find(Message.class, 1);
                        this.em.getTransaction().commit();

                        if(message == null){
                            this.em.getTransaction().begin();
                            MessageBuilder messageBuilder = new MessageBuilder();
                            Message new_message = new Message(null, messageBuilder.loadDefaultMessage());
                            this.em.persist(new_message);
                            this.em.getTransaction().commit();
                        }
                    }
                }
                this.em.close();
                this.emf.close();
                break;
            case 95:
                if (!definirPacote.isActive() ) {
                    WHATSAPP = new WhatsAppDriver(Browser.CHROME);
                    WHATSAPP.open();
                    lblConectionTime.setVisible(true);

                    lblConectionTime.setText("Aguardando Conexão com o WhatsApp Web.");
                    WHATSAPP.waitForConnection();
                    //showTimeForWhatsAppConection();
                    
                    if (WHATSAPP.is_connected() != CONNECTED){
                        lblConectionTime.setText("Tempo esgotado.");
                        Thread.interrupted();
                    } else {
                       
                    }
                    
                }
                break;
            case 100:
                if (!definirPacote.isActive() ) {
                    if(WHATSAPP.is_connected() == true){
                        new Application(WHATSAPP).setVisible(true);
                        Thread.interrupted();
                        dispose();
                    }
                }
                break;
        } 
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progressBar = new whatsender.application.component.ProgressBarCustom();
        lblProgressoMensagem = new javax.swing.JLabel();
        lblConectionTime = new javax.swing.JLabel();
        BackgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        progressBar.setBackground(new java.awt.Color(9, 116, 87));
        progressBar.setForeground(new java.awt.Color(22, 207, 185));
        progressBar.setColorString(new java.awt.Color(22, 207, 185));
        getContentPane().add(progressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 440, 10));

        lblProgressoMensagem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblProgressoMensagem.setForeground(new java.awt.Color(210, 252, 247));
        lblProgressoMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgressoMensagem.setText("Mensagem ...");
        getContentPane().add(lblProgressoMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 470, -1));

        lblConectionTime.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblConectionTime.setForeground(new java.awt.Color(210, 252, 247));
        lblConectionTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConectionTime.setText("Aguardando Whapp");
        getContentPane().add(lblConectionTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 440, -1));

        BackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/load.png"))); // NOI18N
        BackgroundImage.setText("jLabel1");
        getContentPane().add(BackgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doTask("Verificando Pacote contratado ...", 25);
                    doTask("Verificando arquivo de mensagem ...", 50);
                    doTask("Verificando WhatsApp Web ...", 95);
                    
                    if(CONNECTED == true){
                        doTask("Conectado. Seja Bem Vindo ...", 100);
                        dispose();
                    } 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened

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
    private javax.swing.JLabel BackgroundImage;
    private javax.swing.JLabel lblConectionTime;
    private javax.swing.JLabel lblProgressoMensagem;
    private whatsender.application.component.ProgressBarCustom progressBar;
    // End of variables declaration//GEN-END:variables
}
