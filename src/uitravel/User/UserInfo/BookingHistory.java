/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.User.UserInfo;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import net.miginfocom.swing.MigLayout;
import uitravel.User.UserInfo.Component.BookingShortInfo;

/**
 *
 * @author ACER
 */
public class BookingHistory extends javax.swing.JPanel {

    private List<BookingShortInfo> allBooking;

    public BookingHistory() {
        initComponents();
        init();
        loadBookingInfo();
    }
     private void init(){
        cover.setLayout(new MigLayout("wrap, fill, insets 0","5[]push","12[]5"));
    }
    private void loadBookingInfo(){
        allBooking = new ArrayList<>();
        for(int i=0;i<100;i++){
            BookingShortInfo t = new BookingShortInfo();
            cover.add(t);
            allBooking.add(t);
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtName = new javax.swing.JLabel();
        cover = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(850, 1000));

        txtName.setBackground(new java.awt.Color(255, 255, 255));
        txtName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtName.setText("Các chuyến du lịch đã đặt");

        cover.setPreferredSize(new java.awt.Dimension(795, 920));

        javax.swing.GroupLayout coverLayout = new javax.swing.GroupLayout(cover);
        cover.setLayout(coverLayout);
        coverLayout.setHorizontalGroup(
            coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
        );
        coverLayout.setVerticalGroup(
            coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 947, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtName)
                .addGap(18, 18, 18)
                .addComponent(cover, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane cover;
    private javax.swing.JLabel txtName;
    // End of variables declaration//GEN-END:variables
}
