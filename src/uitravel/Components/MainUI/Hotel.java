/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Components.MainUI;

/**
 *
 * @author ACER
 */
public class Hotel extends javax.swing.JPanel {

    /**
     * Creates new form Hotel
     */
    public Hotel() {
        initComponents();
        init();
    }
    public void init(){
        //imgHotel.setImage("/resources/backimg.jpg");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel2 = new uitravel.Components.RoundedPanel();
        imgHotel = new uitravel.Components.RoundedImage();
        txtName = new javax.swing.JLabel();
        txtLocation = new javax.swing.JLabel();
        txtPrice = new javax.swing.JLabel();
        txtPrice1 = new javax.swing.JLabel();
        txtLocation1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(300, 250));

        roundedPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel2.setBorderColor(new java.awt.Color(204, 204, 204));
        roundedPanel2.setOpaque(false);
        roundedPanel2.setRadius(40);
        roundedPanel2.setWithBorder(true);
        roundedPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                roundedPanel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                roundedPanel2MouseExited(evt);
            }
        });

        imgHotel.setOpaque(false);
        imgHotel.setRadius(40);

        javax.swing.GroupLayout imgHotelLayout = new javax.swing.GroupLayout(imgHotel);
        imgHotel.setLayout(imgHotelLayout);
        imgHotelLayout.setHorizontalGroup(
            imgHotelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imgHotelLayout.setVerticalGroup(
            imgHotelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        txtName.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtName.setText("Khách sạn ABC");

        txtLocation.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        txtLocation.setText("Thủ Đức, TP HCM");

        txtPrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtPrice.setForeground(new java.awt.Color(255, 0, 51));
        txtPrice.setText("VND1.004.722");

        txtPrice1.setBackground(new java.awt.Color(0, 102, 255));
        txtPrice1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtPrice1.setForeground(new java.awt.Color(255, 255, 255));
        txtPrice1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPrice1.setText("10.0");
        txtPrice1.setOpaque(true);
        txtPrice1.setPreferredSize(new java.awt.Dimension(32, 32));

        txtLocation1.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        txtLocation1.setForeground(new java.awt.Color(0, 51, 204));
        txtLocation1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtLocation1.setText("Xem chi tiết");

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 168, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundedPanel2Layout.createSequentialGroup()
                                .addComponent(txtPrice)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtLocation1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundedPanel2Layout.createSequentialGroup()
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))))
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(imgHotel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(imgHotel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLocation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice)
                    .addComponent(txtLocation1))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundedPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundedPanel2MouseEntered
        roundedPanel2.setBorderColor(new java.awt.Color(0, 204, 204));
        roundedPanel2.repaint();
    }//GEN-LAST:event_roundedPanel2MouseEntered

    private void roundedPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundedPanel2MouseExited
        roundedPanel2.setBorderColor(new java.awt.Color(204, 204, 204));
        roundedPanel2.repaint();

    }//GEN-LAST:event_roundedPanel2MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private uitravel.Components.RoundedImage imgHotel;
    private uitravel.Components.RoundedPanel roundedPanel2;
    private javax.swing.JLabel txtLocation;
    private javax.swing.JLabel txtLocation1;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtPrice;
    private javax.swing.JLabel txtPrice1;
    // End of variables declaration//GEN-END:variables
}