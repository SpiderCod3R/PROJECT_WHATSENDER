package whatsender.application.component;

import java.awt.event.ActionListener;

/**
 *
 * @author THE GRAND MASTER
 */
public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdButton = new whatsender.application.swing.Button();

        setBackground(new java.awt.Color(244, 245, 255));

        cmdButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N
        cmdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(487, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public void addMenuEvent(ActionListener event){
        cmdButton.addActionListener(event);
    }
    
    private void cmdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private whatsender.application.swing.Button cmdButton;
    // End of variables declaration//GEN-END:variables
}
