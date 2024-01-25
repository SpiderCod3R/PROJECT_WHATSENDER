
package whatsender.gui.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import net.miginfocom.swing.MigLayout;
import whatsender.application.menu.event_menu.EventMenu;
import whatsender.application.menu.event_menu.EventMenuSelected;
import whatsender.application.menu.model_menu.ModelMenu;

/**
 *
 * @author THE GRAND MASTER
 */
public class MenuItem extends javax.swing.JPanel {


    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public EventMenuSelected getEventMenuSelected() {
        return eventMenuSelected;
    }

    public void setEventMenuSelected(EventMenuSelected eventMenuSelected) {
        this.eventMenuSelected = eventMenuSelected;
    }

    public int getIndex() {
        return index;
    }
    
    public ModelMenu getMenu() {
        return menu;
    }

    public void setMenu(ModelMenu menu) {
        this.menu = menu;
    }

    private float alpha;
    private ModelMenu menu;
    private boolean open;
    private EventMenuSelected eventMenuSelected;
    private int index;
    
    public MenuItem(ModelMenu menu, EventMenu event, EventMenuSelected eventMenuSelected, int index) {
        initComponents();
        this.menu = menu;
        this.eventMenuSelected = eventMenuSelected;
        this.index = index;
        setOpaque(false);
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[fill, 40!]0[fill, 35!]"));
        MenuButton firstItem = new MenuButton(menu.getIcon(), "      " + menu.getMenuName());
        
        firstItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(menu.getSubMenu().length > 0){
                    if(event.menuPressed(MenuItem.this, !open)){
                        open =! open;
                    }
                }
                eventMenuSelected.menuSelected(index, -1);
            }
        });
        add(firstItem);
        
        int subMenuIndex = -1;
        for(String st : menu.getSubMenu()){
            MenuButton item = new MenuButton(st);
            item.setIndex(++subMenuIndex);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eventMenuSelected.menuSelected(index, item.getIndex());
                }
            });
            add(item);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int heigth = getPreferredSize().height;
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(50,50,50));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.fillRect(0,2, width, 38);
        g2.setComposite(AlphaComposite.SrcOver);
        g2.fillRect(0,40, width, heigth - 40);
        g2.setColor(new Color(100,100,100));
        g2.drawLine(30, 40, 30, heigth - 17);
        
        for (int i=0; i < menu.getSubMenu().length; i++) {
            int y=( (i+1) * 35 +40) - 17;
            g2.drawLine(30, y, 38, y);
        }
        if(menu.getSubMenu().length > 0){
            createArrowButton(g2);
        }
        super.paintComponent(grphcs);
    }
    
    private void createArrowButton(Graphics2D g2){
        int size=4;
        int y=19;
        int x=205;
        g2.setColor(new Color(230,230,230));
        float ay= alpha*size;
        float ay1= (1f-alpha) * size;
        g2.drawLine(x, (int)(y + ay), x + 4, (int)(y + ay1));
        g2.drawLine(x + 4, (int)(y + ay1), x + 8, (int)(y + ay));
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
