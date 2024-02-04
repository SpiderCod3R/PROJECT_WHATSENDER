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

/**
 *
 * @author ALEXANDRE (THE GRAND MASTER)
 */
public class Application extends javax.swing.JFrame {
    private MigLayout layout;
    private MenuLateral menuLateral;
    private Header header;
    private MainForm mainForm;
    private Animator animator;
    private WhatsAppDriver WHATSAPP = null;
    
    public Application(WhatsAppDriver whatsapp) {
        initComponents();
        GlassPanePopup.install(this);
        __init__(whatsapp);
    }
    
    public void __init__(WhatsAppDriver whatsAppDriver){
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        WHATSAPP = whatsAppDriver;
        
        menuLateral = new MenuLateral(WHATSAPP);
        header = new Header();
        mainForm = new MainForm();
        
        menuLateral.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuLateralIndex, int subMenuIndex) {
                System.out.println("Menu index : " + menuLateralIndex + " SubMenu index " + subMenuIndex);
                
                switch (menuLateralIndex) {
                    case 0:
                        if(subMenuIndex==-1){
                            mainForm.showForm(new FormHome(whatsAppDriver));
                        }
                        break;
                    case 1:
                        if(subMenuIndex==-1){
                            mainForm.showForm(new SendMessageForm(whatsAppDriver));
                        }
                        break;
                    case 2:
                        if(subMenuIndex==0){
                           MessageConfirmationForm obj = new MessageConfirmationForm("Aviso!", "Deseja entrar nas configurações do Cliente?");
                            obj.eventOK(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    mainForm.showForm(new ClientForm());
                                    //dispose();
                                }
                            });
                            GlassPanePopup.showPopup(obj); 
                            
                        }
                        if(subMenuIndex==1){
                            
                            MessageConfirmationForm obj = new MessageConfirmationForm("Aviso!", "Deseja Fechar a aplicação e entrar nas configurações de Pacote?");
                            obj.eventOK(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
//                                    mainForm.showForm(new FormHome(whatsAppDriver));
//                                    DefinirPacote definirPacote = new DefinirPacote();
//                                    definirPacote.setVisible(true);
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
                           WHATSAPP.quit();
                           System.exit(0);
                        }
                        break;
                }
            }
        });
        
        menuLateral.addEventShowPopUpMenu(new EventShowPopUpMenu() {
            @Override
            public void showPopUp(Component component) {
                MenuItem menuLateralItem = (MenuItem)component;
                PopUpMenu popUp = new PopUpMenu(Application.this, menuLateralItem.getIndex(), menuLateralItem.getEventMenuSelected(), menuLateralItem.getMenu().getSubMenu());
                int x = Application.this.getX() + 52;
                int y = Application.this.getY() + component.getY() + 86;
                popUp.setLocation(x, y);
                popUp.setVisible(true);
            }
        });
        menuLateral.initMenuItem();
        
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
        mainForm.showForm(new FormHome(WHATSAPP));
        //mainForm.showForm(new SendMessageForm(mainForm, whatsapp));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

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
            .addGap(0, 1168, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
        );

        jMenu2.setText("Configurações");

        jMenuItem1.setText("Editar Cliente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Opções");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

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
        WHATSAPP.quit();
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public void backForm(Component component, Integer indexMenu, Integer oldForm) {
       mainForm = new MainForm();
        if( (component == null)){
            mainForm.showForm(new FormHome(WHATSAPP));
            //cardMenu1.selectMenu(indexMenu, oldForm);
        } else {
            mainForm.showForm(component);
            //cardMenu1.selectMenu(indexMenu, oldForm);   
        }  
    }
    
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        WhatsAppDriver WHATSAPP = new WhatsAppDriver(Browser.CHROME);
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Application(WHATSAPP).setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
