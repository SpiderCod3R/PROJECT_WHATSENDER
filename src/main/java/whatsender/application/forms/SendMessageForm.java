package whatsender.application.forms;

import com.opencsv.bean.CsvToBeanBuilder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.openqa.selenium.NoSuchWindowException;

import whatsender.bot.driver.WhatsAppDriver;
import whatsender.application.entities.Consulta;
import whatsender.application.helpers.LocaleHelper;
import whatsender.application.entities.Cliente;
import whatsender.application.entities.Contato;
import whatsender.application.entities.Message;
import whatsender.application.entities.LogMessage;
import whatsender.application.entities.Pacote;
import whatsender.application.entities.PacoteContratado;
import whatsender.system.filechooser.JnaFileChooser;
import whatsender.application.helpers.BannerType;
import whatsender.application.helpers.FormatterHelper;
import whatsender.application.logs.LogType;
import whatsender.application.helpers.MessageBuilder;
import whatsender.application.helpers.MessageType;
import whatsender.application.start.Application;
import whatsender.gui.modal.MensagemModal;
import whatsender.gui.modal.MessageConfirmationForm;
import whatsender.gui.modal.popup.GlassPanePopup;



/**
 *
 * @author ALEXANDRE
 */
public class SendMessageForm extends javax.swing.JPanel {
    private MessageBuilder messageBuilder;
    private Message message;
    private Application app;
    private Boolean messageChanged = false;
    private Contato contato;
    private Cliente client;
    private Consulta consulta;
    private static WhatsAppDriver WHATSAPP = null;
    private static MessageConfirmationForm obj;
    private PacoteContratado pacoteContratado;
    private Integer mensagensDisponiveis;
    private Integer mensagensEnviadas;
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
    private EntityManager em;
    
    List<Contato> lstContatos = new ArrayList<>();
    
    private void setBannerMessage(String message, BannerType bannerType){
        if(!pnBannerMessage.isVisible()){
            pnBannerMessage.setVisible(true);
        }
        
        lblBannerMessage.setText(message);
        
        if(bannerType.getValue() == "info"){
            pnBannerMessage.setBackground(new Color(60,136,162));
            lblBannerMessage.setForeground(Color.WHITE);
        }
        
        if(bannerType.getValue() == "sucesso"){
            pnBannerMessage.setBackground(new Color(141,245,208));
            lblBannerMessage.setForeground(Color.BLACK);
        }
        
        if(bannerType.getValue() == "erro"){
            pnBannerMessage.setBackground(new Color(245,73,85));
            lblBannerMessage.setForeground(Color.WHITE);
        }
        
        if(bannerType.getValue() == "aviso"){
            pnBannerMessage.setBackground(new Color(237,226,38,93));
            lblBannerMessage.setForeground(new Color(184,144,70));
        }
    }
    
    private void closeBannerMessage(){
        pnBannerMessage.setVisible(false);
    }
    
    public SendMessageForm(WhatsAppDriver whatsappDriver) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        EntityManager em = emf.createEntityManager();
        
        initComponents();
        setOpaque(false);
        
        WHATSAPP = whatsappDriver;

        pnContactsList.setVisible(false);
        btnSendMessage.setVisible(false);
        pnBannerMessage.setVisible(false);
        inputMessageArea.setEnabled(false);
        
        /*
        INICIANDO TRANSAÇÃO PARA LOCALIZAR ITENS OBRIGATORIOS
        */
        em.getTransaction().begin();
        
        //LOCALIZAR O MENSAGEM PADRAO NO DATABASE
        message = em.find(Message.class, 1);
        
        //LOCALIZAR O CLIENTE LOCAL NO DATABASE
        client = em.find(Cliente.class, 1);
        
        em.getTransaction().commit();
        
        /*
        FINAL DA TRANSAÇÃO PARA LOCALIZAR ITENS OBRIGATORIOS
        */
        
        if(client==null) {        
            setBannerMessage(LocaleHelper.CLIENT_DATA, BannerType.WARNING);
            
            pnClientData.setVisible(false);
            pnClientForm.setVisible(true);
             
            inputClientName.setText("GLOBALNETSIS");
            inputClientPhone.setText("(22)99999-9999");
            inputClientWhatsApp.setText("(22)99999-9999");
        } else {
            pnClientData.setVisible(true);
            pnClientForm.setVisible(false);
            loadClientData(em);
            
            rbSingleContact.setSelected(true);
            rbMultiContacts.setSelected(false);
            
            btnInputSearch.setVisible(false);
            inputMultiContacts.setVisible(false);
            
            pnContactsList.setVisible(true);
            
            if(rbSingleContact.isSelected()){
                if(contato != null){
                    btnSendMessage.setVisible(true);
                }
            }
        }
        
        //LOCALIZAR A MENSAGEM PADRÃO NO DATABASE
        if (message==null){
            em.getTransaction().begin();
            messageBuilder = new MessageBuilder();
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
        inputSingleContact.setText("(22)996072173");
        inputDate.setText("14/12/2023");
        inputDoctor.setText("Luciano Borgia");
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
        rbSingleContact = new whatsender.gui.swing.JRadioButtonCustom();
        rbMultiContacts = new whatsender.gui.swing.JRadioButtonCustom();
        inputSingleContact = new whatsender.gui.swing.input.FormatedTextField();
        btnInputSearch = new whatsender.gui.custom_button.CustomButton();
        inputMultiContacts = new whatsender.gui.swing.input.TextField();
        inputContactName = new whatsender.gui.swing.input.TextField();
        lblContactname = new javax.swing.JLabel();
        inputDate = new whatsender.gui.swing.input.FormatedTextField();
        lblDate = new javax.swing.JLabel();
        inputHora = new whatsender.gui.swing.input.FormatedTextField();
        lblHour = new javax.swing.JLabel();
        lblContactNumber = new javax.swing.JLabel();
        btnAddToMessage = new whatsender.gui.custom_button.CustomButton();
        lblDoctor = new javax.swing.JLabel();
        inputDoctor = new whatsender.gui.swing.input.TextField();
        pnMessages = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputMessageArea = new whatsender.gui.swing.input.TextArea();
        btnSendMessage = new whatsender.gui.custom_button.CustomButton();
        pnClientForm = new javax.swing.JPanel();
        lblCompanyName1 = new javax.swing.JLabel();
        lblCompanyPhone1 = new javax.swing.JLabel();
        lblWhatsPhone1 = new javax.swing.JLabel();
        inputClientName = new whatsender.gui.swing.input.TextField();
        inputClientPhone = new whatsender.gui.swing.input.FormatedTextField();
        inputClientWhatsApp = new whatsender.gui.swing.input.FormatedTextField();
        btnSaveClient = new whatsender.gui.custom_button.CustomButton();
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
                            .addComponent(lblContactNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnContactsListLayout.createSequentialGroup()
                                .addComponent(inputSingleContact, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputContactName, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblContactname, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnContactsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnContactsListLayout.createSequentialGroup()
                                        .addComponent(rbSingleContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(68, 68, 68)
                                        .addComponent(rbMultiContacts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnAddToMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

        inputMessageArea.setColumns(20);
        inputMessageArea.setLineWrap(true);
        inputMessageArea.setRows(5);
        inputMessageArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(inputMessageArea);

        btnSendMessage.setBackground(new java.awt.Color(1, 155, 155));
        btnSendMessage.setForeground(new java.awt.Color(228, 255, 254));
        btnSendMessage.setText("Enviar Mensagem");
        btnSendMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMessageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMessagesLayout = new javax.swing.GroupLayout(pnMessages);
        pnMessages.setLayout(pnMessagesLayout);
        pnMessagesLayout.setHorizontalGroup(
            pnMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMessagesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                    .addGroup(pnMessagesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnMessagesLayout.setVerticalGroup(
            pnMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMessagesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        pnBannerMessage.setVisible(false);
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
        btnSendMessage.setVisible(false);
        
        lblDoctor.setVisible(true);
        inputDoctor.setVisible(true); 
    }//GEN-LAST:event_rbSingleContactActionPerformed

    private void rbMultiContactsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMultiContactsActionPerformed
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
        EntityManager em = emf.createEntityManager();

        pnBannerMessage.setVisible(false);
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
        
        messageBuilder = new MessageBuilder();
        
        loadMessageWithClientData(em);
        //inputMessageArea.setText(messageBuilder.addClientDataToBodyMessage(message.getBodyMessage(), client)); 
        
        if(lstContatos.size() != 0){
            btnSendMessage.setVisible(true);
        }else{
            btnSendMessage.setVisible(false);
        }
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
                lstContatos = new CsvToBeanBuilder(new FileReader(fileName, StandardCharsets.UTF_8))
                        .withSkipLines(1)  
                        .withType(Contato.class)
                        .build()
                        .parse();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SendMessageForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SendMessageForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            lstContatos.forEach(System.out::println);
        }
        
        if(lstContatos.size() != 0){
            btnSendMessage.setVisible(true);
        }else{
            btnSendMessage.setVisible(false);
        }
        
        
        
    }//GEN-LAST:event_btnInputSearchActionPerformed

    private void btnSaveClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveClientActionPerformed
        if ((getInputClientName().isBlank()) || (getInputClientPhone().isBlank()) || (getInputClientWhatsApp().isBlank())){
            //systemLog.generateMessageLog("Erro", LocaleHelper.COMPANY_DATA_MISSING, null, evt.getActionCommand());
            setBannerMessage(LocaleHelper.CLIENT_DATA_MISSING, BannerType.WARNING);
        }else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("whatsender-jpa");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            
            Cliente client = new Cliente(null, getInputClientName(), getInputClientPhone(), getInputClientWhatsApp());
            em.persist(client);
            em.getTransaction().commit();
            setBannerMessage("Dados do Cliente Salvos com Sucesso", BannerType.SUCCESS);
            
            loadClientData(em);
            loadMessageWithClientData(em);
            
            pnClientData.setVisible(true);
            pnClientForm.setVisible(false);
            
            rbSingleContact.setSelected(true);
            rbMultiContacts.setSelected(false);
            
            btnInputSearch.setVisible(false);
            inputMultiContacts.setVisible(false);
            
            pnContactsList.setVisible(true);
            
            if(contato != null){
                btnSendMessage.setVisible(true);
            }else{
                btnSendMessage.setVisible(false);
            }
                
            em.close();
            emf.close();
        }
    }//GEN-LAST:event_btnSaveClientActionPerformed

    private void btnAddToMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToMessageActionPerformed
        messageBuilder = new MessageBuilder();
        
        contato = new Contato(
            inputContactName.getText(), 
            inputSingleContact.getText(),
            inputDate.getText(),
            inputHora.getText() );
        
        consulta = new Consulta(
            contato.getName(), 
            contato.getWhatsNumber(), 
            contato.getData(), 
            contato.getHour(), 
            inputDoctor.getText());
        
        String messageReloaded;
        messageChanged = true;
        
        try {
            messageReloaded = messageBuilder.AddContactToMessage(inputMessageArea.getText(), consulta);
            setBannerMessage(LocaleHelper.CLIENT_DATA_ADDED_TO_MESSAGE, BannerType.INFO);
            inputMessageArea.setText(messageReloaded);  
            btnSendMessage.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(SendMessageForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnAddToMessage.setVisible(false);
    }//GEN-LAST:event_btnAddToMessageActionPerformed

    private void inicilizaEntityManagerFactory(){
        if(!emf.isOpen()){
            emf = Persistence.createEntityManagerFactory("whatsender-jpa");
            em = emf.createEntityManager();
        }else{
            em = emf.createEntityManager();
        }
    }
    
    private void preparaMensagemParaEnvio(){
        message = new Message();
        messageBuilder = new MessageBuilder();
        message.setBodyMessage(inputMessageArea.getText().replace("\n", " ").replace("\r", " "));
    }
    
    private void enviarMensagemUnica(){
        inicilizaEntityManagerFactory();
        preparaMensagemParaEnvio();
        
        try {
            WHATSAPP.waitForConnection();

            if(WHATSAPP.is_connected()){

                WHATSAPP.abrir_conversa_com_contato(FormatterHelper.formatPhoneNumber(consulta.getContactPhone()));
                WHATSAPP.sendMsg(message.getBodyMessage());

                if(consulta.getId() == null){
                    em.getTransaction().begin();
                    em.persist(consulta);
                    em.getTransaction().commit();
                }else{
                    consulta = em.find(Consulta.class, consulta.getId());
                }

                LogMessage messageLog = new LogMessage(
                        null, 
                        message.getBodyMessage(), 
                        consulta, 
                        LogType.SUCCESS, 
                        MessageType.NORMAL );

                em.getTransaction().begin();
                em.persist(messageLog);
                em.getTransaction().commit();
                
                
                /***
                 * APOS ENVIAR A MENSAGEM DEVE SER FEITA UMA ATUALIZAÇÃO NO 
                 * PACOTE CONTRATADO NO CAMPO DE MENSAGENS DISPONIVEIS
                 * 
                 * DEVE-SE DECREMENTAR UMA UNIDADE
                 * .
                 */
                
                this.pacoteContratado = this.em.find(PacoteContratado.class, 1);
                mensagensDisponiveis = this.pacoteContratado.getMensagensDisponiveis();
                mensagensEnviadas = this.pacoteContratado.getMensagensEnviadas();
                this.pacoteContratado.setMensagensDisponiveis(mensagensDisponiveis - 1);
                this.pacoteContratado.setMensagensEnviadas(mensagensEnviadas + 1);
                
                if(this.pacoteContratado.getTipoPacote() == "CONTRATO AVULSO"){
            
                }
                
                if(emf.isOpen()){
                    em.close();
                    emf.close();
                }
                
            }
            
            setBannerMessage(LocaleHelper.MESSAGE_SENDED, BannerType.SUCCESS);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Erro De Time Out");
        }catch (NoSuchWindowException e){
            System.err.println("Janela do Chrome Controlada pela Aplicação foi Fechada");
        }  
    }
    
    public void enviarVariasMensagens(){
        inicilizaEntityManagerFactory();
        preparaMensagemParaEnvio();
        
        
        for (Contato contato : this.lstContatos){
            try {
                WHATSAPP.waitForConnection();
                if(WHATSAPP.is_connected()){
                    if(contato != null) {
                        String messageEmLote = messageBuilder.
                            AddMessage(
                                    inputMessageArea.getText(), 
                                    contato).
                            replace("\n", " ").
                            replace("\r", " ");

                        WHATSAPP.abrir_conversa_com_contato(FormatterHelper.formatPhoneNumber(contato.getWhatsNumber()));
                        WHATSAPP.sendMsg(messageEmLote);

                        consulta = new Consulta(
                            contato.getName(), 
                            contato.getWhatsNumber(), 
                            contato.getData(), 
                            contato.getHour(), 
                            contato.getDoctor());

                        em.getTransaction().begin();
                        em.persist(consulta);
                        em.getTransaction().commit();

                        LogMessage messageLog = new LogMessage(
                                null, 
                                messageEmLote, 
                                consulta, 
                                LogType.SUCCESS, 
                                MessageType.LOTE );

                        em.getTransaction().begin();
                        em.persist(messageLog);
                        em.getTransaction().commit();
                    } 
                }
            } catch (org.openqa.selenium.TimeoutException e) {
                System.out.println("Erro De Time Out");
            }catch (NoSuchWindowException e){
                System.err.println("Janela do Chrome Controlada pela Aplicação foi Fechada");
            } catch (ParseException ex) {
                Logger.getLogger(SendMessageForm.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        /**
         * CASO A CONEXAO ESTIVER ABERTA ELE FECHARÁ A FACTORY
         */
        if(emf.isOpen()){
            em.close();
            emf.close();
        }
    }
    
    private void btnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessageActionPerformed
        /***
         * VERIFICAR PRIMEIRAMENTE O PACOTE CONTRATADO
         */
        inicilizaEntityManagerFactory();
        this.pacoteContratado = this.em.find(PacoteContratado.class, 1);
        Date data_do_dia = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        /***
         * VERIFICAR QUANTIDADE DE MENSAGENS DISPONÍVEIS
         * {
         *  CASO 1: SOMENTE PODE ENVIAR MENSAGENS SE TIVER MENSAGENS DISPONIVEIS
         *          MAIOR OU IGUAL A 1 MENSAGEM
         * 
         *  CASO 2: NÃO PODE ENVIAR CASO TENHO ZERO MENSAGENS DISPONIVEIS 
         *          PARA ESSE CASO DEVE-SE PEDIR PARA ENTRAR EM CONTATO COM O
         *          FORNECEDOR DO APLICATIVO.
         * }
         */
        if(this.pacoteContratado != null){
            if(this.pacoteContratado.getDt_expiracao_contrato().equals(dateFormat.format(data_do_dia) )) {
                MensagemModal mensagemModal = exibirMensagemPacoteExpirado();
                setTimeOut(() -> GlassPanePopup.showPopup(mensagemModal), 1500);
            }else{
                if( (this.pacoteContratado.getMensagensDisponiveis() >=1) ){
                    if(rbSingleContact.isSelected()){
                        obj = new MessageConfirmationForm("Atenção!", "Deseja enviar esta mensagem?");
                    } else{
                        if (rbMultiContacts.isSelected()){
                            obj = new MessageConfirmationForm("Atenção!", "Deseja enviar estas mensagens?");
                        }
                    }
                    GlassPanePopup.showPopup(obj); 
                    obj.eventOK(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            if(rbSingleContact.isSelected()){
                                MensagemModal mensagemModal = showProgressoMensagemModal();
                                setTimeOut(() -> GlassPanePopup.showPopup(mensagemModal), 1500);
                                GlassPanePopup.closePopupLast();
                                setTimeOut(() -> enviarMensagemUnica(), 3000);
                            }
                            if (rbMultiContacts.isSelected()){
                                MensagemModal mensagemModal = showProgressoMensagemModal();
                                setTimeOut(() -> GlassPanePopup.showPopup(mensagemModal), 1500);
                                GlassPanePopup.closePopupLast();
                                setTimeOut(() -> enviarVariasMensagens(), 3000);
                            }
                        }
                    });
                } else {
                    MensagemModal mensagemModal = exibirMensagemPacoteExpirado();
                    setTimeOut(() -> GlassPanePopup.showPopup(mensagemModal), 1500);
                }
            }
            
        }
 
    }//GEN-LAST:event_btnSendMessageActionPerformed

    public void loadMessageWithClientData(EntityManager manager){
        manager.getTransaction().begin();
        client  = manager.find(Cliente.class, 1);  
        message = manager.find(Message.class, 1);
        manager.getTransaction().commit();

        messageBuilder = new MessageBuilder();
        inputMessageArea.setText(messageBuilder.addClientDataToBodyMessage(message.getBodyMessage(), client));
    }
    
    public void loadClientData(EntityManager em){
        em.getTransaction().begin();
        Cliente client  = em.find(Cliente.class, 1);
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
    
    private MensagemModal showProgressoMensagemModal(){
        MensagemModal mensagemModal = new MensagemModal();
        mensagemModal.setTitle("Aviso!");
        mensagemModal.setMenssagem(
                "Aguarde enquanto a "
                + "aplicação envia as mensagens.");
        mensagemModal.ferramenta_de_enviar_mensagem_executando(true);
        mensagemModal.loadProgressBar();
        return mensagemModal;
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.gui.custom_button.CustomButton btnAddToMessage;
    private whatsender.gui.custom_button.CustomButton btnInputSearch;
    private whatsender.gui.custom_button.CustomButton btnSaveClient;
    private whatsender.gui.custom_button.CustomButton btnSendMessage;
    private whatsender.gui.swing.input.TextField inputClientName;
    private whatsender.gui.swing.input.FormatedTextField inputClientPhone;
    private whatsender.gui.swing.input.FormatedTextField inputClientWhatsApp;
    private whatsender.gui.swing.input.TextField inputContactName;
    private whatsender.gui.swing.input.FormatedTextField inputDate;
    private whatsender.gui.swing.input.TextField inputDoctor;
    private whatsender.gui.swing.input.FormatedTextField inputHora;
    private whatsender.gui.swing.input.TextArea inputMessageArea;
    private whatsender.gui.swing.input.TextField inputMultiContacts;
    private whatsender.gui.swing.input.FormatedTextField inputSingleContact;
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
    private whatsender.gui.swing.JRadioButtonCustom rbMultiContacts;
    private whatsender.gui.swing.JRadioButtonCustom rbSingleContact;
    // End of variables declaration//GEN-END:variables

}
