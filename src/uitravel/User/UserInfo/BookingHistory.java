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
        cover.setLayout(new MigLayout("wrap, fill, insets 0","5[]push","20[]push"));
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
                cover.add(t);
                String background = document.getString("Background");
                System.out.println(background);
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
