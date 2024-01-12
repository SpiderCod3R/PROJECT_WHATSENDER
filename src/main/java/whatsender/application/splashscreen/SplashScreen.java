package whatsender.application.splashscreen;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private boolean running = false;
    
    int contadorRegressivo = 60;
    
    private int minutes=0;
    private int seconds=0;
    private int mili_seconds=0;
    private Thread tMin, tSec, tMil;
    
    public SplashScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        getContentPane().setBackground(new Color(221, 221, 221));
        //  To disable key Alt+F4 to close dialog
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        lblSecCounter.hide();
        lblConectionMessage.hide();
        lblSecCounter.hide();
        
        
    }

    private void doTask(String taskName, int progress) throws Exception {
        lblProgress.setText(taskName);
        Thread.sleep(2000);
        progressBar.setValue(progress);
        
        switch (progress) {
            case 10:
                this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
                this.em = this.emf.createEntityManager();
                
                em.getTransaction().begin();
                if (em.isOpen() ) {
                    //LOCALIZAR O MENSAGEM PADRAO NO DATABASE
                    Message message = em.find(Message.class, 1);
                    em.getTransaction().commit();
                    
                    if(message == null){
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
            case 50:
                //this.whatsapp = new WhatsAppDriver(Browser.CHROME);
                //this.whatsapp.open();
                lblConectionMessage.show();
                
                //this.whatsapp.waitForConnection();
                showTimeForWhatsAppConection();
                
                /*if(!this.whatsapp.is_connected()){
                    lblSecCounter.hide();
                    lblConectionMessage.setText("Esgotado o tempo para conectar. Por favor aperte no botao vermelho ao lado.");
                    
                }
                if (this.whatsapp.is_connected()){
                    dispose();
                    Thread.interrupted();
                    curvesPanel1.stop();
                    new Application(this.whatsapp).setVisible(true);
                }*/
                break;
        } 
    }
    
    
    //https://www.delftstack.com/pt/howto/java/countdown-timer-java/
    private void showTimeForWhatsAppConection(){
        lblConectionMessage.setText("Tempo para Conectar o WhatsApp Web:");
        lblSecCounter.show();
        
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        this.running = true;
        
        final Runnable runnable = new Runnable() {
          public void run() {
            lblSecCounter.setText(contadorRegressivo + "s");
            contadorRegressivo--;

            if (contadorRegressivo < 0) {
              lblConectionMessage.setText("Tempo esgotado. Por favor tente novamente no botao vermelho ao lado.");
              btnConnection.show();
              scheduler.shutdown();
            }
          }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);    

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        curvesPanel1 = new whatsender.application.splashscreen.CurvesPanel();
        progressBar = new whatsender.application.splashscreen.ProgressBarCustom();
        lblProgress = new javax.swing.JLabel();
        pnConectionWhatsApp = new javax.swing.JPanel();
        lblConectionMessage = new javax.swing.JLabel();
        btnConnection = new whatsender.application.swing.Button();
        lblSecCounter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        curvesPanel1.setBackground(new java.awt.Color(255, 255, 255));
        curvesPanel1.setForeground(new java.awt.Color(51, 51, 51));

        lblProgress.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        lblProgress.setForeground(new java.awt.Color(247, 247, 247));
        lblProgress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgress.setText("Progresso");

        pnConectionWhatsApp.setBackground(new java.awt.Color(225, 222, 222));
        pnConectionWhatsApp.setOpaque(false);

        lblConectionMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblConectionMessage.setForeground(new java.awt.Color(255, 255, 255));
        lblConectionMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConectionMessage.setText("Tempo para Conectar o WhatsApp Web:");

        btnConnection.setBackground(new java.awt.Color(230, 147, 147));
        btnConnection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/wi-fi.png"))); // NOI18N

        lblSecCounter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSecCounter.setForeground(new java.awt.Color(240, 240, 240));
        lblSecCounter.setText("0");

        javax.swing.GroupLayout pnConectionWhatsAppLayout = new javax.swing.GroupLayout(pnConectionWhatsApp);
        pnConectionWhatsApp.setLayout(pnConectionWhatsAppLayout);
        pnConectionWhatsAppLayout.setHorizontalGroup(
            pnConectionWhatsAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConectionWhatsAppLayout.createSequentialGroup()
                .addGroup(pnConectionWhatsAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnConectionWhatsAppLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblConectionMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConectionWhatsAppLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSecCounter)
                        .addGap(228, 228, 228)))
                .addComponent(btnConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnConectionWhatsAppLayout.setVerticalGroup(
            pnConectionWhatsAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConectionWhatsAppLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConectionWhatsAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConectionWhatsAppLayout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(lblConectionMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSecCounter))
                    .addGroup(pnConectionWhatsAppLayout.createSequentialGroup()
                        .addComponent(btnConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout curvesPanel1Layout = new javax.swing.GroupLayout(curvesPanel1);
        curvesPanel1.setLayout(curvesPanel1Layout);
        curvesPanel1Layout.setHorizontalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(curvesPanel1Layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(curvesPanel1Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(lblProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 112, Short.MAX_VALUE))
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnConectionWhatsApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        curvesPanel1Layout.setVerticalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, curvesPanel1Layout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProgress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnConectionWhatsApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doTask("Verificando arquivo de mensagem ...", 10);
                    doTask("Verificando WhatsApp Web ...", 50);
                    doTask("Concluído ...", 101);
                    dispose();
                    curvesPanel1.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened

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
    private whatsender.application.swing.Button btnConnection;
    private whatsender.application.splashscreen.CurvesPanel curvesPanel1;
    private javax.swing.JLabel lblConectionMessage;
    private javax.swing.JLabel lblProgress;
    private javax.swing.JLabel lblSecCounter;
    private javax.swing.JPanel pnConectionWhatsApp;
    private whatsender.application.splashscreen.ProgressBarCustom progressBar;
    // End of variables declaration//GEN-END:variables
}
