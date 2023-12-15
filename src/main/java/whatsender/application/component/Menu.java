package whatsender.application.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import whatsender.application.event.EventMenu;
import whatsender.application.event.EventMenuSelected;
import whatsender.application.model.ModelMenu;
import icon.IconConstants;
import whatsender.application.event.EventShowPopUpMenu;
import whatsender.application.swing.MenuAnimation;
import whatsender.application.swing.MenuItem;
import whatsender.application.swing.scrollbar.ScrollBarCustom;

/**
 *
 * @author THE GRAND MASTER
 */
public class Menu extends javax.swing.JPanel {
    private final MigLayout layout;
    private Animator animator;
    private EventMenuSelected event;
    private EventShowPopUpMenu eventShowPopUpMenu;

    
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public boolean isShowMenu() {
        return showMenu;
    }
    
    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
        TimingTargetAdapter target = new TimingTargetAdapter(){
        
        };
        
    }
    
    public void addMenu(ModelMenu menu){
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }
    
    public void addEventShowPopUpMenu(EventShowPopUpMenu eventShowPopUpMenu) {
        this.eventShowPopUpMenu = eventShowPopUpMenu;
    }
    
    private EventMenu getEventMenu(){
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
//                System.err.println("Menu press");
//                return true;
                if(enableMenu){
                    if(showMenu){
                        if(open){
                            new MenuAnimation(layout, com).openMenu();
                        }else{
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    }else {
//                        System.err.println("Show pop up menu (Menu is Closed)");
                          eventShowPopUpMenu.showPopUp(com);
                    }
                }
                return false;
            }
        };
    }

    protected ImageIcon createImageIcon(URL imgURL,String description) {
        return new ImageIcon(imgURL, description);
    }
    
    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(IconConstants.imgDash)), "Painel"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(IconConstants.imgSendMessage)), "Enviar mensagem"));
        
        addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(IconConstants.imgConfig)), "Configurações", "Cliente"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(IconConstants.imgLogs)), "Logs"));
        
        addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(IconConstants.imgExit)), "Fechar"));
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }
    
    public void hideAllMenus(){
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem)com;
            if(item.isOpen()){
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpaque(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile1 = new whatsender.application.component.Profile();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
            .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(60,136,162), getWidth(), 0 , new Color(13,127,161));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private whatsender.application.component.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
