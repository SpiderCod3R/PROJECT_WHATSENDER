package whatsender.application.start;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import whatsender.application.configuracoes.DefinirPacoteForm;
import whatsender.application.configuracoes.EditorDePacoteForm;
import whatsender.application.entities.Cliente;

import whatsender.bot.driver.WhatsAppDriver;
import whatsender.gui.component.header.Header;
import whatsender.gui.component.menu.MenuLateral;
import whatsender.gui.component.menu.event_menu.EventMenuSelected;
import whatsender.gui.component.menu.event_menu.EventShowPopUpMenu;
import whatsender.application.forms.ClientForm;
import whatsender.application.forms.FormHome;
import whatsender.application.forms.MainForm;
import whatsender.application.forms.SendMessageForm;
import whatsender.bot.driver.Browser;
import whatsender.gui.login.LoginScreen;
import whatsender.gui.modal.MessageConfirmationForm;
import whatsender.gui.swing.MenuItem;
import whatsender.gui.swing.PopUpMenu;

import whatsender.gui.modal.popup.GlassPanePopup;
import whatsender.gui.splashscreen.SplashScreen;

/**
 *
 * @author ALEXANDRE (THE GRAND MASTER)
 */
public class Configuracoes extends javax.swing.JFrame {
    private MigLayout layout;
    private MenuLateral menuLateral;
    private Header header;
    private MainForm mainForm;
    private Animator animator;
    
    public Configuracoes() {
        initComponents();
        GlassPanePopup.install(this);
        __init__();
    }
    
    public void __init__(){
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        
        menuLateral = new MenuLateral();
        header = new Header();
        mainForm = new MainForm();
        
        menuLateral.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuLateralIndex, int subMenuIndex) {
                System.out.println("Menu index : " + menuLateralIndex + " SubMenu index " + subMenuIndex);
                
                switch (menuLateralIndex) {
                    case 0:
                        if(subMenuIndex==0){
                            mainForm.showForm(new DefinirPacoteForm());
                        }
                        
                        if(subMenuIndex==1){
                            mainForm.showForm(new EditorDePacoteForm(1));
                        }
                                               
                        if(subMenuIndex==2){
                            mainForm.showForm(new EditorDePacoteForm(2));
                        }
                        
                        if(subMenuIndex==3){
                            mainForm.showForm(new EditorDePacoteForm(3));
                        }
                        
                        if(subMenuIndex==4){
                            mainForm.showForm(new EditorDePacoteForm(4));
                        }
                        
                        break;
                    case 1:
                        if(subMenuIndex==-1){
                            mainForm.showForm(new ClientForm());
                        }
                        break;
                    case 2:
                        if(subMenuIndex==-1){
                            
                            MessageConfirmationForm obj = new MessageConfirmationForm("Aviso!", "Deseja Fechar a aplicação e entrar nas configurações de Pacote?");
                            obj.eventOK(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    LoginScreen telaLogin = new LoginScreen(new javax.swing.JFrame(), true);
                                    telaLogin.setVisible(true);
                                    dispose();
                                }
                            });
                            GlassPanePopup.showPopup(obj); 
                        }
                        break;
                    case 3:
                        if(subMenuIndex==-1){
                            dispose();
                            Thread.interrupted();
                            new SplashScreen(null, true).setVisible(true);
                        }
                        break;
                }
            }
        });
        
        menuLateral.addEventShowPopUpMenu(new EventShowPopUpMenu() {
            @Override
            public void showPopUp(Component component) {
                MenuItem menuLateralItem = (MenuItem)component;
                PopUpMenu popUp = new PopUpMenu(Configuracoes.this, menuLateralItem.getIndex(), menuLateralItem.getEventMenuSelected(), menuLateralItem.getMenu().getSubMenu());
                int x = Configuracoes.this.getX() + 52;
                int y = Configuracoes.this.getY() + component.getY() + 86;
                popUp.setLocation(x, y);
                popUp.setVisible(true);
            }
        });
        menuLateral.initMenuConfiguracoes();
        
        bg.add(menuLateral, "w 230!, spany 2");
        bg.add(header, "h 50!, wrap");
        bg.add(mainForm, "w 100%, h 100%");
        
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double width;
                if(menuLateral.isShowMenu()){
                    width=60 + (170 * (1f - fraction));
                } else {
                    width=60 + (170 * fraction);
                }
                layout.setComponentConstraints(menuLateral, "w " + width + "!, spany2");
                menuLateral.revalidate();
            }

            @Override
            public void end() {
                menuLateral.setShowMenu(!menuLateral.isShowMenu());
                menuLateral.setEnableMenu(true);
            }
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(!animator.isRunning()){
                   animator.start();
               }
               menuLateral.setEnableMenu(false);
               
               if(menuLateral.isShowMenu()){
                   menuLateral.hideAllMenus();
               }
            }
        });
        
        // INICIALIZANDO FONTES DE ICONES DO GOOGLE
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        // Carregando a tela inicial do APP
        mainForm.showForm(new DefinirPacoteForm());
        //mainForm.showForm(new SendMessageForm(mainForm, whatsapp));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //WHATSAPP.quit();
    }//GEN-LAST:event_formWindowClosed

    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Configuracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Configuracoes().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
