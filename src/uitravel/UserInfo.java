/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uitravel;

import FireBase.FirebaseInitializer;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import com.raven.glasspanepopup.GlassPanePopup;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import uitravel.User.UserInfo.AccountInfo;
import uitravel.User.UserInfo.Balances;
import uitravel.User.UserInfo.BookingHistory;
import uitravel.User.UserInfo.Component.ChangePass;

/**
 *
 * @author ACER
 */
public class UserInfo extends javax.swing.JFrame {


    private AccountInfo accountInfo;
    private BookingHistory bookingHistory;
    private Balances balances;
    private String uid;

    public UserInfo() {
        initComponents();
        init();
    }
    public void setUID(String uid) throws IOException{
        this.uid = uid;
        accountInfo.setUID(uid);
        loadDataFromFireStore(uid);
        }
    private void loadDataFromFireStore(String uid){
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference docRef  = db.collection("user").document(uid);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            // Block on response
            DocumentSnapshot document;  
            document = future.get();
           // System.out.println((document.getData()));
            // System.out.println(document.getString("FullName"));

            if(document.exists()){
                
                header.setUserName(document.getString("FullName"));
                txtName.setText(document.getString("FullName"));
                ImageIcon temp =loadImage();
                    if(temp!=null){
                        UserPic.setbackgroundImage(temp);
                        header.setUserAvatar(temp);
                    }
                }
            docRef.addSnapshotListener((snapshot, e) -> {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    String fullName = snapshot.getString("FullName"); 
                    header.setUserName(fullName);
                    txtName.setText(fullName); 
                    ImageIcon temp =loadImage();
                    if(temp!=null){
                        UserPic.setbackgroundImage(temp);
                        header.setUserAvatar(temp);
                    }
                } else {
                    System.out.print("Current data: null");
                }
        });
        }
        catch (ExecutionException | InterruptedException ex) {
            Logger.getLogger(UserMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private void init(){
        GlassPanePopup.install(this);
        accountInfo = new AccountInfo();
        accountInfo.addChangePass(new MouseAdapter(){
                @Override
                    public void mousePressed(MouseEvent e) {
                        GlassPanePopup.showPopup(new ChangePass());
                }
            });
        cover.setLayout(new MigLayout("wrap, fill, insets 0"));
        cover.add(accountInfo);
        header.addEvent((ActionEvent e) -> {
                UserMain um = new UserMain();
                um.setUID(uid);
                um.setIsLogged(true);
                um.setVisible(true);
                dispose();
        });
        header.addAminUIEvent((ActionEvent e)->{
                AdminWelcome am = new AdminWelcome();
                am.setVisible(true);
                dispose();
            });
        header.addChatBoxEvent((ActionEvent e) -> {
            chatBox.setVisible(true);

        });
        chatBox.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        bg = new javax.swing.JLayeredPane();
        roundedPanel1 = new uitravel.Components.RoundedPanel();
        item2 = new uitravel.User.UserInfo.Component.Item();
        item3 = new uitravel.User.UserInfo.Component.Item();
        item4 = new uitravel.User.UserInfo.Component.Item();
        item5 = new uitravel.User.UserInfo.Component.Item();
        item6 = new uitravel.User.UserInfo.Component.Item();
        customLineComponent1 = new uitravel.Components.CustomLineComponent();
        customLineComponent2 = new uitravel.Components.CustomLineComponent();
        UserPic = new uitravel.Components.ImagePanel();
        txtName = new javax.swing.JTextArea();
        cover = new javax.swing.JLayeredPane();
        header = new uitravel.User.UserInfo.Header();
        chatBox = new uitravel.User.MainUI.ChatBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1400, 800));

        bg.setBackground(new java.awt.Color(245, 255, 255));
        bg.setOpaque(true);

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setBorderColor(new java.awt.Color(204, 204, 204));
        roundedPanel1.setWithBorder(true);

        item2.setText("Quản lí số dư");
        item2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                item2MousePressed(evt);
            }
        });

        item3.setText("Lịch sử");
        item3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                item3MousePressed(evt);
            }
        });

        item4.setText("Chuyến đi hiện tại");
        item4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                item4MousePressed(evt);
            }
        });

        item5.setText("Cài đặt");
        item5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                item5MousePressed(evt);
            }
        });
        item5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                item5KeyPressed(evt);
            }
        });

        item6.setText("Đăng xuất");
        item6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                item6MousePressed(evt);
            }
        });

        UserPic.setbackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/resources/BigBeach_GettyImages-874980426-ezgif.com-webp-to-png-converter.png"))); // NOI18N
        UserPic.setisTransparent(false);
        UserPic.setRadius(90);
        UserPic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UserPicMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UserPicMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UserPicMousePressed(evt);
            }
        });

        javax.swing.GroupLayout UserPicLayout = new javax.swing.GroupLayout(UserPic);
        UserPic.setLayout(UserPicLayout);
        UserPicLayout.setHorizontalGroup(
            UserPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );
        UserPicLayout.setVerticalGroup(
            UserPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        txtName.setColumns(20);
        txtName.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtName.setLineWrap(true);
        txtName.setRows(5);
        txtName.setWrapStyleWord(true);

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(UserPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customLineComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(item2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(item3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(item4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(customLineComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(item6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(item5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(UserPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customLineComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(item2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(item3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(item4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customLineComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(item5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(item6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        cover.setPreferredSize(new java.awt.Dimension(850, 532));

        javax.swing.GroupLayout coverLayout = new javax.swing.GroupLayout(cover);
        cover.setLayout(coverLayout);
        coverLayout.setHorizontalGroup(
            coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        coverLayout.setVerticalGroup(
            coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
        );

        header.setOpaque(true);

        bg.setLayer(roundedPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(cover, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(header, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(chatBox, javax.swing.JLayeredPane.POPUP_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 1401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 456, Short.MAX_VALUE))
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(cover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(960, 960, 960)
                    .addComponent(chatBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(504, Short.MAX_VALUE)))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cover, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)))
                .addGap(104, 104, 104))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(350, 350, 350)
                    .addComponent(chatBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(196, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(bg);

        getContentPane().add(jScrollPane1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void item6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item6MousePressed
        int res = JOptionPane.showOptionDialog(new JFrame(), "Bạn có muốn đăng xuất không?","Xác nhận",
         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
         new Object[] { "Có", "Không" }, JOptionPane.YES_OPTION);
        switch (res) {
            case JOptionPane.YES_OPTION -> {
                    UserMain um = new UserMain();
                    um.setUID(uid);
                    um.setIsLogged(false);
                    um.setVisible(true);
                    dispose();
                }
            case JOptionPane.NO_OPTION -> {break;}
            case JOptionPane.CLOSED_OPTION -> {break;}
            default -> {
            }
        }
    }//GEN-LAST:event_item6MousePressed
    
    private void item5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item5MousePressed
        cover.removeAll();
        accountInfo = new AccountInfo();
        try {
            accountInfo.setUID(uid);
        } catch (IOException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        accountInfo.addChangePass(new MouseAdapter(){
                @Override
                    public void mousePressed(MouseEvent e) {
                        GlassPanePopup.showPopup(new ChangePass());
                }
            });
        cover.setLayout(new MigLayout("wrap, fill, insets 0"));
        cover.add(accountInfo);
        cover.repaint();
        cover.revalidate();
    }//GEN-LAST:event_item5MousePressed

    private void item3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item3MousePressed
        cover.removeAll();
        bookingHistory = new BookingHistory();
        cover.setLayout(new MigLayout("wrap, fill, insets 0","[]","[]push"));
        cover.add(bookingHistory);
        cover.repaint();
        cover.revalidate();
    }//GEN-LAST:event_item3MousePressed

    private void item2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item2MousePressed
        cover.removeAll();
        balances = new Balances();
        cover.setLayout(new MigLayout("wrap, fill, insets 0","[]","[]push"));
        cover.add(balances);
        cover.repaint();
        cover.revalidate();
    }//GEN-LAST:event_item2MousePressed

    private void item4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_item4MousePressed

    private void item5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_item5KeyPressed
    private void UserPicMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPicMouseEntered
        UserPic.setwithBlack(true);

    }//GEN-LAST:event_UserPicMouseEntered

    private void UserPicMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPicMouseExited
        UserPic.setwithBlack(false);

    }//GEN-LAST:event_UserPicMouseExited
    public void uploadImage(ImageIcon imageIcon ) {
         try {
        BufferedImage bufferedImage = new BufferedImage(
                imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        imageIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] imageData = baos.toByteArray();

        // Convert byte array to Base64 string
        String imageDataString = Base64.getEncoder().encodeToString(imageData);

        Firestore firestore = FirestoreClient.getFirestore();
        Map<String, Object> userData = new HashMap<>();
        userData.put("Avatar", imageDataString);
        DocumentReference docRef = firestore.collection("user").document(uid);
        docRef.update(userData);

        JOptionPane.showMessageDialog(null,
                "Thay đổi ảnh đại diện thành công!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null,
                "Lỗi khi tải lên ảnh!",
                "Thông báo!",
                JOptionPane.ERROR_MESSAGE);
    }
    }
    public ImageIcon loadImage() {
     try {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("user").document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            String imageDataString = document.getString("Avatar");
            if (imageDataString != null) {
                // Convert Base64 string back to byte array
                byte[] imageData = Base64.getDecoder().decode(imageDataString);

                ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
                BufferedImage bufferedImage = ImageIO.read(bais);
                return new ImageIcon(bufferedImage);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Không tìm thấy dữ liệu ảnh!",
                        "Thông báo!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Không tìm thấy người dùng!",
                    "Thông báo!",
                    JOptionPane.ERROR_MESSAGE);
        }
    } catch (HeadlessException | IOException | InterruptedException | ExecutionException e) {
        JOptionPane.showMessageDialog(null,
                "Lỗi khi tải ảnh!",
                "Thông báo!",
                JOptionPane.ERROR_MESSAGE);
    }
    return null;
}
    private void UserPicMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPicMousePressed
        ImageIcon img = chooseImages();
        if(img!=null){
            uploadImage(img);
        }
    }//GEN-LAST:event_UserPicMousePressed
    private ImageIcon chooseImages() {

       JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose image file");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                return imageIcon;
            
          
        }
        else
            return null;
    };
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
            java.util.logging.Logger.getLogger(UserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private uitravel.Components.ImagePanel UserPic;
    private javax.swing.JLayeredPane bg;
    private uitravel.User.MainUI.ChatBox chatBox;
    private javax.swing.JLayeredPane cover;
    private uitravel.Components.CustomLineComponent customLineComponent1;
    private uitravel.Components.CustomLineComponent customLineComponent2;
    private uitravel.User.UserInfo.Header header;
    private uitravel.User.UserInfo.Component.Item item2;
    private uitravel.User.UserInfo.Component.Item item3;
    private uitravel.User.UserInfo.Component.Item item4;
    private uitravel.User.UserInfo.Component.Item item5;
    private uitravel.User.UserInfo.Component.Item item6;
    private javax.swing.JScrollPane jScrollPane1;
    private uitravel.Components.RoundedPanel roundedPanel1;
    private javax.swing.JTextArea txtName;
    // End of variables declaration//GEN-END:variables
}
