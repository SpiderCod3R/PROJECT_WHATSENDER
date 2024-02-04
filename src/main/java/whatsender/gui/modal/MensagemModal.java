package whatsender.gui.modal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.persistence.Persistence;
import whatsender.gui.modal.popup.GlassPanePopup;
import whatsender.application.entities.Pacote;

/**
 *
 * @author ALEXANDRE
 */
public class MensagemModal extends javax.swing.JPanel {

    private String messageBox = "Pacote [PACOTE] \n"
        + "Indicado para [INDICADO]\n"
        + "No Valor de [VALOR] \n"
        + "Contratado com Sucesso.";

    private String title = "";
    private String progressMessage = "";
    private boolean ferramenta_de_enviar_mensagem_sendo_executada = false;
    
    public MensagemModal() {
        initComponents();
        setOpaque(false);
        txtPacote.setBackground(new Color(0, 0, 0, 0));
        txtPacote.setSelectionColor(new Color(48, 170, 63, 200));
        txtPacote.setOpaque(false);
        
    }
    
    public void ferramenta_de_enviar_mensagem_executando(Boolean value){
        this.ferramenta_de_enviar_mensagem_sendo_executada = value;
    }
    
    public void setProgressMessage(String progressMessage){
        this.progressMessage = progressMessage;
        lblProgressMessage.setText(this.progressMessage);
    }
    
    public void setMenssagem(String mensagem) {
        this.messageBox = mensagem;
        txtPacote.setText(this.messageBox);
    }
    
    public void setMenssagemContrato(Pacote pacote) {
        this.messageBox = messageBox.
            replace("[PACOTE]", pacote.getPacoteNome()).
            replace("[INDICADO]", pacote.getPacoteDescricao()).
            replace("[VALOR]", "R$: " + pacote.getValor());
        
        txtPacote.setText(this.messageBox);
    }

    public void loadProgressBar(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    if(ferramenta_de_enviar_mensagem_sendo_executada){
                        doTask("Verificando Conexao ...", 25);
                        doTask("Preparando mensagens ...", 50);
                        doTask("Enviando mensagens ...", 75);
                        doTask("Mensagens Enviadas .", 100);
                    } else{
                        doTask("Salvando Pacote contratado ...", 50);
                        doTask("Pacote Salvo com sucesso ...", 100);
                    }                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setTitle(String title) {
        this.title = title;
        lblTitle.setText(this.title);
    }
    
    private void doTask(String progressMessage, int progress) throws Exception {
        Thread.sleep(3000);
        progressBar1.setValue(progress);
        lblProgressMessage.setText(progressMessage);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(grphcs);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPacote = new javax.swing.JTextPane();
        lblProgressMessage = new javax.swing.JLabel();
        progressBar1 = new whatsender.application.splashscreen.ProgressBarCustom();

        setBackground(new java.awt.Color(239, 239, 239));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setText("TITULO");

        txtPacote.setText("TESTE");
        jScrollPane1.setViewportView(txtPacote);

        lblProgressMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblProgressMessage.setForeground(new java.awt.Color(34, 91, 174));
        lblProgressMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgressMessage.setText("...");

        progressBar1.setBackground(new java.awt.Color(192, 192, 192));
        progressBar1.setForeground(new java.awt.Color(67, 127, 215));
        progressBar1.setColorString(new java.awt.Color(67, 127, 215));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(lblProgressMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 80, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(progressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(progressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProgressMessage)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblProgressMessage;
    private javax.swing.JLabel lblTitle;
    private whatsender.application.splashscreen.ProgressBarCustom progressBar1;
    private javax.swing.JTextPane txtPacote;
    // End of variables declaration//GEN-END:variables
}
