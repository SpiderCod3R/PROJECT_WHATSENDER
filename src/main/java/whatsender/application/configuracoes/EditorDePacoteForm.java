package whatsender.application.configuracoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import whatsender.application.entities.Pacote;
import whatsender.application.entities.PacoteContratado;
import whatsender.gui.component.card.ModelCard;
import whatsender.gui.modal.MensagemModal;
import whatsender.gui.modal.MessageConfirmationForm;
import whatsender.gui.modal.popup.GlassPanePopup;

public class EditorDePacoteForm extends javax.swing.JPanel {
    private EntityManagerFactory emf;
    private EntityManager em;
    private Pacote pacote;
    
    public void carregaDadosPacote(Integer pacoteID){
        this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        this.em = this.emf.createEntityManager();
        
        this.em.getTransaction().begin();
        this.pacote = this.em.find(Pacote.class, pacoteID);
        
        dadosPacote.setData(new ModelCard(this.pacote.getPacoteNome(), 
                this.pacote.getPacoteDescricao(), 
                this.pacote.getQtdeMensagensMensais(), 
                this.pacote.getValorPacote(), null));
    }
    
    public EditorDePacoteForm(Integer pacoteID) {
        initComponents();
        carregaDadosPacote(pacoteID);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgPanel = new javax.swing.JPanel();
        btnAtualizarPacote = new whatsender.gui.custom_button.CustomButton();
        txtIndicadoPara = new whatsender.gui.swing.input.TextField();
        txtNomePacote = new whatsender.gui.swing.input.TextField();
        txtQuantidadeMensagens = new whatsender.gui.swing.input.TextField();
        txtValorPacote = new whatsender.gui.swing.input.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTItulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        dadosPacote = new whatsender.gui.component.card.CardPackage();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        bgPanel.setBackground(new java.awt.Color(223, 224, 224));

        btnAtualizarPacote.setBackground(new java.awt.Color(0, 153, 51));
        btnAtualizarPacote.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualizarPacote.setText("Atualizar");
        btnAtualizarPacote.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAtualizarPacote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarPacoteActionPerformed(evt);
            }
        });

        txtIndicadoPara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIndicadoParaKeyReleased(evt);
            }
        });

        txtNomePacote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomePacoteKeyReleased(evt);
            }
        });

        txtQuantidadeMensagens.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantidadeMensagensKeyReleased(evt);
            }
        });

        txtValorPacote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorPacoteKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nome do Pacote");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Indicado para");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Valor");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Quantidade de Mensagens");

        lblTItulo.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        lblTItulo.setForeground(new java.awt.Color(0, 51, 204));
        lblTItulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTItulo.setText("Editar este Pacote");

        jPanel1.setBackground(new java.awt.Color(223, 224, 224));

        dadosPacote.setBackground(new java.awt.Color(192, 200, 212));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(15, Short.MAX_VALUE)
                    .addComponent(dadosPacote, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(8, Short.MAX_VALUE)
                    .addComponent(dadosPacote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout bgPanelLayout = new javax.swing.GroupLayout(bgPanel);
        bgPanel.setLayout(bgPanelLayout);
        bgPanelLayout.setHorizontalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTItulo, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgPanelLayout.createSequentialGroup()
                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnAtualizarPacote, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                .addComponent(txtQuantidadeMensagens, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtValorPacote, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIndicadoPara, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNomePacote, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        bgPanelLayout.setVerticalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTItulo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgPanelLayout.createSequentialGroup()
                        .addComponent(txtNomePacote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)
                        .addComponent(txtIndicadoPara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorPacote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidadeMensagens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnAtualizarPacote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        add(bgPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtualizarPacoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarPacoteActionPerformed
        this.emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        this.em = this.emf.createEntityManager();
        this.em.getTransaction().begin();
        
        this.pacote.setPacoteNome(txtNomePacote.getText());
        this.pacote.setPacoteDescricao(txtIndicadoPara.getText());
        this.pacote.setValorPacote(Float.parseFloat(txtValorPacote.getText().replace(",", ".")));
        this.pacote.setQtdeMensagens(Integer.parseInt(txtQuantidadeMensagens.getText()));
        this.pacote.setQtdeMensagensMensais(Integer.parseInt(txtQuantidadeMensagens.getText()));
        
        this.em.merge(this.pacote);
        this.em.getTransaction().commit();
        
        this.emf.close();
        this.em.close();
        
        if(!this.em.isOpen()) {
            GlassPanePopup.showPopup(showMensagemModal(this.pacote));
        };
        
        txtNomePacote.setText("");
        txtIndicadoPara.setText("");
        txtValorPacote.setText("");
        txtQuantidadeMensagens.setText("");
    }//GEN-LAST:event_btnAtualizarPacoteActionPerformed

    private MensagemModal showMensagemModal(Pacote pacote){
        MensagemModal mensagemModal = new MensagemModal();
        mensagemModal.setMenssagemContrato(pacote);
        mensagemModal.setTitle("Atualizado com Sucesso.");
        return mensagemModal;
    }
    
    
    private void txtNomePacoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomePacoteKeyReleased
        dadosPacote.setLblTitle(txtNomePacote.getText());
    }//GEN-LAST:event_txtNomePacoteKeyReleased

    private void txtIndicadoParaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIndicadoParaKeyReleased
        dadosPacote.setLblDescricao(txtIndicadoPara.getText());
    }//GEN-LAST:event_txtIndicadoParaKeyReleased

    private void txtValorPacoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorPacoteKeyReleased
        try
        {
            Integer valor_pacote = Integer.parseInt(txtValorPacote.getText());
            dadosPacote.setLblValue(valor_pacote.toString());
        }
        catch(NumberFormatException e)
        {
            System.err.println("Apenas numeros são permitidos nesse campo.");
        }
        
    }//GEN-LAST:event_txtValorPacoteKeyReleased

    private void txtQuantidadeMensagensKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeMensagensKeyReleased
        try
        {
            Integer qtde_maxima_mensagens = Integer.parseInt(txtQuantidadeMensagens.getText());
            dadosPacote.setLblQtdMensagens(qtde_maxima_mensagens.toString());
        }
        catch(NumberFormatException e)
        {
            System.err.println("Apenas numeros são permitidos nesse campo.");
        }
        
        
        
    }//GEN-LAST:event_txtQuantidadeMensagensKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgPanel;
    private whatsender.gui.custom_button.CustomButton btnAtualizarPacote;
    private whatsender.gui.component.card.CardPackage dadosPacote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTItulo;
    private whatsender.gui.swing.input.TextField txtIndicadoPara;
    private whatsender.gui.swing.input.TextField txtNomePacote;
    private whatsender.gui.swing.input.TextField txtQuantidadeMensagens;
    private whatsender.gui.swing.input.TextField txtValorPacote;
    // End of variables declaration//GEN-END:variables
}
