package whatsender.application.forms;

import com.opencsv.bean.CsvToBeanBuilder;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import whatsender.application.entities.Appointment;
import whatsender.application.helpers.LocaleHelper;
import whatsender.application.entities.Client;
import whatsender.application.entities.Contact;
import whatsender.application.entities.Message;
import whatsender.application.filechooser.JnaFileChooser;
import whatsender.application.helpers.BannerType;
import whatsender.application.helpers.MessageBuilder;
import whatsender.application.main.Application;



/**
 *
 * @author ALEXANDRE
 */
public class SendMessageForm extends javax.swing.JPanel {
    private MessageBuilder messageBuilder;
    private Message message;
    private Application app;
    private MainForm mainForm;
    
    List<Contact> lstContacts = new ArrayList<>();
    
    private void setBannerMessage(String message, BannerType typeMessage){
        if(!pnBannerMessage.isVisible()){
            pnBannerMessage.setVisible(true);
        }
        
        if(typeMessage.getValue() == "info"){
            pnBannerMessage.setBackground(new Color(50,156,237));
        }
        
        if(typeMessage.getValue() == "sucesso"){
            pnBannerMessage.setBackground(new Color(141,245,208));
        }
        
        if(typeMessage.getValue() == "erro"){
            pnBannerMessage.setBackground(new Color(245,73,85));
        }
        
        lblBannerMessage.setText(message);
    }
    
    private void closeBannerMessage(){
        if(pnBannerMessage.isVisible()){
            pnBannerMessage.setVisible(false);
        }
    }
    
    public SendMessageForm(MainForm mainForm) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        EntityManager em = emf.createEntityManager();
        
        initComponents();
        setOpaque(false);
        
        mainForm = mainForm;
        
        rbSingleContact.setSelected(true);
        inputSingleContact.setVisible(true);
        inputMultiContacts.setVisible(false);
        btnInputSearch.setVisible(false);
        pnBannerMessage.setVisible(false);
        
        /*
        INICIANDO TRANSAÇÃO PARA LOCALIZAR ITENS OBRIGATORIOS
        */
        em.getTransaction().begin();
        
        //LOCALIZAR O MENSAGEM PADRAO NO DATABASE
        message = em.find(Message.class, 1);
        
        //LOCALIZAR O CLIENTE LOCAL NO DATABASE
        Client client  = em.find(Client.class, 1);
        
        em.getTransaction().commit();
        
        /*
        FINAL DA TRANSAÇÃO PARA LOCALIZAR ITENS OBRIGATORIOS
        */
        
        if(client==null) {
            pnClientData.setVisible(false);
            pnClientForm.setVisible(true);
             
            inputClientName.setText("GLOBALNETSIS");
            inputClientPhone.setText("(22)99999-9999");
            inputClientWhatsApp.setText("(22)99999-9999");
        } else {
            pnClientData.setVisible(true);
            pnClientForm.setVisible(false);
            loadClientData(em);
        }
        
        //LOCALIZAR A MENSAGEM PADRÃO NO DATABASE
        if (message==null){
            em.getTransaction().begin();
            messageBuilder = new MessageBuilder();
            System.out.println("Mensagem \n" + messageBuilder.loadDefaultMessage() );
            Message new_message = new Message(null, messageBuilder.loadDefaultMessage());
            em.persist(new_message);
            em.getTransaction().commit();
            
            inputMessageArea.setText(messageBuilder.loadDefaultMessage());
        } else{
            if(client!=null) {
                //CARREGAR MENSAGEM COMPLETA APOS LOCALIZAR REGISTRO DO CLIENTE
                loadMessageWithClientData(em);
            }else{
                inputMessageArea.setText(message.getBodyMessage()); 
            }
        }
        
        em.close();
        emf.close();
        
        inputContactName.setText("Alexandre"); 
        inputSingleContact.setText("(22)999999999");
        inputDate.setText("12/12/2023");
        inputHora.setText("15:30");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        pnClientData = new javax.swing.JPanel();
        lblCompanyName = new javax.swing.JLabel();
        lblClientName = new javax.swing.JLabel();
        lblPhoneNumber = new javax.swing.JLabel();
        lblCompanyPhone = new javax.swing.JLabel();
        lblWhatsPhone = new javax.swing.JLabel();
        lblWhatsapp = new javax.swing.JLabel();
        pnContactsList = new javax.swing.JPanel();
        rbSingleContact = new whatsender.application.swing.JRadioButtonCustom();
        rbMultiContacts = new whatsender.application.swing.JRadioButtonCustom();
        inputSingleContact = new whatsender.application.swing.input.FormatedTextField();
        btnInputSearch = new whatsender.application.swing.custom_button.SwingCustomButton();
        inputMultiContacts = new whatsender.application.swing.input.TextField();
        inputContactName = new whatsender.application.swing.input.TextField();
        lblContactname = new javax.swing.JLabel();
        inputDate = new whatsender.application.swing.input.FormatedTextField();
        lblDate = new javax.swing.JLabel();
        inputHora = new whatsender.application.swing.input.FormatedTextField();
        lblHour = new javax.swing.JLabel();
        lblContactNumber = new javax.swing.JLabel();
        btnAddToMessage = new whatsender.application.swing.custom_button.SwingCustomButton();
        lblDoctor = new javax.swing.JLabel();
        inputDoctor = new whatsender.application.swing.input.TextField();
        pnMessages = new javax.swing.JPanel();
        swingCustomButton1 = new whatsender.application.swing.custom_button.SwingCustomButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputMessageArea = new whatsender.application.swing.input.TextArea();
        pnClientForm = new javax.swing.JPanel();
        lblCompanyName1 = new javax.swing.JLabel();
        lblCompanyPhone1 = new javax.swing.JLabel();
        lblWhatsPhone1 = new javax.swing.JLabel();
        inputClientName = new whatsender.application.swing.input.TextField();
        inputClientPhone = new whatsender.application.swing.input.FormatedTextField();
        inputClientWhatsApp = new whatsender.application.swing.input.FormatedTextField();
        btnSaveClient = new whatsender.application.swing.custom_button.SwingCustomButton();
        pnBannerMessage = new javax.swing.JPanel();
        lblBannerMessage = new javax.swing.JLabel();

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("/ Enviar Mensagens");

        pnClientData.setBackground(new java.awt.Color(217, 227, 243));

        lblCompanyName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCompanyName.setText("Nome da Empresa");

        lblClientName.setText("EmpresaNome");

        lblPhoneNumber.setText("Telefone");

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
                    .addComponent(lblClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnClientDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCompanyPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149)
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
                    .addComponent(lblClientName)
                    .addComponent(lblPhoneNumber)
                    .addComponent(lblWhatsapp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        try {
            inputSingleContact.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnInputSearch.setBackground(new java.awt.Color(51, 51, 255));
        btnInputSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnInputSearch.setText("Importar xls/csv");
        btnInputSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInputSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputSearchActionPerformed(evt);
            }
        });

        inputMultiContacts.setText("...");

        lblContactname.setText("Nome do Contato");

        try {
            inputDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblDate.setText("Data da Consulta");

        inputHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));

        lblHour.setText("Hora da Consulta");

        lblContactNumber.setText("N° Contato");

        btnAddToMessage.setBackground(new java.awt.Color(1, 155, 155));
        btnAddToMessage.setForeground(new java.awt.Color(239, 244, 255));
        btnAddToMessage.setText("Atualizar Mensagem");
        btnAddToMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddToMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToMessageActionPerformed(evt);
            }
        });

        lblDoctor.setText("Nome do Médico");

        javax.swing.GroupLayout pnContactsListLayout = new javax.swing.GroupLayout(pnContactsList);
        pnContactsList.setLayout(pnContactsListLayout);
        pnContactsListLayout.setHorizontalGroup(
            pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactsListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnContactsListLayout.createSequentialGroup()
                        .addComponent(btnInputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputMultiContacts, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
                    .addGroup(pnContactsListLayout.createSequentialGroup()
                        .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnContactsListLayout.createSequentialGroup()
                                .addComponent(lblContactNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnContactsListLayout.createSequentialGroup()
                                .addComponent(inputSingleContact, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputContactName, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnContactsListLayout.createSequentialGroup()
                                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblContactname, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnContactsListLayout.createSequentialGroup()
                                            .addComponent(rbSingleContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(68, 68, 68)
                                            .addComponent(rbMultiContacts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnAddToMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(122, 122, 122)))
                        .addGap(9, 9, 9))
                    .addGroup(pnContactsListLayout.createSequentialGroup()
                        .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputDate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHour, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputHora, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContactname)
                    .addComponent(lblContactNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputContactName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputSingleContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctor)
                    .addComponent(lblDate)
                    .addComponent(lblHour))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddToMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputMultiContacts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pnMessages.setBackground(new java.awt.Color(217, 227, 243));

        swingCustomButton1.setBackground(new java.awt.Color(17, 186, 140));
        swingCustomButton1.setForeground(new java.awt.Color(250, 250, 250));
        swingCustomButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/send.png"))); // NOI18N
        swingCustomButton1.setText("Enviar mensagem");
        swingCustomButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

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

        pnClientForm.setBackground(new java.awt.Color(217, 227, 243));

        lblCompanyName1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCompanyName1.setText("Nome do Cliente");

        lblCompanyPhone1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCompanyPhone1.setText("Telefone Comercial");

        lblWhatsPhone1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWhatsPhone1.setText("Celular Whatsapp");

        try {
            inputClientPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            inputClientWhatsApp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnSaveClient.setBackground(new java.awt.Color(51, 51, 255));
        btnSaveClient.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveClient.setText("Salvar Dados");
        btnSaveClient.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSaveClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnClientFormLayout = new javax.swing.GroupLayout(pnClientForm);
        pnClientForm.setLayout(pnClientFormLayout);
        pnClientFormLayout.setHorizontalGroup(
            pnClientFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnClientFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnClientFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCompanyName1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnClientFormLayout.createSequentialGroup()
                        .addGroup(pnClientFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCompanyPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnClientFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblWhatsPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputClientWhatsApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                .addComponent(btnSaveClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnClientFormLayout.setVerticalGroup(
            pnClientFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnClientFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCompanyName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputClientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnClientFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnClientFormLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnSaveClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnClientFormLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnClientFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCompanyPhone1)
                            .addComponent(lblWhatsPhone1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnClientFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputClientWhatsApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnBannerMessage.setBackground(new java.awt.Color(255, 255, 204));
        pnBannerMessage.setForeground(new java.awt.Color(156, 52, 52));

        lblBannerMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBannerMessage.setForeground(new java.awt.Color(102, 102, 255));
        lblBannerMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBannerMessage.setText("Banner de Mensagem");

        javax.swing.GroupLayout pnBannerMessageLayout = new javax.swing.GroupLayout(pnBannerMessage);
        pnBannerMessage.setLayout(pnBannerMessageLayout);
        pnBannerMessageLayout.setHorizontalGroup(
            pnBannerMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBannerMessageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBannerMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnBannerMessageLayout.setVerticalGroup(
            pnBannerMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBannerMessageLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(lblBannerMessage)
                .addGap(26, 26, 26))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnBannerMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnClientForm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnContactsList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnClientData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnMessages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnBannerMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnClientData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnClientForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnContactsList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
        
        lblContactNumber.setVisible(true);
        lblContactname.setVisible(true);
        inputContactName.setVisible(true);
        lblDate.setVisible(true);
        inputDate.setVisible(true);
        lblHour.setVisible(true);
        inputHora.setVisible(true);
        btnAddToMessage.setVisible(true);
        
        lblDoctor.setVisible(true);
        inputDoctor.setVisible(true);
    }//GEN-LAST:event_rbSingleContactActionPerformed

    private void rbMultiContactsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMultiContactsActionPerformed
        inputSingleContact.setVisible(false);
        rbSingleContact.setSelected(false);
        inputMessageArea.enable(false);
        inputMultiContacts.setVisible(true);
        btnInputSearch.setVisible(true);
        
        lblContactNumber.setVisible(false);
        lblContactname.setVisible(false);
        inputContactName.setVisible(false);
        lblDate.setVisible(false);
        inputDate.setVisible(false);
        lblHour.setVisible(false);
        inputHora.setVisible(false);
        btnAddToMessage.setVisible(false);
        
        lblDoctor.setVisible(false);
        inputDoctor.setVisible(false);
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

    private void btnSaveClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveClientActionPerformed
        if ((getInputClientName().isBlank()) || (getInputClientPhone().isBlank()) || (getInputClientWhatsApp().isBlank())){
            //systemLog.generateMessageLog("Erro", LocaleHelper.COMPANY_DATA_MISSING, null, evt.getActionCommand());
            setBannerMessage(LocaleHelper.CLIENT_DATA_MISSING, BannerType.WARNING);
        }else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
            EntityManager em = emf.createEntityManager();

            setBannerMessage("Aguarde Dados do Cliente sendo salvos.", BannerType.INFO);
            
            em.getTransaction().begin();
            Client client = new Client(null, getInputClientName(), getInputClientPhone(), getInputClientWhatsApp());
            em.persist(client);
            em.getTransaction().commit();
            setBannerMessage("Dados do Cliente Salvos com Sucesso", BannerType.SUCCESS);
            
            try {
                TimeUnit.SECONDS.sleep(5);
                closeBannerMessage();
                pnClientData.setVisible(true);
                pnClientForm.setVisible(false);
                
                loadClientData(em);
                loadMessageWithClientData(em);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            em.close();
            emf.close();
        }
    }//GEN-LAST:event_btnSaveClientActionPerformed

    private void btnAddToMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToMessageActionPerformed
        messageBuilder = new MessageBuilder();
        Message message = new Message();
        message.setBodyMessage(inputMessageArea.getText());
        
        Contact contact = new Contact(
            inputContactName.getText(), 
            inputSingleContact.getText(),
            inputDate.getText(),
            inputHora.getText() );
        
        Appointment appointment = new Appointment(contact, inputDate.getText(), inputHora.getText(), inputDoctor.getText());
        String messageReloaded = messageBuilder.AddContactToMessage(message, appointment);
        
        System.out.println("Message \n" + messageReloaded);
        inputMessageArea.setText(messageReloaded);
    }//GEN-LAST:event_btnAddToMessageActionPerformed

    public void loadMessageWithClientData(EntityManager manager){
        manager.getTransaction().begin();
        Client client  = manager.find(Client.class, 1);
        manager.getTransaction().commit();
        messageBuilder = new MessageBuilder();
        inputMessageArea.setText(messageBuilder.addClientDataToBodyMessage(client));
    }
    
    public void loadClientData(EntityManager em){
        em.getTransaction().begin();
        Client client  = em.find(Client.class, 1);
        em.getTransaction().commit();
        
        lblClientName.setText(client.getClientName());
        lblPhoneNumber.setText(client.getPhoneNumber());
        lblWhatsapp.setText(client.getWhatsApp());
    }
    
    public String getInputClientName() {
        return inputClientName.getText();
    }

    public String getInputClientWhatsApp() {
        return this.inputClientWhatsApp.getText();
    }

    public String getInputClientPhone() {
        return this.inputClientPhone.getText();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.application.swing.custom_button.SwingCustomButton btnAddToMessage;
    private whatsender.application.swing.custom_button.SwingCustomButton btnInputSearch;
    private whatsender.application.swing.custom_button.SwingCustomButton btnSaveClient;
    private whatsender.application.swing.input.TextField inputClientName;
    private whatsender.application.swing.input.FormatedTextField inputClientPhone;
    private whatsender.application.swing.input.FormatedTextField inputClientWhatsApp;
    private whatsender.application.swing.input.TextField inputContactName;
    private whatsender.application.swing.input.FormatedTextField inputDate;
    private whatsender.application.swing.input.TextField inputDoctor;
    private whatsender.application.swing.input.FormatedTextField inputHora;
    private whatsender.application.swing.input.TextArea inputMessageArea;
    private whatsender.application.swing.input.TextField inputMultiContacts;
    private whatsender.application.swing.input.FormatedTextField inputSingleContact;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBannerMessage;
    private javax.swing.JLabel lblClientName;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblCompanyName1;
    private javax.swing.JLabel lblCompanyPhone;
    private javax.swing.JLabel lblCompanyPhone1;
    private javax.swing.JLabel lblContactNumber;
    private javax.swing.JLabel lblContactname;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblHour;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblWhatsPhone;
    private javax.swing.JLabel lblWhatsPhone1;
    private javax.swing.JLabel lblWhatsapp;
    private javax.swing.JPanel pnBannerMessage;
    private javax.swing.JPanel pnClientData;
    private javax.swing.JPanel pnClientForm;
    private javax.swing.JPanel pnContactsList;
    private javax.swing.JPanel pnMessages;
    private whatsender.application.swing.JRadioButtonCustom rbMultiContacts;
    private whatsender.application.swing.JRadioButtonCustom rbSingleContact;
    private whatsender.application.swing.custom_button.SwingCustomButton swingCustomButton1;
    // End of variables declaration//GEN-END:variables
}
