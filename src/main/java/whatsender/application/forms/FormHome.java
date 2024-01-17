package whatsender.application.forms;

import java.awt.Color;
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
import whatsender.application.bot.config.utilities.WhatsAppDriver;
import whatsender.application.entities.LogMessage;
import whatsender.application.main.Application;
import whatsender.application.model.ModelCard;

/**
 *
 * @author ALEXANDRE
 */
public class FormHome extends javax.swing.JPanel {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
    private EntityManager em;
    private List<LogMessage> lstsNormalLogMessages;
    private List<LogMessage> lstsExtLogMessages;

    
    public FormHome(WhatsAppDriver whatsappDriver) {
        initComponents();
        setOpaque(false);

        if(!whatsappDriver.is_connected()){
            pnlWhatsError.setVisible(true);
        }else{
            pnlWhatsError.setVisible(false);
        } 
        
        initData(); 
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
                tblLogModel.addRow(new Object[]{log.getId(), log.getData(), log.getHour(), log.getContactAppointment().getContactPhone(), log.getMessageType(), log.getLogType()});
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
        cardApplicationErrors.setData(new ModelCard("Erros", 0, 0, iconErrors));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cardMessageSended = new whatsender.application.component.Card();
        cardMultiMessagesSended = new whatsender.application.component.Card();
        cardApplicationErrors = new whatsender.application.component.Card();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLog = new javax.swing.JTable();
        pnlWhatsError = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Painel / Página inicial");

        cardMessageSended.setBackground(new java.awt.Color(43, 83, 204));
        cardMessageSended.setColorGradient(new java.awt.Color(94, 179, 221));

        cardMultiMessagesSended.setBackground(new java.awt.Color(43, 83, 204));
        cardMultiMessagesSended.setColorGradient(new java.awt.Color(153, 204, 255));

        cardApplicationErrors.setBackground(new java.awt.Color(255, 51, 51));
        cardApplicationErrors.setColorGradient(new java.awt.Color(51, 0, 51));

        tblLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Data", "Hora", "WhatsApp", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblLog);

        pnlWhatsError.setBackground(new java.awt.Color(184, 41, 41));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 236, 236));
        jLabel2.setText("Erro ao conectar com o WhatsApp Web. Tente novamente no Botao Vermelho no menu lateral.");

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
                        .addGap(68, 68, 68)
                        .addComponent(cardApplicationErrors, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(cardMultiMessagesSended, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardApplicationErrors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.application.component.Card cardApplicationErrors;
    private whatsender.application.component.Card cardMessageSended;
    private whatsender.application.component.Card cardMultiMessagesSended;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlWhatsError;
    private javax.swing.JTable tblLog;
    // End of variables declaration//GEN-END:variables
}
