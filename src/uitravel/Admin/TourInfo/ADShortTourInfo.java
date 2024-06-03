/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Admin.TourInfo;

import uitravel.Components.TourInfo.Component.*;
import java.awt.Color;
import java.awt.event.ActionListener;

/**
 *
 * @author ACER
 */
public class ADShortTourInfo extends javax.swing.JPanel {

    private ActionListener eventGetFullTourInfo;
    public void addEventGetTourInfo(ActionListener event) {
        this.eventGetFullTourInfo = event;
    }
    private String id;
    public ADShortTourInfo() {
        initComponents();
    }
    public void setTourName(String name){
        txtName.setText(name);
    }
    public void setPlace(String place){
        txtPlace.setText(place);
    }
    public void setDescription(String place){
        txtDescription.setText(place);
    }
    public void setTime(String place){
        txtTime.setText(place);
    }
     public void setScore(Double score){
        txtScore.setText(score.toString());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel1 = new uitravel.Components.RoundedPanel();
        txtName = new javax.swing.JLabel();
        pic = new uitravel.Components.RoundedImage();
        txtTime = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextArea();
        myButton1 = new uitravel.Components.MyButton();
        txtPlace = new javax.swing.JLabel();
        txtScore = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(550, 176));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setBorderColor(new java.awt.Color(204, 204, 204));
        roundedPanel1.setWithBorder(true);

        txtName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtName.setText("Du lịch Dĩ An");

        pic.setOpaque(false);
        pic.setRadius(30);

        javax.swing.GroupLayout picLayout = new javax.swing.GroupLayout(pic);
        pic.setLayout(picLayout);
        picLayout.setHorizontalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        picLayout.setVerticalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
        );

        txtTime.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txtTime.setText("Thời gian: 2 ngày");

        txtDescription.setEditable(false);
        txtDescription.setBackground(new java.awt.Color(255, 255, 255));
        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setText("Tour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour aTour a\n\n");
        txtDescription.setWrapStyleWord(true);
        txtDescription.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDescription.setEnabled(false);
        txtDescription.setOpaque(false);

        myButton1.setBackground(new java.awt.Color(0, 0, 255));
        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setText("Xem thông tin ");
        myButton1.setBorderColor(new java.awt.Color(0, 0, 255));
        myButton1.setColor(new java.awt.Color(0, 0, 255));
        myButton1.setColorClick(new java.awt.Color(0, 150, 255));
        myButton1.setColorOver(new java.awt.Color(0, 100, 255));
        myButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        myButton1.setRadius(10);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        txtPlace.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txtPlace.setText("TP HCM");

        txtScore.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtScore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Star1.png"))); // NOI18N
        txtScore.setText("1");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(txtName)
                        .addGap(285, 285, 285))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(roundedPanel1Layout.createSequentialGroup()
                            .addComponent(txtPlace)
                            .addGap(284, 284, 284)
                            .addComponent(txtScore))
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundedPanel1Layout.createSequentialGroup()
                                .addComponent(txtTime)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 1, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPlace)
                    .addComponent(txtScore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTime)
                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(roundedPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        roundedPanel1.setBorderColor(new Color(0,153,255));
        repaint();
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
         roundedPanel1.setBorderColor(new Color(204,204,204));
                 repaint();

    }//GEN-LAST:event_formMouseExited

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        eventGetFullTourInfo.actionPerformed(evt);
    }//GEN-LAST:event_myButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private uitravel.Components.MyButton myButton1;
    private uitravel.Components.RoundedImage pic;
    private uitravel.Components.RoundedPanel roundedPanel1;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtPlace;
    private javax.swing.JLabel txtScore;
    private javax.swing.JLabel txtTime;
    // End of variables declaration//GEN-END:variables
}
