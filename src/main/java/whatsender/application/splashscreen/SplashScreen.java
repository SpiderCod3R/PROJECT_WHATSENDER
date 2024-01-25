package whatsender.application.splashscreen;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static boolean RUNNING = false;
    private static int TIMER = 600; //10 MINUTOS
    
    private static DefinirPacote definirPacote;
    
    public SplashScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        getContentPane().setBackground(new Color(221, 221, 221));
        //  To disable key Alt+F4 to close dialog
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        lblTimer.setVisible(false);
        lblConectionTime.setVisible(false);
        btnConnection.setVisible(false);
        lblTimer.setVisible(false);
        lblConnectionMessage.setVisible(false);
    }

    private void doTask(String taskName, int progress) throws Exception {
        lblProgress.setText(taskName);
        Thread.sleep(2000);
        progressBar.setValue(progress);
        this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        this.em = this.emf.createEntityManager();
        
        switch (progress) {
            case 10:
                
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
            case 25:
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
            case 50:
                if (!definirPacote.isActive() ) {
                    WHATSAPP = new WhatsAppDriver(Browser.CHROME);
                    WHATSAPP.open();
                    lblConectionTime.setVisible(true);

                    showTimeForWhatsAppConection();
                    WHATSAPP.waitForConnection();

                    if (WHATSAPP.is_connected() == false){
                        if(RUNNING == false){
                            lblConectionTime.setText("Tempo esgotado.");
                            lblConnectionMessage.setVisible(true);
                            lblConnectionMessage.setText("Por favor tente novamente no botao vermelho ao lado.");
                            btnConnection.setVisible(true);
                        }
                        Thread.interrupted();
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
     
    //https://www.delftstack.com/pt/howto/java/countdown-timer-java/
    private void showTimeForWhatsAppConection(){
        lblConectionTime.setText("Tempo para Conectar o WhatsApp Web:");
        lblTimer.setVisible(true);
       
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                
              lblTimer.setText(TIMER + "s");
              TIMER--;

              if (TIMER < 0) {
                 RUNNING = false;
                timer.cancel();
              }
            }
        }, 0, 1000);
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        curvesPanel1 = new whatsender.application.splashscreen.CurvesPanel();
        progressBar = new whatsender.application.splashscreen.ProgressBarCustom();
        lblProgress = new javax.swing.JLabel();
        pnConectionWhatsApp = new javax.swing.JPanel();
        lblConectionTime = new javax.swing.JLabel();
        btnConnection = new whatsender.application.swing.Button();
        lblTimer = new javax.swing.JLabel();
        lblConnectionMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        curvesPanel1.setBackground(new java.awt.Color(255, 255, 255));
        curvesPanel1.setForeground(new java.awt.Color(102, 102, 102));

        lblProgress.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        lblProgress.setForeground(new java.awt.Color(247, 247, 247));
        lblProgress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgress.setText("Progresso");

        pnConectionWhatsApp.setBackground(new java.awt.Color(225, 222, 222));
        pnConectionWhatsApp.setOpaque(false);

        lblConectionTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblConectionTime.setForeground(new java.awt.Color(255, 255, 255));
        lblConectionTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConectionTime.setText("Tempo para Conectar o WhatsApp Web:");

        btnConnection.setBackground(new java.awt.Color(230, 147, 147));
        btnConnection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/wi-fi.png"))); // NOI18N
        btnConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectionActionPerformed(evt);
            }
        });

        lblTimer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTimer.setForeground(new java.awt.Color(240, 240, 240));
        lblTimer.setText("0");

        lblConnectionMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblConnectionMessage.setForeground(new java.awt.Color(252, 252, 252));
        lblConnectionMessage.setText("Por favor tente novamente no botao vermelho ao lado.");

        javax.swing.GroupLayout pnConectionWhatsAppLayout = new javax.swing.GroupLayout(pnConectionWhatsApp);
        pnConectionWhatsApp.setLayout(pnConectionWhatsAppLayout);
        pnConectionWhatsAppLayout.setHorizontalGroup(
            pnConectionWhatsAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConectionWhatsAppLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnConectionWhatsAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConectionWhatsAppLayout.createSequentialGroup()
                        .addComponent(lblConectionTime, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTimer)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConectionWhatsAppLayout.createSequentialGroup()
                        .addComponent(lblConnectionMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(btnConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        pnConectionWhatsAppLayout.setVerticalGroup(
            pnConectionWhatsAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConectionWhatsAppLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConectionWhatsAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnConectionWhatsAppLayout.createSequentialGroup()
                        .addGroup(pnConectionWhatsAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTimer)
                            .addComponent(lblConectionTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblConnectionMessage)))
                .addContainerGap(26, Short.MAX_VALUE))
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
                        .addGap(0, 116, Short.MAX_VALUE))
                    .addGroup(curvesPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnConectionWhatsApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        curvesPanel1Layout.setVerticalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, curvesPanel1Layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProgress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnConectionWhatsApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
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
                    doTask("Verificando Pacote contratado ...", 10);
                    doTask("Verificando arquivo de mensagem ...", 25);
                    doTask("Verificando WhatsApp Web ...", 50);
                    
                    if(RUNNING == true){
                        doTask("Conectado. Seja Bem Vindo ...", 100);
                        dispose();
                        curvesPanel1.stop();
                    } 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened

    private void btnConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectionActionPerformed
        TIMER = 600;
        RUNNING = true;
        
        lblConnectionMessage.setVisible(false);
        WHATSAPP.waitForConnection();
        
        btnConnection.setBackground(new Color(121,224,201,88));
        lblConectionTime.setText("Conectado...");
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(RUNNING == true){
                        doTask("Conectado. Seja Bem Vindo ...", 100);
                    } 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
         
    }//GEN-LAST:event_btnConnectionActionPerformed

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
    private javax.swing.JLabel lblConectionTime;
    private javax.swing.JLabel lblConnectionMessage;
    private javax.swing.JLabel lblProgress;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JPanel pnConectionWhatsApp;
    private whatsender.application.splashscreen.ProgressBarCustom progressBar;
    // End of variables declaration//GEN-END:variables
}
