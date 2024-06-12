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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
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
import uitravel.Components.Activity;
import uitravel.User.UserInfo.Component.BookingShortInfo;
import uitravel.User.UserInfo.Component.HistoryFullInfo;
import uitravel.UserInfo;
import uitravel.UserMain;
/**
 *
 * @author ACER
 */
public class CurrentTour extends javax.swing.JPanel {

    private String uid;
    
    Firestore firestore = FirestoreClient.getFirestore();

    public CurrentTour(){
        initComponents();
        init();
    }
    private void init(){
        pnlAcc.setLayout(new MigLayout("fillx"));
        none.setVisible(false);
        haveOne.setVisible(false);

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
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date data = null;
                Date now = null;
                try {
                     data = dateFormat.parse(document.getString("Time"));
                     now = dateFormat.parse(dateFormat.format(new Date()));

                } catch (ParseException ex) {
                    Logger.getLogger(CurrentTour.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(data!=null&&data.equals(now)&&now!=null)
                {
                    txtBookingID.setText("Mã vé: "+ document.getId());
                    txtTime.setText(document.getString("Time"));
                    String TourID = document.getString("MainTourID");
                    System.out.println(TourID);
                    DocumentReference tourInfoDoc = firestore.collection("admin").document("AllTours").collection("TourInfo").document(TourID);
                    txtName.setText(tourInfoDoc.get().get().getString("TourName"));
                    txtTimeLength.setText(tourInfoDoc.get().get().getString("TourLength"));
                    txtTimeStart.setText(tourInfoDoc.get().get().getString("TourTime"));
                    List<String> tourImagesBase64;
                    tourImagesBase64 = (List<String>) tourInfoDoc.get().get().get("TourImages");
                    ImageIcon tempIMG = convertBase64ToImageIcon(tourImagesBase64);
                    List<Map<String, Object>>Acctivities = (List<Map<String, Object>>) tourInfoDoc.get().get().get("TourActivities");
                        for (Map<String, Object> tourActivity : Acctivities){
                            System.out.println(tourActivity);
                            String temp =(String) tourActivity.get("Length");
                            Activity t2 = new Activity();
                            t2.setAccName((String) tourActivity.get("Name"));
                            t2.setDescription((String) tourActivity.get("Description"));
                            t2.setDuriation(temp);
                            pnlAcc.add(t2,"wrap, w 95%");
                        }
                    imagePanel3.setbackgroundImage(tempIMG);
                    haveOne.setVisible(true);
                    none.setVisible(false);
                    return;
                }
               
            }
            haveOne.setVisible(false);
            none.setVisible(true);
        }
        catch (ExecutionException | InterruptedException ex) {
            haveOne.setVisible(false);
            none.setVisible(true);
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

        txt = new javax.swing.JLabel();
        cover = new javax.swing.JLayeredPane();
        haveOne = new uitravel.Components.RoundedPanel();
        imagePanel3 = new uitravel.Components.ImagePanel();
        txtTime = new javax.swing.JLabel();
        txtTimeStart = new javax.swing.JLabel();
        txtTimeStart1 = new javax.swing.JLabel();
        txtTimeStart3 = new javax.swing.JLabel();
        txtTimeStart4 = new javax.swing.JLabel();
        txtTimeStart5 = new javax.swing.JLabel();
        customLineComponent1 = new uitravel.Components.CustomLineComponent();
        txtTimeLength = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pnlAcc = new javax.swing.JPanel();
        txtBookingID = new javax.swing.JLabel();
        txtName = new javax.swing.JTextArea();
        none = new uitravel.Components.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        imagePanel1 = new uitravel.Components.ImagePanel();
        jTextArea1 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(918, 640));

        txt.setBackground(new java.awt.Color(255, 255, 255));
        txt.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txt.setText("Chuyến đi hiện tại");

        cover.setPreferredSize(new java.awt.Dimension(795, 920));

        haveOne.setBackground(new java.awt.Color(255, 255, 255));
        haveOne.setBorderColor(new java.awt.Color(0, 102, 255));
        haveOne.setWithBorder(true);

        imagePanel3.setbackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/resources/tourInfo.png"))); // NOI18N
        imagePanel3.setisTransparent(false);
        imagePanel3.setRadius(30);

        javax.swing.GroupLayout imagePanel3Layout = new javax.swing.GroupLayout(imagePanel3);
        imagePanel3.setLayout(imagePanel3Layout);
        imagePanel3Layout.setHorizontalGroup(
            imagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        imagePanel3Layout.setVerticalGroup(
            imagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );

        txtTime.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTime.setText("jLabel3");

        txtTimeStart.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTimeStart.setText("jLabel3");

        txtTimeStart1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTimeStart1.setText("Tình trạng:");

        txtTimeStart3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTimeStart3.setText("Tốt");

        txtTimeStart4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTimeStart4.setText("Giờ xuất phát:");

        txtTimeStart5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTimeStart5.setText("Thời gian đi:");

        customLineComponent1.setLineColor(new java.awt.Color(153, 255, 255));

        txtTimeLength.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTimeLength.setText("1 giờ");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Clock.png"))); // NOI18N
        jLabel5.setText("Thời gian : ");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setText("Thông tin hành trình");

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setMaximumSize(new java.awt.Dimension(470, 209));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(470, 209));

        pnlAcc.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlAccLayout = new javax.swing.GroupLayout(pnlAcc);
        pnlAcc.setLayout(pnlAccLayout);
        pnlAccLayout.setHorizontalGroup(
            pnlAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlAccLayout.setVerticalGroup(
            pnlAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(pnlAcc);

        txtBookingID.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        txtBookingID.setText("jLabel3");

        txtName.setEditable(false);
        txtName.setColumns(20);
        txtName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtName.setLineWrap(true);
        txtName.setRows(5);
        txtName.setText("ádashdkashdkajhsdkjahsdkahskdhakjhdkahdkahkdhakjdashdkjashkdjhaskjdakjshdkjashdkjahsdkjahskjdasdas");
        txtName.setWrapStyleWord(true);
        txtName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtName.setEnabled(false);
        txtName.setMargin(new java.awt.Insets(2, 0, 2, 6));
        txtName.setOpaque(false);

        javax.swing.GroupLayout haveOneLayout = new javax.swing.GroupLayout(haveOne);
        haveOne.setLayout(haveOneLayout);
        haveOneLayout.setHorizontalGroup(
            haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(haveOneLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, haveOneLayout.createSequentialGroup()
                        .addComponent(imagePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(haveOneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTimeStart5)
                                    .addComponent(txtTimeStart4)
                                    .addComponent(txtTimeStart1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, haveOneLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(txtTime))
                                    .addComponent(txtTimeStart3)
                                    .addComponent(txtTimeStart))
                                .addContainerGap(277, Short.MAX_VALUE))
                            .addGroup(haveOneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBookingID)))))
                    .addGroup(haveOneLayout.createSequentialGroup()
                        .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customLineComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(haveOneLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(haveOneLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTimeLength))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        haveOneLayout.setVerticalGroup(
            haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(haveOneLayout.createSequentialGroup()
                .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(haveOneLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtBookingID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTime)
                            .addComponent(txtTimeStart5))
                        .addGap(18, 18, 18)
                        .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimeStart)
                            .addComponent(txtTimeStart4))
                        .addGap(18, 18, 18)
                        .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimeStart1)
                            .addComponent(txtTimeStart3)))
                    .addGroup(haveOneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imagePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(customLineComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(haveOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTimeLength))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(noneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        cover.setLayer(haveOne, javax.swing.JLayeredPane.DEFAULT_LAYER);
        cover.setLayer(none, javax.swing.JLayeredPane.DRAG_LAYER);

        javax.swing.GroupLayout coverLayout = new javax.swing.GroupLayout(cover);
        cover.setLayout(coverLayout);
        coverLayout.setHorizontalGroup(
            coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(haveOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
            .addGroup(coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(coverLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(none, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        coverLayout.setVerticalGroup(
            coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coverLayout.createSequentialGroup()
                .addComponent(haveOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(coverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(coverLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(none, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cover, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt)
                .addGap(18, 18, 18)
                .addComponent(cover, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane cover;
    private uitravel.Components.CustomLineComponent customLineComponent1;
    private uitravel.Components.RoundedPanel haveOne;
    private uitravel.Components.ImagePanel imagePanel1;
    private uitravel.Components.ImagePanel imagePanel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private uitravel.Components.RoundedPanel none;
    private javax.swing.JPanel pnlAcc;
    private javax.swing.JLabel txt;
    private javax.swing.JLabel txtBookingID;
    private javax.swing.JTextArea txtName;
    private javax.swing.JLabel txtTime;
    private javax.swing.JLabel txtTimeLength;
    private javax.swing.JLabel txtTimeStart;
    private javax.swing.JLabel txtTimeStart1;
    private javax.swing.JLabel txtTimeStart3;
    private javax.swing.JLabel txtTimeStart4;
    private javax.swing.JLabel txtTimeStart5;
    // End of variables declaration//GEN-END:variables
}
