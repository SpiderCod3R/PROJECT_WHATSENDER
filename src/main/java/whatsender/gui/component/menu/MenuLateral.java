package whatsender.gui.component.menu;

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
import whatsender.gui.component.menu.event_menu.EventMenu;
import whatsender.gui.component.menu.event_menu.EventMenuSelected;
import whatsender.bot.driver.Browser;
import whatsender.bot.driver.WhatsAppDriver;
import whatsender.gui.component.menu.event_menu.EventShowPopUpMenu;
import whatsender.gui.swing.MenuAnimation;
import whatsender.gui.swing.MenuItem;
import whatsender.gui.scrollbar.ScrollBarCustom;

/**
 *
 * @author THE GRAND MASTER
 */
public class MenuLateral extends javax.swing.JPanel {
    private final MigLayout layout;
    private Animator animator;
    private EventMenuSelected event;
    private EventShowPopUpMenu eventShowPopUpMenu;
    private static WhatsAppDriver WHATSAPP = null;

    
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public boolean isShowMenu() {
        return showMenu;
    }
    
    public MenuLateral(WhatsAppDriver WHATSAPP) {
        initComponents();
        setOpaque(false);
        
        WHATSAPP = WHATSAPP;
        
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panelMenu.setLayout(layout);

        TimingTargetAdapter target = new TimingTargetAdapter(){
        
        };
        
        if(WHATSAPP.is_connected()) {
            btnConnection.setBackground(new Color(121,224,201,88));
            lblConnection.setText("Conectado");
        }
        
    }
    
    public void addMenu(ModelMenu menu){
        panelMenu.add(new MenuItem(menu, getEventMenu(), event, panelMenu.getComponentCount()), "h 40!");
    }
    
    public void addEventShowPopUpMenu(EventShowPopUpMenu eventShowPopUpMenu) {
        this.eventShowPopUpMenu = eventShowPopUpMenu;
    }
    
    private EventMenu getEventMenu(){
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
//                System.err.println("MenuLateral press");
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
//                        System.err.println("Show pop up menu (MenuLateral is Closed)");
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
        addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(Icones.imgDash)), "Painel"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(Icones.imgSendMessage)), "Enviar mensagem"));
        
        addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(Icones.imgConfig)), "Configurações", "Editar Cliente", "Gerenciar Pacote"));
        //addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(Icones.imgLogs)), "Logs"));
        
        addMenu(new ModelMenu(new ImageIcon(getClass().getClassLoader().getResource(Icones.imgExit)), "Fechar"));
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
        for (Component com : panelMenu.getComponents()) {
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
        panelMenu = new javax.swing.JPanel();
        bannerProfile = new whatsender.gui.component.menu.MenuLogo();
        panelConnection = new javax.swing.JPanel();
        lblConnection = new javax.swing.JLabel();
        btnConnection = new whatsender.gui.swing.Button();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelMenu.setOpaque(false);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );

        sp.setViewportView(panelMenu);

        panelConnection.setOpaque(false);

        lblConnection.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblConnection.setForeground(new java.awt.Color(244, 244, 244));
        lblConnection.setText("Não Conectado");
        lblConnection.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        btnConnection.setBackground(new java.awt.Color(230, 147, 147));
        btnConnection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/6s.png"))); // NOI18N

        javax.swing.GroupLayout panelConnectionLayout = new javax.swing.GroupLayout(panelConnection);
        panelConnection.setLayout(panelConnectionLayout);
        panelConnectionLayout.setHorizontalGroup(
            panelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConnectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblConnection)
                .addGap(15, 15, 15))
        );
        panelConnectionLayout.setVerticalGroup(
            panelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConnectionLayout.createSequentialGroup()
                .addGroup(panelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConnectionLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblConnection))
                    .addGroup(panelConnectionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bannerProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(panelConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(bannerProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
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
    private whatsender.gui.component.menu.MenuLogo bannerProfile;
    private whatsender.gui.swing.Button btnConnection;
    private javax.swing.JLabel lblConnection;
    private javax.swing.JPanel panelConnection;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
