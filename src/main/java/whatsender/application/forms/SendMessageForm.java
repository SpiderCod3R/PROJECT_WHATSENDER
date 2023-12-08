package whatsender.application.forms;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import whatsender.application.entities.Contact;
import whatsender.application.entities.Message;
import whatsender.application.filechooser.JnaFileChooser;
import whatsender.application.main.Application;



/**
 *
 * @author ALEXANDRE
 */
public class SendMessageForm extends javax.swing.JPanel {
    private Message message;
    private Application app;
    List<Contact> lstContacts = new ArrayList<>();
    
    public SendMessageForm() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        EntityManager em = emf.createEntityManager();
        
        initComponents();
        setOpaque(false);
        
        rbSingleContact.setSelected(true);
        inputSingleContact.setVisible(true);
        inputMultiContacts.setVisible(false);
        btnInputSearch.setVisible(false);
        
        em.getTransaction().begin();
        message = em.find(Message.class, 1);
        em.getTransaction().commit();
         
        if (message==null){
            em.getTransaction().begin();
            message = new Message();
            Message new_message = new Message(null, message.loadDefaultMessage());
            em.persist(new_message);
            em.getTransaction().commit();
            
            inputMessageArea.setText(new_message.getBodyMessage());
            em.close();
            emf.close();
        } else {
            inputMessageArea.setText(message.getBodyMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        pnClientData = new javax.swing.JPanel();
        lblCompanyName = new javax.swing.JLabel();
        lblCompany = new javax.swing.JLabel();
        lblTelCom = new javax.swing.JLabel();
        lblCompanyPhone = new javax.swing.JLabel();
        lblWhatsPhone = new javax.swing.JLabel();
        lblWhatsapp = new javax.swing.JLabel();
        pnContactsList = new javax.swing.JPanel();
        rbSingleContact = new whatsender.application.swing.JRadioButtonCustom();
        rbMultiContacts = new whatsender.application.swing.JRadioButtonCustom();
        inputSingleContact = new whatsender.application.swing.input.FormatedTextField();
        btnInputSearch = new whatsender.application.swing.custom_button.SwingCustomButton();
        inputMultiContacts = new whatsender.application.swing.input.TextField();
        pnMessages = new javax.swing.JPanel();
        swingCustomButton1 = new whatsender.application.swing.custom_button.SwingCustomButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputMessageArea = new whatsender.application.swing.input.TextArea();

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("/ Enviar Mensagens");

        lblCompanyName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCompanyName.setText("Nome da Empresa");

        lblCompany.setText("EmpresaNome");

        lblTelCom.setText("Telefone");

        lblCompanyPhone.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCompanyPhone.setText("Telefone Comercial");

        lblWhatsPhone.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWhatsPhone.setText("Celular Whatsapp");

        lblWhatsapp.setText("WhatsApp numero");

        javax.swing.GroupLayout pnClientDataLayout = new javax.swing.GroupLayout(pnClientData);
        pnClientData.setLayout(pnClientDataLayout);
        pnClientDataLayout.setHorizontalGroup(
            pnClientDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnClientDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnClientDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(pnClientDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelCom, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCompanyPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 339, Short.MAX_VALUE)
                .addGroup(pnClientDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWhatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWhatsPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnClientDataLayout.setVerticalGroup(
            pnClientDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnClientDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnClientDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCompanyName)
                    .addComponent(lblCompanyPhone)
                    .addComponent(lblWhatsPhone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnClientDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCompany)
                    .addComponent(lblTelCom)
                    .addComponent(lblWhatsapp))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pnContactsList.setBackground(new java.awt.Color(217, 227, 243));

        rbSingleContact.setText("Apenas um contato");
        rbSingleContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSingleContactActionPerformed(evt);
            }
        });

        rbMultiContacts.setText("Contatos em Lote");
        rbMultiContacts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMultiContactsActionPerformed(evt);
            }
        });

        inputSingleContact.setText("(  )     -    ");

        btnInputSearch.setBackground(new java.awt.Color(51, 51, 255));
        btnInputSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnInputSearch.setText("Importar xls/csv");
        btnInputSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnInputSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputSearchActionPerformed(evt);
            }
        });

        inputMultiContacts.setText("...");

        javax.swing.GroupLayout pnContactsListLayout = new javax.swing.GroupLayout(pnContactsList);
        pnContactsList.setLayout(pnContactsListLayout);
        pnContactsListLayout.setHorizontalGroup(
            pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactsListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnContactsListLayout.createSequentialGroup()
                        .addComponent(btnInputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(inputMultiContacts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnContactsListLayout.createSequentialGroup()
                        .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputSingleContact, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnContactsListLayout.createSequentialGroup()
                                .addComponent(rbSingleContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(rbMultiContacts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnContactsListLayout.setVerticalGroup(
            pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactsListLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSingleContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbMultiContacts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inputSingleContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnContactsListLayout.createSequentialGroup()
                        .addComponent(inputMultiContacts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnInputSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnMessages.setBackground(new java.awt.Color(217, 227, 243));

        swingCustomButton1.setBackground(new java.awt.Color(17, 186, 140));
        swingCustomButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/send.png"))); // NOI18N
        swingCustomButton1.setText("Enviar mensagem");
        swingCustomButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        inputMessageArea.setColumns(20);
        inputMessageArea.setLineWrap(true);
        inputMessageArea.setRows(5);
        inputMessageArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(inputMessageArea);

        javax.swing.GroupLayout pnMessagesLayout = new javax.swing.GroupLayout(pnMessages);
        pnMessages.setLayout(pnMessagesLayout);
        pnMessagesLayout.setHorizontalGroup(
            pnMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMessagesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(swingCustomButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
            .addGroup(pnMessagesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnMessagesLayout.setVerticalGroup(
            pnMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMessagesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(swingCustomButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(732, 732, 732))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnContactsList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnClientData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnMessages, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(pnClientData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnContactsList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(pnMessages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rbSingleContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSingleContactActionPerformed
        inputSingleContact.setVisible(true);
        inputMultiContacts.setVisible(false);
        btnInputSearch.setVisible(false);
        rbMultiContacts.setSelected(false);
        inputMessageArea.enable(true);
    }//GEN-LAST:event_rbSingleContactActionPerformed

    private void rbMultiContactsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMultiContactsActionPerformed
        inputSingleContact.setVisible(false);
        rbSingleContact.setSelected(false);
        inputMessageArea.enable(false);
        inputMultiContacts.setVisible(true);
        btnInputSearch.setVisible(true);
    }//GEN-LAST:event_rbMultiContactsActionPerformed

    private void btnInputSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputSearchActionPerformed
        JnaFileChooser ch = new JnaFileChooser();
        
        ch.addFilter("csv", "csv");
        ch.addFilter("xls", "xls");
        ch.addFilter("xlsx", "xlsx");
        
        boolean action  = ch.showOpenDialog(app);
        if (action) {
            inputMultiContacts.setText(ch.getSelectedFile().toString());
            inputMultiContacts.setEnabled(false);
            String fileName = ch.getSelectedFile().toString();
            
            try {
                lstContacts = new CsvToBeanBuilder(new FileReader(fileName))
                        .withSkipLines(1)  
                        .withType(Contact.class)
                        .build()
                        .parse();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SendMessageForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            lstContacts.forEach(System.out::println);
        }
    }//GEN-LAST:event_btnInputSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.application.swing.custom_button.SwingCustomButton btnInputSearch;
    private whatsender.application.swing.input.TextArea inputMessageArea;
    private whatsender.application.swing.input.TextField inputMultiContacts;
    private whatsender.application.swing.input.FormatedTextField inputSingleContact;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCompany;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblCompanyPhone;
    private javax.swing.JLabel lblTelCom;
    private javax.swing.JLabel lblWhatsPhone;
    private javax.swing.JLabel lblWhatsapp;
    private javax.swing.JPanel pnClientData;
    private javax.swing.JPanel pnContactsList;
    private javax.swing.JPanel pnMessages;
    private whatsender.application.swing.JRadioButtonCustom rbMultiContacts;
    private whatsender.application.swing.JRadioButtonCustom rbSingleContact;
    private whatsender.application.swing.custom_button.SwingCustomButton swingCustomButton1;
    // End of variables declaration//GEN-END:variables
}
