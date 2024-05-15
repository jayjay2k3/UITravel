/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Components.MainUI;

import java.awt.*;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import uitravel.LoginMain;

/**
 *
 * @author ACER
 */
public class Header extends javax.swing.JPanel {

    /**
     * Creates new form Header
     */
    private ActionListener eventLogin;
    public boolean isLogin;
    public Header() {
        initComponents();
        setOpaque(false);
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
      @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#0052d4"), 0, getHeight(), Color.decode("#9cecfb"));
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.fillRect(0, getHeight()-25, getWidth(), getHeight());

        super.paintComponent(grphcs);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myButton1 = new uitravel.Components.MyButton();
        myButton2 = new uitravel.Components.MyButton();
        btnHelp = new javax.swing.JButton();
        btnDiscount = new javax.swing.JButton();
        btnRoom = new javax.swing.JButton();

        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setText("Đăng ký");
        myButton1.setToolTipText("");
        myButton1.setBorderColor(new java.awt.Color(255, 255, 255));
        myButton1.setColor(new java.awt.Color(0, 102, 255));
        myButton1.setColorClick(new java.awt.Color(51, 153, 255));
        myButton1.setColorOver(new java.awt.Color(0, 102, 204));
        myButton1.setContentAreaFilled(true);
        myButton1.setOpaque(false);
        myButton1.setRadius(10);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        myButton2.setForeground(new java.awt.Color(255, 255, 255));
        myButton2.setText("Đăng nhập");
        myButton2.setBorderColor(new java.awt.Color(255, 255, 255));
        myButton2.setColor(new java.awt.Color(0, 102, 255));
        myButton2.setColorClick(new java.awt.Color(51, 153, 255));
        myButton2.setColorOver(new java.awt.Color(0, 102, 204));
        myButton2.setContentAreaFilled(true);
        myButton2.setRadius(10);
        myButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton2ActionPerformed(evt);
            }
        });

        btnHelp.setText("Hỗ trợ");
        btnHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHelpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHelpMouseExited(evt);
            }
        });

        btnDiscount.setText("Khuyến mãi");
        btnDiscount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDiscountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDiscountMouseExited(evt);
            }
        });
        btnDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountActionPerformed(evt);
            }
        });

        btnRoom.setText("Phòng đã đặt");
        btnRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRoomMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRoomMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(658, Short.MAX_VALUE)
                .addComponent(btnDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(178, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        this.isLogin = false;
        eventLogin.actionPerformed(evt);
    }//GEN-LAST:event_myButton1ActionPerformed

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
         this.isLogin = true;
        eventLogin.actionPerformed(evt);
    }//GEN-LAST:event_myButton2ActionPerformed

    private void btnHelpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelpMouseEntered
        // TODO add your handling code here:
            btnHelp.setBackground(new Color(0, 82, 212, 210)); // semi-transparent white
            btnHelp.setContentAreaFilled(true); // Fill content area to show background color
            btnHelp.setBorderPainted(false);
            btnHelp.repaint();
    }//GEN-LAST:event_btnHelpMouseEntered

    private void btnHelpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelpMouseExited
            btnHelp.setContentAreaFilled(false); // Fill content area to show background color

    }//GEN-LAST:event_btnHelpMouseExited

    private void btnDiscountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDiscountMouseEntered
        btnDiscount.setBackground(new Color(0, 82, 212, 210)); // semi-transparent white
        btnDiscount.setContentAreaFilled(true); // Fill content area to show background color
        btnDiscount.setBorderPainted(false);
        btnDiscount.repaint();
    }//GEN-LAST:event_btnDiscountMouseEntered

    private void btnDiscountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDiscountMouseExited
        btnDiscount.setContentAreaFilled(false); // Fill content area to show background color

    }//GEN-LAST:event_btnDiscountMouseExited

    private void btnDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountActionPerformed
        
    }//GEN-LAST:event_btnDiscountActionPerformed

    private void btnRoomMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRoomMouseEntered
        btnRoom.setBackground(new Color(0, 82, 212, 210)); // semi-transparent white
        btnRoom.setContentAreaFilled(true); // Fill content area to show background color
        btnRoom.setBorderPainted(false);
        btnRoom.repaint();
    }//GEN-LAST:event_btnRoomMouseEntered

    private void btnRoomMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRoomMouseExited
        btnRoom.setContentAreaFilled(false); // Fill content area to show background color

    }//GEN-LAST:event_btnRoomMouseExited
    public void addEvent(ActionListener event) {
        this.eventLogin = event;
    }
    private void init() {
       btnHelp.setOpaque(false);
       btnHelp.setContentAreaFilled(false);
       btnHelp.setBorderPainted(false);
       btnHelp.setBorder(null); // Remove the border
       btnHelp.setForeground(Color.WHITE);
       
       btnDiscount.setOpaque(false);
       btnDiscount.setContentAreaFilled(false);
       btnDiscount.setBorderPainted(false);
       btnDiscount.setBorder(null); // Remove the border
       btnDiscount.setForeground(Color.WHITE);
       
       btnRoom.setOpaque(false);
       btnRoom.setContentAreaFilled(false);
       btnRoom.setBorderPainted(false);
       btnRoom.setBorder(null); // Remove the border
       btnRoom.setForeground(Color.WHITE);
       
        myButton1.setTransparent(false);
        myButton1.setForeground(Color.WHITE);
        myButton2.setTransparent(false);
        myButton2.setForeground(Color.WHITE);
        
        Font buttonFont = new Font("Times new roman", Font.BOLD, 16);
        myButton1.setFont(buttonFont);
        myButton2.setFont(buttonFont);
        btnDiscount.setFont(buttonFont);
        btnRoom.setFont(buttonFont);
        btnHelp.setFont(buttonFont);

      // myButton1.setContentAreaFilled(false);
      // myButton1.setBorderPainted(false);
       
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDiscount;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnRoom;
    private uitravel.Components.MyButton myButton1;
    private uitravel.Components.MyButton myButton2;
    // End of variables declaration//GEN-END:variables
}
