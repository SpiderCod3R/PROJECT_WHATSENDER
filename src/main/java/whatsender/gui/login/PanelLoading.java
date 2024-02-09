package whatsender.gui.login;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import whatsender.application.entities.Admin;


public class PanelLoading extends javax.swing.JLayeredPane {

    private final Animator animator;
    private boolean slideLeft;
    private float animate;
    private boolean isMessage;
    private Admin admin_model;
    
    public PanelLoading() {
        initComponents();
        //setOpaque(false);
        
        loadingPanel.setVisible(true);
        configPanel.setVisible(false);
        messagePanel.setVisible(false);
        
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
                if(isMessage){
                    messagePanel.setVisible(true);
                }else{
                    //configPanel.setVisible(true);
                }
            }

            @Override
            public void timingEvent(float fraction) {
                if(isMessage){
                    messagePanel.setAlpha(fraction);
                    loadingPanel.setAlpha(1f - fraction);
                }else{
                    //configPanel.setAlpha(fraction);
                    loadingPanel.setAlpha(1f - fraction);
                }
                repaint();
            }  
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
    }
    
    public void setAnimate(boolean slideLeft, float animate) {
        this.slideLeft = slideLeft;
        this.animate = animate;
    }
    
    public void addEventBack(ActionListener event){
        btnCancelar.addActionListener(event);
        btnCancelar1.addActionListener(event);
        btnCancelar2.addActionListener(event);
    }
    
    public void addEventContinuarParaPacote(ActionListener event){
        btnContinuarParaPacote.addActionListener(event);
    }
    
    public void addEventContinuarParaDadosCliente(ActionListener event){
        btnContinuarParaDadosCliente.addActionListener(event);
    }
    
    public void doneLogin(Admin admin_model){
        isMessage = false;
        this.admin_model = admin_model;
        //configPanel.setVisible(false);
        //loadingPanel.setVisible(false);
        animator.start();
    }
    
    public void showError(String ms){
        isMessage = true;
        lblMensagem.setText(ms);
        animator.start();
    }
    
    public void reset(){
        loadingPanel.setAlpha(1f);
        loadingPanel.setVisible(true);
        //configPanel.setVisible(false);
        messagePanel.setVisible(false);
    }
    
    @Override
    public void paint(Graphics grphcs) {
        
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());

        int width = getWidth();
        int height = getHeight();
        //float x =easeOutQuint(animate) * width;
        int x =0;
        int y =0;
        
        if(slideLeft){
            int centerY = height/2;
            Path2D.Float p = new Path2D.Float();
            p.moveTo(x, y);
            p.lineTo(width, y);
            p.lineTo(width, height);
            p.lineTo(x, height);
            p.curveTo(x, height, easeOutBounce(animate)*width, centerY, x, y);
            
            g2.fill(p);
        }else {
            g2.fillRect(x, y, width, height);
        }
        g2.dispose();
        super.paint(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        configPanel = new whatsender.gui.login.PanelTransparent();
        btnCancelar = new whatsender.gui.swing.Button();
        imageAvatar1 = new whatsender.gui.login.component.ImageAvatar();
        btnContinuarParaPacote = new whatsender.gui.custom_button.CustomButton();
        btnContinuarParaDadosCliente = new whatsender.gui.custom_button.CustomButton();
        loadingPanel = new whatsender.gui.login.PanelTransparent();
        btnCancelar1 = new whatsender.gui.swing.Button();
        jLabel2 = new javax.swing.JLabel();
        messagePanel = new whatsender.gui.login.PanelTransparent();
        btnCancelar2 = new whatsender.gui.swing.Button();
        lblMensagem = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        btnCancelar.setBackground(new java.awt.Color(44, 74, 104));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        imageAvatar1.setForeground(new java.awt.Color(41, 75, 110));
        imageAvatar1.setBorderSize(3);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/config.png"))); // NOI18N
        imageAvatar1.setOpaque(true);

        btnContinuarParaPacote.setBackground(new java.awt.Color(79, 133, 196));
        btnContinuarParaPacote.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnContinuarParaPacote.setForeground(new java.awt.Color(253, 253, 253));
        btnContinuarParaPacote.setText("Pacote Contratado");
        btnContinuarParaPacote.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnContinuarParaDadosCliente.setBackground(new java.awt.Color(79, 133, 196));
        btnContinuarParaDadosCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnContinuarParaDadosCliente.setForeground(new java.awt.Color(253, 253, 253));
        btnContinuarParaDadosCliente.setText("Dados Cliente");
        btnContinuarParaDadosCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout configPanelLayout = new javax.swing.GroupLayout(configPanel);
        configPanel.setLayout(configPanelLayout);
        configPanelLayout.setHorizontalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configPanelLayout.createSequentialGroup()
                .addGroup(configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(configPanelLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(configPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnContinuarParaDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnContinuarParaPacote, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(configPanelLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        configPanelLayout.setVerticalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnContinuarParaPacote, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnContinuarParaDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        add(configPanel, "card2");

        btnCancelar1.setBackground(new java.awt.Color(44, 74, 104));
        btnCancelar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar1.setText("Cancelar");
        btnCancelar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/loading.gif"))); // NOI18N

        javax.swing.GroupLayout loadingPanelLayout = new javax.swing.GroupLayout(loadingPanel);
        loadingPanel.setLayout(loadingPanelLayout);
        loadingPanelLayout.setHorizontalGroup(
            loadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingPanelLayout.createSequentialGroup()
                .addGroup(loadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loadingPanelLayout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loadingPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        loadingPanelLayout.setVerticalGroup(
            loadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loadingPanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        add(loadingPanel, "card2");

        btnCancelar2.setBackground(new java.awt.Color(44, 74, 104));
        btnCancelar2.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar2.setText("Cancelar");
        btnCancelar2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lblMensagem.setBackground(new java.awt.Color(230, 230, 230));
        lblMensagem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMensagem.setForeground(new java.awt.Color(255, 51, 51));
        lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensagem.setText("Mensagem ...");
        lblMensagem.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        lblMensagem.setOpaque(true);

        javax.swing.GroupLayout messagePanelLayout = new javax.swing.GroupLayout(messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addGroup(messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(messagePanelLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(messagePanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        messagePanelLayout.setVerticalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, messagePanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(lblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        add(messagePanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    
    private float easeOutBounce(float x) {
        float n1 = 7.5625f;
        float d1 = 2.75f;
        double v;
        if (x < 1 / d1) {
            v=  n1 * x * x;
        } else if (x < 2 / d1) {
            v = n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            v =  n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            v= n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
        
        if(slideLeft){
            return (float)(1f - v);
        } else {
            return (float)v;
        }
    }
    
    private float easeOutQuint(float x) {
        double v =  1 - Math.pow(1 - x, 5);
        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.gui.swing.Button btnCancelar;
    private whatsender.gui.swing.Button btnCancelar1;
    private whatsender.gui.swing.Button btnCancelar2;
    private whatsender.gui.custom_button.CustomButton btnContinuarParaDadosCliente;
    private whatsender.gui.custom_button.CustomButton btnContinuarParaPacote;
    private whatsender.gui.login.PanelTransparent configPanel;
    private whatsender.gui.login.component.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblMensagem;
    private whatsender.gui.login.PanelTransparent loadingPanel;
    private whatsender.gui.login.PanelTransparent messagePanel;
    // End of variables declaration//GEN-END:variables
}
