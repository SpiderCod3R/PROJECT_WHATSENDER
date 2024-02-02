package whatsender.application.start;

import java.awt.TextField;
import java.io.IOException;
import whatsender.gui.modal.popup.GlassPanePopup;
import whatsender.gui.swing.input.FormatedTextField;

/**
 *
 * @author ALEXANDRE
 */
public class ClienteDados extends javax.swing.JFrame {
    
    public ClienteDados() {
        initComponents();        
        GlassPanePopup.install(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgPanel = new javax.swing.JPanel();
        lblCompanyName = new javax.swing.JLabel();
        txtCompanyName = new gns.whatsender.swing.input.TextField();
        lblCompanyPhone = new javax.swing.JLabel();
        txtPhone = new gns.whatsender.swing.input.FormatedTextField();
        lblWhatsPhone = new javax.swing.JLabel();
        txtWhatsapp = new gns.whatsender.swing.input.FormatedTextField();
        btnSaveConfig = new gns.whatsender.swing.custom_button.REButton();
        jLabel2 = new javax.swing.JLabel();
        navBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("WelcomeFrame"); // NOI18N
        setResizable(false);

        bgPanel.setBackground(new java.awt.Color(235, 235, 235));

        lblCompanyName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCompanyName.setText("Nome do Cliente");

        lblCompanyPhone.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCompanyPhone.setText("Telefone Comercial");

        try {
            txtPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblWhatsPhone.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWhatsPhone.setText("Celular Whatsapp");

        try {
            txtWhatsapp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnSaveConfig.setBackground(new java.awt.Color(102, 193, 129));
        btnSaveConfig.setForeground(new java.awt.Color(102, 102, 102));
        btnSaveConfig.setText("Salvar Dados");
        btnSaveConfig.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSaveConfig.setShadowColor(new java.awt.Color(102, 193, 129));
        btnSaveConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveConfigActionPerformed(evt);
            }
        });

        jLabel2.setText("Olá Seja bem vindo. Para começar coloque os dados do Cliente.");

        javax.swing.GroupLayout bgPanelLayout = new javax.swing.GroupLayout(bgPanel);
        bgPanel.setLayout(bgPanelLayout);
        bgPanelLayout.setHorizontalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCompanyName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSaveConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgPanelLayout.createSequentialGroup()
                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblCompanyName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCompanyPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWhatsapp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblWhatsPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgPanelLayout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(60, 60, 60))
        );
        bgPanelLayout.setVerticalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(lblCompanyName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgPanelLayout.createSequentialGroup()
                        .addComponent(lblCompanyPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgPanelLayout.createSequentialGroup()
                        .addComponent(lblWhatsPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWhatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(btnSaveConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gns/whatsender/imgs/navbar_bg.PNG"))); // NOI18N

        javax.swing.GroupLayout navBarLayout = new javax.swing.GroupLayout(navBar);
        navBar.setLayout(navBarLayout);
        navBarLayout.setHorizontalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        navBarLayout.setVerticalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(navBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleName("WelcomeFrame");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveConfigActionPerformed
        try {
            if ((getTxtCompanyName().isBlank()) || (getTxtPhoneComercial().isBlank()) || (getTxtWhatsapp().isBlank())){
                systemLog.generateMessageLog("Erro", LocaleHelper.COMPANY_DATA_MISSING, null, evt.getActionCommand());
                Notifications.getInstance().show(Notifications.Type.ERROR,Notifications.Location.BOTTOM_RIGHT , LocaleHelper.COMPANY_DATA_MISSING);
            }else {
                Company company = new Company(getTxtCompanyName(), getTxtPhoneComercial(), getTxtWhatsapp());
                if (company.save()) {
                    Notifications.getInstance().show(Notifications.Type.INFO,Notifications.Location.BOTTOM_RIGHT , company.toString());

                    this.dispose();
                    new SplashScreen(null, true).setVisible(true);
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR,Notifications.Location.BOTTOM_RIGHT , "ERRO ao salvar dados do cliente");
                }

            }
        } catch (NullPointerException e) {
            systemLog.generateMessageLog("Erro", e.getMessage(), null, evt.getActionCommand());
            Notifications.getInstance().show(Notifications.Type.INFO,Notifications.Location.BOTTOM_RIGHT , "ERRO".concat(e.getMessage()));
        } catch (IOException ex) {
            Notifications.getInstance().show(Notifications.Type.INFO,Notifications.Location.BOTTOM_RIGHT , "ERRO".concat(ex.getMessage()));
            systemLog.generateMessageLog("Erro", ex.getMessage(), null, evt.getActionCommand());
        }
    }//GEN-LAST:event_btnSaveConfigActionPerformed

    
    public String getTxtCompanyName() {
        return txtCompanyName.getText();
    }

    public void setTxtCompanyName(TextField txtCompanyName) {
        this.txtCompanyName = txtCompanyName;
    }

    public String getTxtWhatsapp() {
        return this.txtWhatsapp.getText();
    }

    public void setTxtWhatsapp(FormatedTextField txtWhatsapp) {
        this.txtWhatsapp = txtWhatsapp;
    }

    public String getTxtPhoneComercial() {
        return this.txtPhone.getText();
    }

    public void setTxtPhoneComercial(FormatedTextField txtPhone) {
        this.txtPhone = txtPhone;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgPanel;
    private gns.whatsender.swing.custom_button.REButton btnSaveConfig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblCompanyPhone;
    private javax.swing.JLabel lblWhatsPhone;
    private javax.swing.JPanel navBar;
    private gns.whatsender.swing.input.TextField txtCompanyName;
    private gns.whatsender.swing.input.FormatedTextField txtPhone;
    private gns.whatsender.swing.input.FormatedTextField txtWhatsapp;
    // End of variables declaration//GEN-END:variables
}
