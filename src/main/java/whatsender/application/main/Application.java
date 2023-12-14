package whatsender.application.main;

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
import whatsender.application.bot.config.utilities.Browser;
import whatsender.application.bot.config.utilities.WhatsAppDriver;
import whatsender.application.component.Header;
import whatsender.application.component.Menu;
import whatsender.application.event.EventMenuSelected;
import whatsender.application.event.EventShowPopUpMenu;
import whatsender.application.forms.ClientForm;
import whatsender.application.forms.FormHome;
import whatsender.application.forms.MainForm;
import whatsender.application.forms.SendMessageForm;
import whatsender.application.forms.LogsForm;
import whatsender.application.swing.MenuItem;
import whatsender.application.swing.PopUpMenu;

/**
 *
 * @author ALEXANDRE (THE GRAND MASTER)
 */
public class Application extends javax.swing.JFrame {
    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm mainForm;
    private Animator animator;
    
    public Application() {
        initComponents();
        __init__();
    }
    
    public void __init__(){
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        mainForm = new MainForm();
        
        //WhatsAppDriver whatsapp = new WhatsAppDriver(Browser.CHROME);
        //whatsapp.open();
        
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu index : " + menuIndex + " SubMenu index " + subMenuIndex);
                
                if(menuIndex==0){
                    if(subMenuIndex==0){
                        mainForm.showForm(new FormHome());
                    }
                }
                
                switch (menuIndex) {
                    case 1:
                        if(subMenuIndex==-1){
                            mainForm.showForm(new SendMessageForm(mainForm));
                        }
                        break;
                    case 2:
                        if(subMenuIndex==0){
                            mainForm.showForm(new ClientForm());
                        }
                        break;
                    case 3:
                        if(subMenuIndex==-1){
                            mainForm.showForm(new LogsForm());
                        }
                        break;
                    case 4:
                        if(subMenuIndex==-1){
                            //whatsapp.quit();
                            System.exit(0);
                        }
                        break;
                    default:
                        System.out.println("Menu index : " + menuIndex + " SubMenu index " + subMenuIndex);
                }
            }
        });
        
        menu.addEventShowPopUpMenu(new EventShowPopUpMenu() {
            @Override
            public void showPopUp(Component component) {
                MenuItem menuItem = (MenuItem)component;
                PopUpMenu popUp = new PopUpMenu(Application.this, menuItem.getIndex(), menuItem.getEventMenuSelected(), menuItem.getMenu().getSubMenu());
                int x = Application.this.getX() + 52;
                int y = Application.this.getY() + component.getY() + 86;
                popUp.setLocation(x, y);
                popUp.setVisible(true);
            }
        });
        menu.initMenuItem();
        
        bg.add(menu, "w 230!, spany 2");
        bg.add(header, "h 50!, wrap");
        bg.add(mainForm, "w 100%, h 100%");
        
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double width;
                if(menu.isShowMenu()){
                    width=60 + (170 * (1f - fraction));
                } else {
                    width=60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
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
               menu.setEnableMenu(false);
               
               if(menu.isShowMenu()){
                   menu.hideAllMenus();
               }
            }
        });
        
        // INICIALIZANDO FONTES DE ICONES DO GOOGLE
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        // Carregando a tela inicial do APP
        //mainForm.showForm(new FormHome());
        mainForm.showForm(new SendMessageForm(mainForm));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1168, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
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


    public void backForm(Component component, Integer indexMenu, Integer oldForm) {
       mainForm = new MainForm();
        if( (component == null)){
            mainForm.showForm(new FormHome());
            //cardMenu1.selectMenu(indexMenu, oldForm);
        } else {
            mainForm.showForm(component);
            //cardMenu1.selectMenu(indexMenu, oldForm);   
        }
        
        
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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

        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Application().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
