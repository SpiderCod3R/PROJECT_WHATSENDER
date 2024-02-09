package whatsender.gui.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import org.hibernate.SessionFactory;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import whatsender.application.entities.Admin;
import whatsender.application.start.Configuracoes;
import whatsender.application.start.DefinirPacote;


public class PanelSlide extends javax.swing.JLayeredPane {
    private final Animator animator;
    private float animate = 1f;
    private boolean slideLeft;
    private final PanelLogin panelLogin;
    private final PanelLoading panelLoading;
    private EntityManagerFactory emf;
    private EntityManager em;
    
    private Thread th;
    private JFrame jFrame;
    
    private MigLayout layout;

    public void setFram(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public PanelSlide() {
        initComponents();
        Color color = new Color(67,99,132);
        layout = new MigLayout("inset 0", "[fill]", "[fill]");
        
        setPreferredSize(new Dimension(369, 511));
        setBackground(color);
        setLayout(layout);
        
        panelLogin   = new PanelLogin();
        panelLoading = new PanelLoading();
        panelLoading.setVisible(false);
        panelLoading.setBackground(color);
        
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
                if(slideLeft){
                    panelLoading.setVisible(true);
                }else {
                    panelLogin.setVisible(true);
                }
            }

            @Override
            public void timingEvent(float fraction) {
                double width = getWidth();
                animate = fraction;
                float a  = easeOutQuint(fraction);
                int x = (int)(a * width);
                panelLoading.setAnimate(slideLeft, fraction);
                layout.setComponentConstraints(panelLoading, "pos " + x + " 0 100% 100%");
                revalidate();
                repaint();
            }

            @Override
            public void end() {
                if(slideLeft){
                    panelLogin.setVisible(false);
                }else {
                    panelLoading.setVisible(false);
                    panelLoading.reset();
                }
            }
        };
        
        animator = new Animator(1000, target);
        animator.setResolution(0);
        
        add(panelLoading, " pos 0 0 0 0, w 0!");
        add(panelLogin);
        
        panelLogin.addEventLogin(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()){
                    if(panelLogin.checkLogin()){
                        showSlide(true); 
                        login(panelLogin.getUserName(), panelLogin.getPassword());
                    }
                }
            }
        });
        
        panelLoading.addEventContinuarParaPacote(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DefinirPacote telaDePacotes = new DefinirPacote();
                telaDePacotes.setVisible(true);
                jFrame.dispose();
            }
            
        });
        
        panelLoading.addEventContinuarParaDadosCliente(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                jFrame.dispose();
            }
        });
        
        panelLoading.addEventBack(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()){
                    th.interrupt();
                    showSlide(false);
                }
            }
        });
    }
    
    public void showSlide(boolean show){
        slideLeft = show;
        animator.start();
    }
    
    private void login(String userName, String password){
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    Admin loginData = new Admin(1, userName, password);
                    
                    if(loginData.check_login()){
                        panelLoading.doneLogin(loginData);
                        Thread.sleep(2000);
                        jFrame.dispose();
                        new Configuracoes().setVisible(true);
                    } else {
                        panelLoading.showError("Usuário ou senha incorreta.");
                    } 
                }catch(InterruptedException e){
                    panelLoading.showError("Erro interno da Aplicação.");
                }catch(Exception e){
                    panelLoading.showError(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        if(slideLeft == false){
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            float x =easeOutQuint(animate) * width;
            float y =0;
            int centerY = height/2;

            Path2D.Float p = new Path2D.Float();
            p.moveTo(x, y);
            p.lineTo(x, height);
            p.curveTo(x, height, easeOutBounce(animate)*width, centerY, x, y);
            g2.setColor(getBackground());
            g2.fill(p);
            g2.dispose();
        }
    } 
    
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
    // End of variables declaration//GEN-END:variables
}
