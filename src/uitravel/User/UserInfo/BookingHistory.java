/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.User.UserInfo;

import FireBase.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import uitravel.AdminMain;
import uitravel.User.UserInfo.Component.BookingShortInfo;
import uitravel.User.UserInfo.Component.HistoryFullInfo;
import uitravel.UserInfo;
import uitravel.UserMain;
/**
 *
 * @author ACER
 */
public class BookingHistory extends javax.swing.JPanel {

    private String uid;
    
    Firestore firestore = FirestoreClient.getFirestore();

    public BookingHistory(){
        initComponents();
        init();
    }
    private void init(){
        cover.setLayout(new MigLayout("wrap, fill, insets 0","[]push","[]push"));
        cover.setVisible(false);
        none.setVisible(false);
    }
    public void setUID(String uid){
        this.uid = uid;
        loadDataFromFireStore(uid);

    }
     private void loadDataFromFireStore(String uid){
        try {

            CollectionReference collection = firestore.collection("user").document(uid).collection("History");
            System.out.println(uid);
            ApiFuture<QuerySnapshot> querySnapshot = collection.get();

            // Block on response
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

            if(!documents.isEmpty()){
                for (DocumentSnapshot document : documents) {
                    String documentId = document.getId();
                    BookingShortInfo t = new BookingShortInfo();
                    t.setBookingID(documentId);
                    t.setTime(document.getString("Time"));
                    t.setPrices(document.getString("Prices"));
                    String TourID = document.getString("MainTourID");
                    System.out.println(TourID);
                    DocumentReference tourInfoDoc = firestore.collection("admin").document("AllTours").collection("TourInfo").document(TourID);
                    t.setBookingName(tourInfoDoc.get().get().getString("TourName"));

                    List<String> tourImagesBase64;
                    tourImagesBase64 = (List<String>) tourInfoDoc.get().get().get("TourImages");
                    ImageIcon tempIMG = convertBase64ToImageIcon(tourImagesBase64);
                    t.setBackgroundImage(tempIMG);
                    t.addHitory((ActionEvent e) -> {
                        try {
                            callOpenHistory(this,uid,TourID,tempIMG,firestore.collection("user").document(uid).get().get().getString("FullName"),documentId);
                        } catch (InterruptedException | ExecutionException ex) {
                            Logger.getLogger(BookingHistory.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    cover.add(t,"gapright 10, gaptop 10");
                    String background = document.getString("Background");
                    cover.setVisible(true);
                    none.setVisible(false);
                    System.out.println(background);
                }
            }
            else{
                cover.setVisible(false);
                none.setVisible(true);
            }
            
        }
        catch (ExecutionException | InterruptedException ex) {
            Logger.getLogger(UserMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     private ImageIcon convertBase64ToImageIcon(List<String> base64Images) {
        List<ImageIcon> imageIcons = new ArrayList<>();
        for (String base64Image : base64Images) {
            try {
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                BufferedImage bufferedImage = ImageIO.read(bais);
                ImageIcon imageIcon = new ImageIcon(bufferedImage);
                imageIcons.add(imageIcon);
                break;
            } catch (IOException e) {
            }
        }
        return imageIcons.get(0);
    }
     private void callOpenHistory(Component component, String uid, String tourid, ImageIcon img, String userName, String documentId) {
        JFrame frame = getFrame(component);
        if (frame != null) {
            ((UserInfo) frame).callHistory( uid,tourid,img,userName,documentId);

        }
    }
     private JFrame getFrame(Component component) {
        while (component != null) {
            
            if (component instanceof JFrame jFrame) {
                return jFrame;
            }
            component = component.getParent();
        }
        return null;
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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        cover = new javax.swing.JLayeredPane();
        none = new uitravel.Components.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        imagePanel1 = new uitravel.Components.ImagePanel();
        jTextArea1 = new javax.swing.JTextArea();

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
            .addGap(0, 229, Short.MAX_VALUE)
        );

        none.setBackground(new java.awt.Color(255, 255, 255));
        none.setBorderColor(new java.awt.Color(0, 102, 255));
        none.setWithBorder(true);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Hiện tại không có chuyến đi nào");

        imagePanel1.setbackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/resources/tourInfo.png"))); // NOI18N
        imagePanel1.setisTransparent(false);

        javax.swing.GroupLayout imagePanel1Layout = new javax.swing.GroupLayout(imagePanel1);
        imagePanel1.setLayout(imagePanel1Layout);
        imagePanel1Layout.setHorizontalGroup(
            imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        imagePanel1Layout.setVerticalGroup(
            imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Hãy khám phá một chuyến đi tuyệt vời nào đó thôi!");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setEnabled(false);
        jTextArea1.setOpaque(false);

        javax.swing.GroupLayout noneLayout = new javax.swing.GroupLayout(none);
        none.setLayout(noneLayout);
        noneLayout.setHorizontalGroup(
            noneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noneLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(imagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(noneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(noneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(noneLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        noneLayout.setVerticalGroup(
            noneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noneLayout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(noneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPane1.setLayer(cover, javax.swing.JLayeredPane.POPUP_LAYER);
        jLayeredPane1.setLayer(none, javax.swing.JLayeredPane.POPUP_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addComponent(cover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(none, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cover, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(none, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtName)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(718, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane cover;
    private uitravel.Components.ImagePanel imagePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JTextArea jTextArea1;
    private uitravel.Components.RoundedPanel none;
    private javax.swing.JLabel txtName;
    // End of variables declaration//GEN-END:variables
}
