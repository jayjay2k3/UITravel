/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uitravel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.raven.glasspanepopup.DefaultLayoutCallBack;
import com.raven.glasspanepopup.DefaultOption;
import com.raven.glasspanepopup.GlassPanePopup;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.LayoutCallback;
import net.miginfocom.swing.MigLayout;
import uitravel.Admin.TourInfo.ADShortTourInfo;
import uitravel.Admin.TourInfo.TourBookingData;
import uitravel.Admin.component.UserBar;

/**
 *
 * @author ACER
 */
public class AdminMain extends javax.swing.JFrame {

    List <ADShortTourInfo> allTour;
    public AdminMain() {
        initComponents();
        init();
        loadTour();
    }
    private void init(){
        GlassPanePopup.install(this);
        userBar.setVisible(false);


    }
  private void loadTour(){
     //   bg.setLayout(new MigLayout("wrap, fill"));
        cover.setLayout( new MigLayout("wrap, fill, insets 50","[]10[]", "12[]12"));
        allTour = new ArrayList<>();
        for(int i=0;i<20;i++){
            ADShortTourInfo t = new ADShortTourInfo();
            t.addEventGetTourInfo((ActionEvent e) -> {
               GlassPanePopup.showPopup(new TourBookingData());
            });
            allTour.add(t);
            cover.add(t);
        }
       // cover.setPreferredSize(new Dimension(1400,800));
       
        cover.repaint();
        cover.revalidate();
     
        //main.setViewportView(bg);

        System.out.println(cover.getHeight());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JScrollPane();
        bg = new javax.swing.JLayeredPane();
        header = new javax.swing.JPanel();
        user = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        imagePanel1 = new uitravel.Components.ImagePanel();
        addNewTour = new uitravel.Admin.component.AddNewTour();
        txtEmail1 = new javax.swing.JLabel();
        cover = new javax.swing.JLayeredPane();
        cover1 = new javax.swing.JLayeredPane();
        txtEmail3 = new javax.swing.JLabel();
        userBar = new uitravel.Admin.component.UserBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1400, 800));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        main.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setPreferredSize(new java.awt.Dimension(1400, 91));

        user.setBackground(new java.awt.Color(255, 255, 255));
        user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Xin chào");

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtEmail.setText("21521857@gm.uit.edu.vn");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Arrow - Down.png"))); // NOI18N

        javax.swing.GroupLayout userLayout = new javax.swing.GroupLayout(user);
        user.setLayout(userLayout);
        userLayout.setHorizontalGroup(
            userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        userLayout.setVerticalGroup(
            userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userLayout.createSequentialGroup()
                .addGroup(userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail))
                    .addGroup(userLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap(1148, Short.MAX_VALUE)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        imagePanel1.setbackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/resources/vietnam_halongbay.jpg"))); // NOI18N
        imagePanel1.setisTransparent(false);
        imagePanel1.setPreferredSize(new java.awt.Dimension(1400, 800));

        txtEmail1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        txtEmail1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtEmail1.setText("Thêm chuyến đi mới");

        imagePanel1.setLayer(addNewTour, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imagePanel1.setLayer(txtEmail1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imagePanel1Layout = new javax.swing.GroupLayout(imagePanel1);
        imagePanel1.setLayout(imagePanel1Layout);
        imagePanel1Layout.setHorizontalGroup(
            imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addNewTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail1))
                .addContainerGap(977, Short.MAX_VALUE))
        );
        imagePanel1Layout.setVerticalGroup(
            imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(txtEmail1)
                .addGap(52, 52, 52)
                .addComponent(addNewTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(356, Short.MAX_VALUE))
        );

        cover.setBackground(new java.awt.Color(255, 255, 255));
        cover.setOpaque(true);

        javax.swing.GroupLayout coverLayout = new javax.swing.GroupLayout(cover);
        cover.setLayout(coverLayout);
        coverLayout.setHorizontalGroup(
            coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1450, Short.MAX_VALUE)
        );
        coverLayout.setVerticalGroup(
            coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );

        cover1.setBackground(new java.awt.Color(255, 255, 255));
        cover1.setOpaque(true);

        txtEmail3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        txtEmail3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtEmail3.setText("Quản lí chuyến du lịch");

        cover1.setLayer(txtEmail3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout cover1Layout = new javax.swing.GroupLayout(cover1);
        cover1.setLayout(cover1Layout);
        cover1Layout.setHorizontalGroup(
            cover1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cover1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(txtEmail3)
                .addContainerGap(1023, Short.MAX_VALUE))
        );
        cover1Layout.setVerticalGroup(
            cover1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cover1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(txtEmail3)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        userBar.setBackground(new java.awt.Color(255, 102, 102));

        bg.setLayer(header, javax.swing.JLayeredPane.PALETTE_LAYER);
        bg.setLayer(imagePanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(cover, javax.swing.JLayeredPane.POPUP_LAYER);
        bg.setLayer(cover1, javax.swing.JLayeredPane.POPUP_LAYER);
        bg.setLayer(userBar, javax.swing.JLayeredPane.POPUP_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(imagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                    .addComponent(cover)
                    .addContainerGap()))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(cover1)
                    .addGap(0, 0, 0)))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(1200, 1200, 1200)
                    .addComponent(userBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(106, Short.MAX_VALUE)))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(imagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1028, Short.MAX_VALUE)))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(618, 618, 618)
                    .addComponent(cover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(503, 503, 503)
                    .addComponent(cover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(501, 501, 501)))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(80, 80, 80)
                    .addComponent(userBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(939, Short.MAX_VALUE)))
        );

        main.setViewportView(bg);

        getContentPane().add(main);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMousePressed
        
        userBar.setVisible(!userBar.isVisible());
        System.out.println(userBar);
      
    }//GEN-LAST:event_userMousePressed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
                FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private uitravel.Admin.component.AddNewTour addNewTour;
    private javax.swing.JLayeredPane bg;
    private javax.swing.JLayeredPane cover;
    private javax.swing.JLayeredPane cover1;
    private javax.swing.JPanel header;
    private uitravel.Components.ImagePanel imagePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane main;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtEmail1;
    private javax.swing.JLabel txtEmail3;
    private javax.swing.JPanel user;
    private uitravel.Admin.component.UserBar userBar;
    // End of variables declaration//GEN-END:variables
}
