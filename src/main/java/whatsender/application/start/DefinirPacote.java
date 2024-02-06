package whatsender.application.start;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import whatsender.gui.modal.popup.GlassPanePopup;
import whatsender.application.entities.Pacote;
import whatsender.application.entities.PacoteContratado;
import whatsender.gui.modal.MessageConfirmationForm;
import whatsender.gui.modal.MensagemModal;
import whatsender.gui.component.card.ModelCard;
import whatsender.gui.splashscreen.SplashScreen;


/**
 *
 * @author ALEXANDRE
 */
public class DefinirPacote extends javax.swing.JFrame {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Pacote pacote1, pacote2, pacote3, pacote4;
    private PacoteContratado pacoteContratado;
    
    
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

        this.em.getTransaction().begin();
        this.pacoteContratado = this.em.find(PacoteContratado.class, 1);

        this.em.getTransaction().commit();
        
        if(this.pacoteContratado != null){
            Date data_do_dia = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");


            System.out.println(this.pacoteContratado.getDt_expiracao_contrato().equals(dateFormat.format(data_do_dia)));
            if(this.pacoteContratado.getDt_expiracao_contrato().equals(dateFormat.format(data_do_dia))){
                exibe_pacote_contratado("Renovar Pacote", this.pacoteContratado, true);
            }else {
                exibe_pacote_contratado("Pacote Contratado", this.pacoteContratado, false);
            }
        }
                     
    }
    
    private void exibe_pacote_contratado(String texto_botao, PacoteContratado pacoteContratado, Boolean renovar){
        if(pacoteContratado.getPacote().getId().equals(1)){
            btnPacote1.setText(texto_botao);
            btnPacote1.setBackground(new Color(18,84,102));
            btnPacote1.setForeground(new Color(252,246,246));
            
            if(renovar != true){
                btnPacote1.setEnabled(false);
            }

            btnPacoteAvulso1.setVisible(false);
            btnPacoteAvulso2.setVisible(true);
            btnPacoteAvulso3.setVisible(true);
            btnPacoteAvulso4.setVisible(true);

            btnPacote2.setEnabled(true);
            btnPacote3.setEnabled(true);
            btnPacote4.setEnabled(true);
        }
        if(pacoteContratado.getPacote().getId().equals(2)){
            btnPacote2.setText(texto_botao);
            btnPacote2.setBackground(new Color(18,84,102));
            btnPacote2.setForeground(new Color(252,246,246));
            
            if(renovar != true){
                btnPacote2.setEnabled(false);
            }

            btnPacoteAvulso1.setVisible(true);
            btnPacoteAvulso2.setVisible(false);
            btnPacoteAvulso3.setVisible(true);
            btnPacoteAvulso4.setVisible(true);

            btnPacote1.setEnabled(true);
            btnPacote3.setEnabled(true);
            btnPacote4.setEnabled(true);
        }
        if(pacoteContratado.getPacote().getId().equals(3)){
            btnPacote3.setText(texto_botao);
            btnPacote3.setBackground(new Color(18,84,102));
            btnPacote3.setForeground(new Color(252,246,246));

            if(renovar != true){
                btnPacote3.setEnabled(false);
            }

            btnPacoteAvulso1.setVisible(true);
            btnPacoteAvulso2.setVisible(true);
            btnPacoteAvulso3.setVisible(false);
            btnPacoteAvulso4.setVisible(true);

            btnPacote1.setEnabled(true);
            btnPacote2.setEnabled(true);
            btnPacote4.setEnabled(true);
        }
        if(pacoteContratado.getPacote().getId().equals(4)){
            btnPacote4.setText(texto_botao);
            btnPacote4.setBackground(new Color(18,84,102));
            btnPacote4.setForeground(new Color(252,246,246));

            if(renovar != true){
                btnPacote4.setEnabled(false);
            }

            btnPacoteAvulso4.setVisible(false);

            btnPacoteAvulso1.setVisible(true);
            btnPacoteAvulso2.setVisible(true);

            btnPacote1.setEnabled(true);
            btnPacote2.setEnabled(true);
            btnPacote3.setEnabled(true);
        }
    }
    
    private void initCardData(){
        cardPackage1.setData(new ModelCard(this.pacote1.getPacoteNome(), this.pacote1.getPacoteDescricao(), this.pacote1.getQtdeMensagensMensais(), this.pacote1.getValor() , null));
        cardPackage2.setData(new ModelCard(this.pacote2.getPacoteNome(), this.pacote2.getPacoteDescricao(), this.pacote2.getQtdeMensagensMensais(), this.pacote2.getValor() , null));
        cardPackage3.setData(new ModelCard(this.pacote3.getPacoteNome(), this.pacote3.getPacoteDescricao(), this.pacote3.getQtdeMensagensMensais(), this.pacote3.getValor() , null));
        cardPackage4.setData(new ModelCard(this.pacote4.getPacoteNome(), this.pacote4.getPacoteDescricao(), this.pacote4.getQtdeMensagensMensais(), this.pacote4.getValor() , null));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnPacote1 = new whatsender.gui.swing.Button();
        btnPacote3 = new whatsender.gui.swing.Button();
        btnPacote2 = new whatsender.gui.swing.Button();
        btnPacote4 = new whatsender.gui.swing.Button();
        cardPackage4 = new whatsender.gui.component.card.CardPackage();
        cardPackage1 = new whatsender.gui.component.card.CardPackage();
        cardPackage2 = new whatsender.gui.component.card.CardPackage();
        cardPackage3 = new whatsender.gui.component.card.CardPackage();
        btnPacoteAvulso1 = new whatsender.gui.swing.Button();
        btnPacoteAvulso2 = new whatsender.gui.swing.Button();
        btnPacoteAvulso3 = new whatsender.gui.swing.Button();
        btnPacoteAvulso4 = new whatsender.gui.swing.Button();
        bottomPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        picture = new whatsender.gui.swing.ImageAvatar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELECIONE O PACOTE DESEJADO PARA PROSSEGUIR");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 370, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/navbar_bg.PNG"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 1160, 60));

        btnPacote1.setBackground(new java.awt.Color(111, 111, 249));
        btnPacote1.setForeground(new java.awt.Color(232, 232, 232));
        btnPacote1.setText("Contratar Mensal");
        btnPacote1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacote1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacote1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPacote1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 154, -1));

        btnPacote3.setBackground(new java.awt.Color(111, 111, 249));
        btnPacote3.setForeground(new java.awt.Color(232, 232, 232));
        btnPacote3.setText("Contratar Mensal");
        btnPacote3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacote3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacote3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPacote3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 440, 154, -1));

        btnPacote2.setBackground(new java.awt.Color(111, 111, 249));
        btnPacote2.setForeground(new java.awt.Color(232, 232, 232));
        btnPacote2.setText("Contratar Mensal");
        btnPacote2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacote2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacote2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPacote2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, 154, -1));

        btnPacote4.setBackground(new java.awt.Color(111, 111, 249));
        btnPacote4.setForeground(new java.awt.Color(232, 232, 232));
        btnPacote4.setText("Contratar Mensal");
        btnPacote4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacote4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacote4ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPacote4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 440, 154, -1));

        cardPackage4.setBackground(new java.awt.Color(255, 186, 229));
        jPanel1.add(cardPackage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, 238, -1));

        cardPackage1.setBackground(new java.awt.Color(183, 211, 253));
        jPanel1.add(cardPackage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 110, 238, -1));

        cardPackage2.setBackground(new java.awt.Color(172, 238, 252));
        jPanel1.add(cardPackage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 111, 238, -1));

        cardPackage3.setBackground(new java.awt.Color(255, 233, 170));
        jPanel1.add(cardPackage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(619, 110, 238, -1));

        btnPacoteAvulso1.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteAvulso1.setForeground(new java.awt.Color(232, 232, 232));
        btnPacoteAvulso1.setText("Contratar Avulso");
        btnPacoteAvulso1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteAvulso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteAvulso1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPacoteAvulso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 154, -1));

        btnPacoteAvulso2.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteAvulso2.setForeground(new java.awt.Color(232, 232, 232));
        btnPacoteAvulso2.setText("Contratar Avulso");
        btnPacoteAvulso2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteAvulso2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteAvulso2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPacoteAvulso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 480, 154, -1));

        btnPacoteAvulso3.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteAvulso3.setForeground(new java.awt.Color(232, 232, 232));
        btnPacoteAvulso3.setText("Contratar Avulso");
        btnPacoteAvulso3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteAvulso3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteAvulso3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPacoteAvulso3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, 154, -1));

        btnPacoteAvulso4.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteAvulso4.setForeground(new java.awt.Color(232, 232, 232));
        btnPacoteAvulso4.setText("Contratar Avulso");
        btnPacoteAvulso4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteAvulso4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteAvulso4ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPacoteAvulso4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 480, 154, -1));

        bottomPanel.setBackground(new java.awt.Color(236, 245, 249));
        bottomPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(29, 24, 77));
        jLabel2.setText("Versão");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(29, 24, 77));
        jLabel3.setText("0.99 Beta");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(29, 24, 77));
        jLabel4.setText("Copyright (c) 2023 Globalnetsis - globalnetsis.com.br");

        picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo2.png"))); // NOI18N

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bottomPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(picture, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bottomPanelLayout.createSequentialGroup()
                        .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1154, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void aderir_ou_renovar_contrato(Pacote pacote, String tipoPacote){
        this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        this.em = this.emf.createEntityManager();
        
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        String data_formatada = dateFormat.format(currentDate);
        String hora_formatada = hourFormat.format(currentDate);
        
        this.pacoteContratado = this.em.find(PacoteContratado.class, 1);
        if(this.pacoteContratado == null){
            this.em.getTransaction().begin();
            PacoteContratado pacoteContratado = new PacoteContratado(null, pacote, pacote.getQtdeMensagensMensais(), tipoPacote);        
            this.em.persist(pacoteContratado);
            this.em.getTransaction().commit();
        }else{
            this.em.getTransaction().begin();
            this.pacoteContratado.setPacote(pacote);
            this.pacoteContratado.setMensagensContratada(pacote.getQtdeMensagensMensais());
            this.pacoteContratado.setMensagensDisponiveis(pacote.getQtdeMensagensMensais());
            this.pacoteContratado.setTipoPacote(tipoPacote);
            this.pacoteContratado.setDt_renovacao_contrato(data_formatada);
            this.pacoteContratado.setHr_renovacao_contrato(hora_formatada);
            this.em.merge(this.pacoteContratado);
            this.em.getTransaction().commit();
        }
        
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
    
    public void salvaContrato(Pacote pacote, String tipoPacote){
        aderir_ou_renovar_contrato(pacote, tipoPacote);
        GlassPanePopup.closePopupLast();                

        MensagemModal mensagemModal = showMensagemModal(pacote);

        setTimeOut(() -> GlassPanePopup.showPopup(mensagemModal), 2000);
        /***
         * Fechar janela e voltar para o SplashScreen
         */

        setTimeOut(() -> dispose(), 5000);
        setTimeOut(() -> new SplashScreen(null, true).setVisible(true), 7000);
    }
    
    private void btnPacote1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacote1ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote1);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote1, "CONTRATO MENSAL");
            }
        });
        GlassPanePopup.showPopup(obj);
        
    }//GEN-LAST:event_btnPacote1ActionPerformed

    private void btnPacote2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacote2ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(this.pacote2);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote2, "CONTRATO MENSAL");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacote2ActionPerformed

    private void btnPacote3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacote3ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(this.pacote3);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote3, "CONTRATO MENSAL");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacote3ActionPerformed

    private void btnPacote4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacote4ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(this.pacote4);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote4, "CONTRATO MENSAL");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacote4ActionPerformed

    private void btnPacoteAvulso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteAvulso1ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote1);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote1, "CONTRATO AVULSO");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteAvulso1ActionPerformed

    private void btnPacoteAvulso2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteAvulso2ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote2);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote2, "CONTRATO AVULSO");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteAvulso2ActionPerformed

    private void btnPacoteAvulso3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteAvulso3ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote3);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote3, "CONTRATO AVULSO");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteAvulso3ActionPerformed

    private void btnPacoteAvulso4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteAvulso4ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote4);
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote4, "CONTRATO AVULSO");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteAvulso4ActionPerformed

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
    private javax.swing.JPanel bottomPanel;
    private whatsender.gui.swing.Button btnPacote1;
    private whatsender.gui.swing.Button btnPacote2;
    private whatsender.gui.swing.Button btnPacote3;
    private whatsender.gui.swing.Button btnPacote4;
    private whatsender.gui.swing.Button btnPacoteAvulso1;
    private whatsender.gui.swing.Button btnPacoteAvulso2;
    private whatsender.gui.swing.Button btnPacoteAvulso3;
    private whatsender.gui.swing.Button btnPacoteAvulso4;
    private whatsender.gui.component.card.CardPackage cardPackage1;
    private whatsender.gui.component.card.CardPackage cardPackage2;
    private whatsender.gui.component.card.CardPackage cardPackage3;
    private whatsender.gui.component.card.CardPackage cardPackage4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private whatsender.gui.swing.ImageAvatar picture;
    // End of variables declaration//GEN-END:variables
}
