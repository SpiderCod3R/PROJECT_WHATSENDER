package whatsender.application.forms;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import whatsender.bot.driver.WhatsAppDriver;
import whatsender.application.entities.LogMessage;
import whatsender.application.entities.PacoteContratado;
import whatsender.application.start.Application;
import whatsender.gui.component.card.ModelCard;
import whatsender.gui.modal.MensagemModal;
import whatsender.gui.modal.popup.GlassPanePopup;

/**
 *
 * @author ALEXANDRE
 */
public class FormHome extends javax.swing.JPanel {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
    private EntityManager em;
    private List<LogMessage> lstsNormalLogMessages;
    private List<LogMessage> lstsExtLogMessages;
    private PacoteContratado pacoteContratado;

    Date data_do_dia = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public FormHome(WhatsAppDriver whatsappDriver) {
        initComponents();
        setOpaque(false);

        if(!whatsappDriver.is_connected()){
            pnlWhatsError.setVisible(true);
        }else{
            pnlWhatsError.setVisible(false);
        } 
        
        initData(); 
        
        this.pacoteContratado = this.em.find(PacoteContratado.class, 1);
        
//        if(this.pacoteContratado.getDt_expiracao_contrato().equals(dateFormat.format(data_do_dia))){
//            MensagemModal mensagemModal = exibirMensagemPacoteExpirado();
//            setTimeOut(() -> GlassPanePopup.showPopup(mensagemModal), 1500);
//        }
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
    
    private MensagemModal exibirMensagemPacoteExpirado(){
        MensagemModal mensagemModal = new MensagemModal();
        mensagemModal.setTitle("Aviso!");
        mensagemModal.setMenssagem("Seu pacote de mensagens expirou. "
                                + "\nEntre em contato com o "
                                + "fornecedor do Sistema.");
        mensagemModal.setInvisible();
        return mensagemModal;
    }
    
    public List<LogMessage> listAllNormalLogMessages() {
        //
        this.em = this.emf.createEntityManager();
        this.lstsNormalLogMessages = em.createNativeQuery("SELECT * FROM tb_log_de_mensagem WHERE message_type = 0", LogMessage.class).getResultList();
                
//        Query query = this.em.createQuery("SELECT * FROM tb_log_de_mensagem");
//        query.setParameter("idUsuario", usuario.getId());
//        List<LogMessage> lstsNormalLogMessages = query.getResultList();

        return this.lstsNormalLogMessages;
    }
    
    public List<LogMessage> listAllExtLogMessages() {
        this.em = this.emf.createEntityManager();
        this.lstsExtLogMessages = em.createNativeQuery("SELECT * FROM tb_log_de_mensagem WHERE message_type = 1", LogMessage.class).getResultList();
        return this.lstsExtLogMessages;
    }

    private void initData(){
        listAllNormalLogMessages();
        listAllExtLogMessages();
        initCardData();
        
        DefaultTableModel tblLogModel = (DefaultTableModel) tblLog.getModel();
        removeAllRows(tblLog);
        
        if (tblLogModel.getRowCount() == 0){
            for (LogMessage log : lstsNormalLogMessages){
                tblLogModel.addRow(new Object[]{log.getId(), log.getData(), log.getHour(), log.getDadosConsulta().getDoctor(), log.getDadosConsulta().getContactName() ,log.getDadosConsulta().getContactPhone(), log.getMessageType(), log.getLogType()});
            }
            
            for (LogMessage log : lstsExtLogMessages){
                tblLogModel.addRow(new Object[]{log.getId(), log.getData(), log.getHour(), log.getDadosConsulta().getDoctor(), log.getDadosConsulta().getContactName(), log.getDadosConsulta().getContactPhone(), log.getMessageType(), log.getLogType()});
            }
        }
    }
    
    public static void removeAllRows(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

    }
    
    private void initCardData(){
        Icon iconMessage = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MESSAGE, 40, Color.WHITE);
        Icon iconLote = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.EMAIL, 40, Color.CYAN);
        Icon iconErrors = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ERROR_OUTLINE, 40, Color.PINK);

        Integer valorNormal = (lstsNormalLogMessages.size() * (10/100));
        Integer valorExt = (lstsExtLogMessages.size() * (10/100));
        
        cardMessageSended.setData(new ModelCard("Mensagens", lstsNormalLogMessages.size(), valorNormal, iconMessage));
        cardMultiMessagesSended.setData(new ModelCard("Mensagens em Lote", lstsExtLogMessages.size(), valorExt, iconLote));
        //cardApplicationErrors.setData(new ModelCard("Erros", 0, 0, iconErrors));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cardMessageSended = new whatsender.gui.component.card.Card();
        cardMultiMessagesSended = new whatsender.gui.component.card.Card();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLog = new javax.swing.JTable();
        pnlWhatsError = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Painel / Página inicial");

        cardMessageSended.setBackground(new java.awt.Color(43, 83, 204));
        cardMessageSended.setColorGradient(new java.awt.Color(94, 179, 221));

        cardMultiMessagesSended.setBackground(new java.awt.Color(124, 200, 244));
        cardMultiMessagesSended.setColorGradient(new java.awt.Color(12, 81, 100));

        tblLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Data", "Hora", "Médico", "Paciente", "WhatsApp", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblLog);

        pnlWhatsError.setBackground(new java.awt.Color(184, 41, 41));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 236, 236));
        jLabel2.setText("Erro ao conectar com o WhatsApp Web. Tente sair e entrar novamente.");

        javax.swing.GroupLayout pnlWhatsErrorLayout = new javax.swing.GroupLayout(pnlWhatsError);
        pnlWhatsError.setLayout(pnlWhatsErrorLayout);
        pnlWhatsErrorLayout.setHorizontalGroup(
            pnlWhatsErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWhatsErrorLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlWhatsErrorLayout.setVerticalGroup(
            pnlWhatsErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWhatsErrorLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cardMessageSended, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(cardMultiMessagesSended, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(338, 338, 338))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlWhatsError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlWhatsError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardMessageSended, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardMultiMessagesSended, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.gui.component.card.Card cardMessageSended;
    private whatsender.gui.component.card.Card cardMultiMessagesSended;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlWhatsError;
    private javax.swing.JTable tblLog;
    // End of variables declaration//GEN-END:variables
}
