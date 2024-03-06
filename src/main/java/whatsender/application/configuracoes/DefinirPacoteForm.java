package whatsender.application.configuracoes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import whatsender.application.entities.LogPacote;
import whatsender.application.entities.Pacote;
import whatsender.application.entities.PacoteContratado;
import whatsender.application.logs.LogType;
import whatsender.gui.component.card.ModelCard;
import whatsender.gui.modal.MensagemModal;
import whatsender.gui.modal.MessageConfirmationForm;
import whatsender.gui.modal.popup.GlassPanePopup;

public class DefinirPacoteForm extends javax.swing.JPanel {
    private EntityManagerFactory emf;
    private EntityManager em;
    private Pacote pacote1, pacote2, pacote3, pacote4;
    private PacoteContratado pacoteContratado;
    private static LogPacote logPacote;
    
    public DefinirPacoteForm() {
        initComponents();
        
        this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        this.em = this.emf.createEntityManager();
        
        this.em.getTransaction().begin();
        this.pacote1 = this.em.find(Pacote.class, 1);
        this.pacote2 = this.em.find(Pacote.class, 2);
        this.pacote3 = this.em.find(Pacote.class, 3);
        this.pacote4 = this.em.find(Pacote.class, 4);
        
        this.pacoteContratado = this.em.find(PacoteContratado.class, 1);
        this.em.getTransaction().commit();

        carregarDadosPacotes();
        
        Date data_do_dia = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

        if(this.pacoteContratado != null) {
            System.out.println("PACOTE EXPIRADO " + this.pacoteContratado.getDt_expiracao_contrato().equals(dateFormat.format(data_do_dia)));
            if(this.pacoteContratado.getDt_expiracao_contrato().equals(dateFormat.format(data_do_dia))){
                exibe_pacote_contratado("Renovar Pacote", this.pacoteContratado, true);
            }else {
                exibe_pacote_contratado("Pacote Contratado", this.pacoteContratado, false);
            }

            lblDtContrato.setText(this.pacoteContratado.getDt_contrato());
            lblVencimento.setText(this.pacoteContratado.getDt_expiracao_contrato());
            lblQtMensagens.setText(this.pacoteContratado.getMensagensDisponiveis().toString());
        } 
    }
    
    private void exibe_pacote_contratado(String texto_botao, PacoteContratado pacoteContratado, Boolean renovar){
        if(pacoteContratado.getPacote().getId().equals(1)){
            if(renovar != true){
                btnPacote1.setEnabled(false);
            }
            
            btnPacote1.setText(texto_botao);
            btnPacote1.setBackground(new Color(18,84,102));
            btnPacote1.setForeground(new Color(255,255,255));
            
            btnPacote2.setBackground(new Color(111,111,249));
            btnPacote2.setForeground(new Color(251,251,251));
            
            btnPacote3.setBackground(new Color(111,111,249));
            btnPacote3.setForeground(new Color(251,251,251));
            
            btnPacote4.setBackground(new Color(111,111,249));
            btnPacote4.setForeground(new Color(251,251,251));

            

//            btnPacoteAvulso1.setVisible(false);
//            
//            btnPacoteAvulso2.setVisible(true);
//            btnPacoteAvulso2.setText("Contratar Avulso");
//            
//            btnPacoteAvulso3.setVisible(true);
//            btnPacoteAvulso3.setText("Contratar Avulso");
//            
//            btnPacoteAvulso4.setVisible(true);
//            btnPacoteAvulso4.setText("Contratar Avulso");
//
//            btnPacote2.setEnabled(true);
//            btnPacote2.setText("Contratar Mensal");
//            
//            btnPacote3.setEnabled(true);
//            btnPacote3.setText("Contratar Mensal");
//            
//            btnPacote4.setEnabled(true);
//            btnPacote4.setText("Contratar Mensal");
        }
        if(pacoteContratado.getPacote().getId().equals(2)){
            if(renovar == true){
                
            }
            
            btnPacote2.setText(texto_botao);
            btnPacote2.setBackground(new Color(18,84,102));
            btnPacote2.setForeground(new Color(86,240,196, 94));
            
            btnPacote1.setBackground(new Color(111,111,249));
            btnPacote1.setForeground(new Color(251,251,251));
            
            btnPacote3.setBackground(new Color(111,111,249));
            btnPacote3.setForeground(new Color(251,251,251));
            
            btnPacote4.setBackground(new Color(111,111,249));
            btnPacote4.setForeground(new Color(251,251,251));

//            btnPacoteAvulso2.setVisible(false);
//            btnPacoteAvulso1.setVisible(true);
//            btnPacoteAvulso3.setVisible(true);
//            btnPacoteAvulso4.setVisible(true);
//            btnPacote1.setEnabled(true);
//            btnPacote3.setEnabled(true);
//            btnPacote4.setEnabled(true);
//            
//            btnPacoteAvulso1.setText("Contratar Avulso");           
//            btnPacoteAvulso3.setText("Contratar Avulso");
//            btnPacoteAvulso4.setText("Contratar Avulso");
//
//            btnPacote1.setText("Contratar Mensal");           
//            btnPacote3.setText("Contratar Mensal");           
//            btnPacote4.setText("Contratar Mensal");
        }
        if(pacoteContratado.getPacote().getId().equals(3)){
            btnPacote3.setText(texto_botao);
            btnPacote3.setBackground(new Color(18,84,102));
            btnPacote3.setForeground(Color.WHITE);
            
            btnPacote2.setBackground(new Color(111,111,249));
            btnPacote2.setForeground(new Color(251,251,251));
            
            btnPacote1.setBackground(new Color(111,111,249));
            btnPacote1.setForeground(new Color(251,251,251));
            
            btnPacote4.setBackground(new Color(111,111,249));
            btnPacote4.setForeground(new Color(251,251,251));

            if(renovar != true){
                btnPacote3.setEnabled(false);
            }

//            btnPacoteAvulso1.setVisible(true);
//            btnPacoteAvulso1.setText("Contratar Avulso");
//            
//            btnPacoteAvulso2.setVisible(true);
//            btnPacoteAvulso2.setText("Contratar Avulso");
//            
//            btnPacoteAvulso3.setVisible(false);
//            
//            btnPacoteAvulso4.setVisible(true);
//            btnPacoteAvulso4.setText("Contratar Avulso");
//
//            btnPacote1.setEnabled(true);
//            btnPacote1.setText("Contratar Mensal");
//            
//            btnPacote2.setEnabled(true);
//            btnPacote2.setText("Contratar Mensal");
//            
//            btnPacote4.setEnabled(true);
//            btnPacote4.setText("Contratar Mensal");
        }
        if(pacoteContratado.getPacote().getId().equals(4)){
            btnPacote4.setText(texto_botao);
            btnPacote4.setBackground(new Color(18,84,102));
            btnPacote4.setForeground(Color.WHITE);
            
            btnPacote2.setBackground(new Color(111,111,249));
            btnPacote2.setForeground(new Color(251,251,251));
            
            btnPacote3.setBackground(new Color(111,111,249));
            btnPacote3.setForeground(new Color(251,251,251));
            
            btnPacote1.setBackground(new Color(111,111,249));
            btnPacote1.setForeground(new Color(251,251,251));

            if(renovar != true){
                btnPacote4.setEnabled(false);
            }

//            btnPacoteAvulso4.setVisible(false);
//
//            btnPacoteAvulso1.setVisible(true);
//            btnPacoteAvulso1.setText("Contratar Avulso");
//            
//            btnPacoteAvulso2.setVisible(true);
//            btnPacoteAvulso2.setText("Contratar Avulso");
//            
//            btnPacoteAvulso3.setVisible(true);
//            btnPacoteAvulso3.setText("Contratar Avulso");
//
//            btnPacote1.setEnabled(true);
//            btnPacote1.setText("Contratar Mensal");
//            
//            btnPacote2.setEnabled(true);
//            btnPacote2.setText("Contratar Mensal");
//            
//            btnPacote3.setEnabled(true);
//            btnPacote3.setText("Contratar Mensal");
        }
    }
    
    private void carregarDadosPacotes(){
        cardPackage1.setData(new ModelCard(this.pacote1.getPacoteNome(), this.pacote1.getPacoteDescricao(), this.pacote1.getQtdeMensagensMensais(), this.pacote1.getValorPacote(), null));
        cardPackage2.setData(new ModelCard(this.pacote2.getPacoteNome(), this.pacote2.getPacoteDescricao(), this.pacote2.getQtdeMensagensMensais(), this.pacote2.getValorPacote(), null));
        cardPackage3.setData(new ModelCard(this.pacote3.getPacoteNome(), this.pacote3.getPacoteDescricao(), this.pacote3.getQtdeMensagensMensais(), this.pacote3.getValorPacote(), null));
        cardPackage4.setData(new ModelCard(this.pacote4.getPacoteNome(), this.pacote4.getPacoteDescricao(), this.pacote4.getQtdeMensagensMensais(), this.pacote4.getValorPacote(), null));
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cardPackage1 = new whatsender.gui.component.card.CardPackage();
        cardPackage2 = new whatsender.gui.component.card.CardPackage();
        cardPackage3 = new whatsender.gui.component.card.CardPackage();
        cardPackage4 = new whatsender.gui.component.card.CardPackage();
        jPanel3 = new javax.swing.JPanel();
        btnPacote1 = new whatsender.gui.swing.Button();
        btnPacoteAvulso1 = new whatsender.gui.swing.Button();
        btnPacote2 = new whatsender.gui.swing.Button();
        btnPacoteAvulso2 = new whatsender.gui.swing.Button();
        btnPacote3 = new whatsender.gui.swing.Button();
        btnPacoteAvulso3 = new whatsender.gui.swing.Button();
        btnPacoteAvulso4 = new whatsender.gui.swing.Button();
        btnPacote4 = new whatsender.gui.swing.Button();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblVencimento = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblDtContrato = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblQtMensagens = new javax.swing.JLabel();

        headerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELECIONE O PACOTE DESEJADO PARA PROSSEGUIR");
        headerPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 370, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/navbar_bg.PNG"))); // NOI18N
        headerPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 870, 60));

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 299));

        cardPackage1.setBackground(new java.awt.Color(183, 211, 253));

        cardPackage2.setBackground(new java.awt.Color(172, 238, 252));

        cardPackage3.setBackground(new java.awt.Color(255, 233, 170));

        cardPackage4.setBackground(new java.awt.Color(255, 186, 229));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardPackage1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardPackage2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cardPackage3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardPackage4, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardPackage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPackage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPackage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPackage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        jPanel3.setBackground(new java.awt.Color(233, 233, 233));

        btnPacote1.setBackground(new java.awt.Color(111, 111, 249));
        btnPacote1.setForeground(new java.awt.Color(255, 255, 255));
        btnPacote1.setText("Contratar Mensal");
        btnPacote1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacote1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacote1ActionPerformed(evt);
            }
        });

        btnPacoteAvulso1.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteAvulso1.setForeground(new java.awt.Color(255, 255, 255));
        btnPacoteAvulso1.setText("Contratar Avulso");
        btnPacoteAvulso1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteAvulso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteAvulso1ActionPerformed(evt);
            }
        });

        btnPacote2.setBackground(new java.awt.Color(111, 111, 249));
        btnPacote2.setForeground(new java.awt.Color(255, 255, 255));
        btnPacote2.setText("Contratar Mensal");
        btnPacote2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacote2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacote2ActionPerformed(evt);
            }
        });

        btnPacoteAvulso2.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteAvulso2.setForeground(new java.awt.Color(255, 255, 255));
        btnPacoteAvulso2.setText("Contratar Avulso");
        btnPacoteAvulso2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteAvulso2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteAvulso2ActionPerformed(evt);
            }
        });

        btnPacote3.setBackground(new java.awt.Color(111, 111, 249));
        btnPacote3.setForeground(new java.awt.Color(255, 255, 255));
        btnPacote3.setText("Contratar Mensal");
        btnPacote3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacote3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacote3ActionPerformed(evt);
            }
        });

        btnPacoteAvulso3.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteAvulso3.setForeground(new java.awt.Color(255, 255, 255));
        btnPacoteAvulso3.setText("Contratar Avulso");
        btnPacoteAvulso3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteAvulso3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteAvulso3ActionPerformed(evt);
            }
        });

        btnPacoteAvulso4.setBackground(new java.awt.Color(111, 111, 249));
        btnPacoteAvulso4.setForeground(new java.awt.Color(255, 255, 255));
        btnPacoteAvulso4.setText("Contratar Avulso");
        btnPacoteAvulso4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacoteAvulso4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacoteAvulso4ActionPerformed(evt);
            }
        });

        btnPacote4.setBackground(new java.awt.Color(111, 111, 249));
        btnPacote4.setForeground(new java.awt.Color(255, 255, 255));
        btnPacote4.setText("Contratar Mensal");
        btnPacote4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPacote4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacote4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPacote1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteAvulso1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPacote2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteAvulso2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPacote3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteAvulso3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPacote4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteAvulso4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPacote1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacote2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacote3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacote4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPacoteAvulso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteAvulso2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteAvulso3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacoteAvulso4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel4.setBackground(new java.awt.Color(235, 235, 235));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Data de Expiração");

        lblVencimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblVencimento.setForeground(new java.awt.Color(204, 0, 51));
        lblVencimento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVencimento.setText("00/00/0000");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Data de Inicio da Contratação");

        lblDtContrato.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDtContrato.setForeground(new java.awt.Color(0, 0, 0));
        lblDtContrato.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDtContrato.setText("00/00/0000");

        jLabel2.setText("Mensagens Disponiveis");

        lblQtMensagens.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblQtMensagens.setText("0000");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblDtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250)
                        .addComponent(lblQtMensagens, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(183, 183, 183)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVencimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVencimento)
                    .addComponent(lblDtContrato)
                    .addComponent(lblQtMensagens))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPacote1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacote1ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote1, "CONTRATO MENSAL");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote1, "CONTRATO MENSAL");
            }
        });
        GlassPanePopup.showPopup(obj);

    }//GEN-LAST:event_btnPacote1ActionPerformed

    private void btnPacoteAvulso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteAvulso1ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote1, "CONTRATO AVULSO");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote1, "CONTRATO AVULSO");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteAvulso1ActionPerformed

    private void btnPacote2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacote2ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(this.pacote2, "CONTRATO MENSAL");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote2, "CONTRATO MENSAL");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacote2ActionPerformed

    private void btnPacoteAvulso2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteAvulso2ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote2, "CONTRATO AVULSO");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote2, "CONTRATO AVULSO");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteAvulso2ActionPerformed

    private void btnPacote3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacote3ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(this.pacote3, "CONTRATO MENSAL");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote3, "CONTRATO MENSAL");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacote3ActionPerformed

    private void btnPacoteAvulso3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteAvulso3ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote3, "CONTRATO AVULSO");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote3, "CONTRATO AVULSO");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteAvulso3ActionPerformed

    private void btnPacoteAvulso4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacoteAvulso4ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(pacote4, "CONTRATO AVULSO");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote4, "CONTRATO AVULSO");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacoteAvulso4ActionPerformed

    private void btnPacote4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacote4ActionPerformed
        MessageConfirmationForm obj = new MessageConfirmationForm(this.pacote4, "CONTRATO MENSAL");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                salvaContrato(pacote4, "CONTRATO MENSAL");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_btnPacote4ActionPerformed

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
            
            this.logPacote = new LogPacote(LogType.PACKAGE_HIRED, pacote, pacoteContratado);
            
            this.em.persist(logPacote);
            this.em.persist(pacoteContratado);
            this.em.getTransaction().commit();
            
            lblQtMensagens.setText(this.pacoteContratado.getMensagensDisponiveis().toString());
        }else{
            this.em.getTransaction().begin();
            this.pacoteContratado.setPacote(pacote);
            
            if (tipoPacote == "CONTRATO AVULSO"){
                this.pacoteContratado.setMensagensAvulsaContratada(pacote.getQtdeMensagensMensais());
                this.pacoteContratado.setMensagensAvulsaDisponiveis(pacote.getQtdeMensagensMensais());
            } else {
                this.pacoteContratado.setMensagensContratada(pacote.getQtdeMensagensMensais());
                this.pacoteContratado.setMensagensDisponiveis(pacote.getQtdeMensagensMensais());
            }
            
            
            this.pacoteContratado.setTipoPacote(tipoPacote);
            this.pacoteContratado.setDt_renovacao_contrato(data_formatada);
            this.pacoteContratado.setDt_expiracao_contrato(currentDate);          
            this.pacoteContratado.setHr_renovacao_contrato(hora_formatada);
            
            this.logPacote = new LogPacote(LogType.PACKAGE_RENEWED, pacote, this.pacoteContratado);
            
            this.em.persist(logPacote);
            this.em.merge(this.pacoteContratado);
            this.em.getTransaction().commit();
            
            lblQtMensagens.setText(this.pacoteContratado.getMensagensDisponiveis().toString());
        }

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
        
        if(this.pacoteContratado != null){
            Date data_do_dia = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

            System.out.println("PACOTE EXPIRADO - " + this.pacoteContratado.getDt_expiracao_contrato().equals(dateFormat.format(data_do_dia)));
            if(this.pacoteContratado.getDt_expiracao_contrato().equals(dateFormat.format(data_do_dia))){
                exibe_pacote_contratado("Renovar Pacote", this.pacoteContratado, true);
            }else {
                exibe_pacote_contratado("Pacote Contratado", this.pacoteContratado, false);
            }
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblDtContrato;
    private javax.swing.JLabel lblQtMensagens;
    private javax.swing.JLabel lblVencimento;
    // End of variables declaration//GEN-END:variables
}
