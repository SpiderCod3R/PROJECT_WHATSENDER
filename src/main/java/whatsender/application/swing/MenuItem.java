
package whatsender.application.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import whatsender.application.event.EventMenu;
import whatsender.application.event.EventMenuSelected;
import whatsender.application.forms.ModelMenu;

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
        MenuButton firstItem = new MenuButton(menu.getIcon(), menu.getMenuName());
        
        firstItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(menu.getSubMenu().length > 0){
                    System.out.println("Click the first Item !!!");
                }
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
