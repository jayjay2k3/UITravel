/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.User.UserInfo.Component;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import net.miginfocom.swing.MigLayout;
import uitravel.Components.TourInfo.Component.Comment;
import java.util.Random;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import uitravel.Components.TourInfo.Component.Comment1;
import uitravel.Components.startRating.EventStarRating;
import uitravel.User.MainUI.Chat.CmtLeft;
import uitravel.User.MainUI.Chat.CmtRight;

/**
 *
 * @author ACER
 */
public class HistoryFullInfo extends javax.swing.JPanel {
    Firestore firestore = FirestoreClient.getFirestore();
    private List<Map<String,Object>> Cmts = new ArrayList<>();
    private static final Random RANDOM = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private String TourID;
    private String UID;
    private String userName;
    private String bookingID;

    private boolean isCmtBefore;
    private int numberCMt;
    private int oldRating;
    private int oldUserRating;
    private int currentRating;

    public HistoryFullInfo() {
        this.isCmtBefore = false;
        initComponents();
        init();
    }
    private void init(){
        cmtCover.setLayout(new MigLayout("fillx"));
        rating.addEventStarRating(new EventStarRating(){
            @Override
            public void selected(int start){
                if(isCmtBefore){
                    currentRating = (oldRating*numberCMt-oldUserRating+rating.getStar())/numberCMt;
                  
                }
                else{
                    currentRating = (oldRating*numberCMt+rating.getStar())/(numberCMt+1);
                }
                System.out.println(currentRating);  

            }
        });

    }
    public void setTourID(String id){
        this.TourID = id;
        loadCMT();
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setBookingID(String userName){
        this.bookingID = userName;
        txtBookingID.setText(userName);
    }
    public void setUID(String id){
        this.UID = id;
    }
    public void setPic(ImageIcon img){
        pic.setbackgroundImage(img);
    }
     private void loadCMT(){
        try {
            DocumentReference tourInfoDoc = firestore.collection("admin").document("AllTours").collection("TourInfo").document(TourID);
            ApiFuture<DocumentSnapshot> future = tourInfoDoc.get();
            DocumentSnapshot document = null;
            document = future.get();
            if (document.exists()) {

                Cmts = (List<Map<String, Object>>)document.get("Comments");
                if(Cmts!=null){
                    for (Map<String, Object> cmt : Cmts){
                        if(!cmt.get("UserID").equals(UID)){
                            CmtLeft t = new CmtLeft();
                            t.setAttitude((String) cmt.get("Attitude"));
                            t.setText((String) cmt.get("Content"));
                            t.setUserName((String) cmt.get("UserName"));
                            cmtCover.add(t,"gapleft 20,wrap, w ::80%");
                        }
                        else{
                            isCmtBefore = true;
                            CmtRight t = new CmtRight();
                            t.setAttitude((String) cmt.get("Attitude"));
                            t.setText((String) cmt.get("Content"));
                            t.setUserName("Bạn");
                            cmtCover.add(t,"wrap,al right, w ::80%");
                            oldUserRating =  Integer.parseInt((String) cmt.get("UserRating")) ;
                            rating.setStar(oldUserRating);
                        }
                        
                    }
                Map<String,String> Rating = (Map<String,String>) document.get("TourRating");
                oldRating = Integer.parseInt(Rating.get("Rate"));
                numberCMt = Integer.parseInt(Rating.get("NumberVotted"));
                cmtCover.repaint();
                cmtCover.revalidate();
            }  else{
                    Cmts =  new ArrayList<>();
                }
                
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(HistoryFullInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     
private void updateCommentByUserID(Map<String, Object> updatedComment) {
    DocumentReference tourInfoDoc = firestore.collection("admin").document("AllTours").collection("TourInfo").document(TourID);
    ApiFuture<DocumentSnapshot> future = tourInfoDoc.get();
    {
            try {
                DocumentSnapshot document = future.get();
                if (document.exists()) {
                    Cmts = (List<Map<String, Object>>) document.get("Comments");
                    if (Cmts != null) {
                        boolean found = false;
                        for (Map<String, Object> comment : Cmts) {
                            if (comment.get("UserID").equals(UID)) {
                                // Update the comment fields
                                System.out.println("Old Content: " + comment.get("Content"));
                                System.out.println("New Content: " + updatedComment.get("Content"));
                                comment.putAll(updatedComment);
                                found = true;
                                break;
                            }
                        }
                        
                        if (found) {
                            System.out.println("Updated Comments List: " + Cmts);
                            ApiFuture<WriteResult> writeResult = tourInfoDoc.update("Comments", Cmts);
                            // Wait for the write to complete and check for errors
                            WriteResult result = writeResult.get();
                            System.out.println("Write timestamp: " + result.getUpdateTime());
                            System.out.println("Comment updated successfully.");
                        } else {
                            System.out.println("No comment found with the given UserID.");
                        }
                    } else {
                        System.out.println("No comments found in the document.");
                    }
                } else {
                    System.out.println("No such document!");
                }   } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(HistoryFullInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        roundedPanel1 = new uitravel.Components.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBookingID = new javax.swing.JLabel();
        roundedPanel2 = new uitravel.Components.RoundedPanel();
        txtName2 = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtAdultNum1 = new javax.swing.JLabel();
        txtChildrenNum1 = new javax.swing.JLabel();
        txtInfantNum1 = new javax.swing.JLabel();
        txtCost = new javax.swing.JLabel();
        ttxLabl1 = new javax.swing.JLabel();
        txtInfantNum = new javax.swing.JLabel();
        txtChildrenNum = new javax.swing.JLabel();
        txtAdultNum = new javax.swing.JLabel();
        txtTime = new javax.swing.JLabel();
        txtTickets = new javax.swing.JLabel();
        pic = new uitravel.Components.ImagePanel();
        txtState = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cmtCover = new javax.swing.JPanel();
        cmt = new uitravel.Components.TextField();
        btnCmt = new uitravel.Components.MyButton();
        rating = new uitravel.Components.startRating.StarRating();

        setOpaque(false);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Thông tin chuyến đi:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel2.setText("Mã vé:");

        txtBookingID.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        txtBookingID.setText("Mã đặt chỗ:");

        roundedPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel2.setBorderColor(new java.awt.Color(0, 51, 204));
        roundedPanel2.setWithBorder(true);

        txtName2.setEditable(false);
        txtName2.setColumns(20);
        txtName2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtName2.setLineWrap(true);
        txtName2.setRows(5);
        txtName2.setText("Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour ");
        txtName2.setWrapStyleWord(true);
        txtName2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtName2.setEnabled(false);
        txtName2.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel10.setText("Số lượng vé:");

        txtAdultNum1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtAdultNum1.setText("Người lớn ");

        txtChildrenNum1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChildrenNum1.setText("Trẻ em");

        txtInfantNum1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtInfantNum1.setText("Trẻ sơ sinh");

        txtCost.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtCost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtCost.setText("Tổng cộng");

        ttxLabl1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        ttxLabl1.setText("Tổng giá:");

        txtInfantNum.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtInfantNum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtInfantNum.setText("Trẻ sơ sinh");

        txtChildrenNum.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChildrenNum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtChildrenNum.setText("Trẻ sơ sinh");

        txtAdultNum.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtAdultNum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtAdultNum.setText("Trẻ sơ sinh");

        txtTime.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTime.setText("Th 5, 6 thg 6 08:00");

        txtTickets.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTickets.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTickets.setText("Số lượng vé:");

        pic.setisTransparent(false);
        pic.setRadius(30);

        javax.swing.GroupLayout picLayout = new javax.swing.GroupLayout(pic);
        pic.setLayout(picLayout);
        picLayout.setHorizontalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        picLayout.setVerticalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtName2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtAdultNum1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtChildrenNum1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtInfantNum1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10)
                                    .addComponent(ttxLabl1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtInfantNum, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtChildrenNum, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAdultNum, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTickets, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53))
                            .addComponent(txtTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtName2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTime))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTickets))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAdultNum1)
                    .addComponent(txtAdultNum))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChildrenNum1)
                    .addComponent(txtChildrenNum))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInfantNum1)
                    .addComponent(txtInfantNum))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttxLabl1)
                    .addComponent(txtCost))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        txtState.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        txtState.setText("Mã đặt chỗ:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel3.setText("Tình trạng:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel4.setText("Đánh giá:");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        cmtCover.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout cmtCoverLayout = new javax.swing.GroupLayout(cmtCover);
        cmtCover.setLayout(cmtCoverLayout);
        cmtCoverLayout.setHorizontalGroup(
            cmtCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );
        cmtCoverLayout.setVerticalGroup(
            cmtCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 139, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(cmtCover);

        cmt.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cmt.setLabelText("Bạn nghĩ gì về chuyến đi trên?");

        btnCmt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Send.png"))); // NOI18N
        btnCmt.setBorderColor(new java.awt.Color(0, 0, 255));
        btnCmt.setColor(new java.awt.Color(0, 0, 255));
        btnCmt.setColorClick(new java.awt.Color(51, 102, 255));
        btnCmt.setColorOver(new java.awt.Color(0, 51, 255));
        btnCmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCmtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBookingID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtState))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(cmt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCmt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(85, 85, 85))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(rating, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBookingID)
                    .addComponent(jLabel3)
                    .addComponent(txtState))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(rating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCmt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );

        add(roundedPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCmtActionPerformed
        if(cmt.getText().equals("")){
            
        }
        else{
            if(isCmtBefore){
                    currentRating = (oldRating*numberCMt-oldUserRating+rating.getStar())/numberCMt;
                  
                }
                else{
                    currentRating = (oldRating*numberCMt+rating.getStar())/(numberCMt+1);
                }
            if(!isCmtBefore){
                cmtCover.removeAll();
                Map<String,Object> t = new HashMap<>();
                t.put("UserName", userName);
                t.put("UserID", UID);
                t.put("Content", cmt.getText());
                String attitude = getAttitudePredict(cmt.getText());
                t.put("Attitude", attitude);
                t.put("UserRating", String.valueOf(rating.getStar()));
                Cmts.add(t);
                DocumentReference tourInfoDoc = firestore.collection("admin").document("AllTours").collection("TourInfo").document(TourID);
                tourInfoDoc.update("Comments",Cmts);
                
                Map<String,String> Rating = new HashMap<>();
                Rating.put("Rate", String.valueOf(currentRating));
                numberCMt++;
                Rating.put("NumberVotted", String.valueOf(numberCMt));
                tourInfoDoc.update("TourRating",Rating);
                oldUserRating= currentRating;
                CmtRight chat = new CmtRight();
                chat.setAttitude(attitude);
                chat.setText(cmt.getText());
                chat.setUserName(userName);
                isCmtBefore =true;
                cmtCover.add(chat,"wrap,al right, w ::80%");
                cmtCover.repaint();
                cmtCover.revalidate();
            }else{
                cmtCover.removeAll();
                Map<String,Object> t = new HashMap<>();
                t.put("UserName", userName);
                t.put("UserID", UID);
                t.put("Content", cmt.getText());
                t.put("Attitude", getAttitudePredict(cmt.getText()));
                t.put("UserRating", String.valueOf(rating.getStar()));
                
                updateCommentByUserID(t);

                DocumentReference tourInfoDoc = firestore.collection("admin").document("AllTours").collection("TourInfo").document(TourID);
                tourInfoDoc.update("Comments",Cmts);
                Map<String,String> Rating = new HashMap<>();
                Rating.put("Rate", String.valueOf(currentRating));
                Rating.put("NumberVotted", String.valueOf(numberCMt));
                tourInfoDoc.update("TourRating",Rating);
                cmt.setText("");
                 if(Cmts!=null){
                    for (Map<String, Object> cmt : Cmts){
                        if(!cmt.get("UserID").equals(UID)){
                            CmtLeft chat = new CmtLeft();
                            chat.setAttitude((String) cmt.get("Attitude"));
                            chat.setText((String) cmt.get("Content"));
                            chat.setUserName((String) cmt.get("UserName"));
                            cmtCover.add(chat,"gapleft 20,wrap, w ::80%");
                        }
                        else{
                            isCmtBefore = true;
                            CmtRight chat = new CmtRight();
                            chat.setAttitude((String) cmt.get("Attitude"));
                            chat.setText((String) cmt.get("Content"));
                            chat.setUserName("Bạn");
                            cmtCover.add(chat,"wrap,al right, w ::80%");
                            oldUserRating =  Integer.parseInt((String) cmt.get("UserRating"));
                            rating.setStar(oldUserRating);
                        }
                        
                    }
                    cmtCover.repaint();
                    cmtCover.revalidate();

            }
        }
        }
        
    }//GEN-LAST:event_btnCmtActionPerformed
    private String getAttitudePredict(String inputData){
        String basePath = System.getProperty("user.dir");
        String scriptPath = Paths.get(basePath, "src",  "model", "model.py").toString();
        try {
            // Construct the command to run the Python script
            ProcessBuilder pb = new ProcessBuilder("python", scriptPath, inputData);
            pb.redirectErrorStream(true); // Redirect error stream to the output stream
            Process process = pb.start();

            // Capture the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
                break;
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Prediction result: " + result.toString());
                System.out.println(                result.toString().equals("POSITIVE"));
                if(result.toString().equals("POSITIVE")){
                    return "Tích cực";
                }
                 if(result.toString().equals("NEGATIVE")){
                    return "Tiêu cực";
                }
                  if(result.toString().equals("NEUTRAL")){
                    return "";
                }
                reader.close();
                process.destroy(); // Ensure the process is terminated
            } else {
                System.out.println("Error occurred: " + result.toString());
            }

        } catch (IOException | InterruptedException e) {
        }
        return "";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private uitravel.Components.MyButton btnCmt;
    private uitravel.Components.TextField cmt;
    private javax.swing.JPanel cmtCover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private uitravel.Components.ImagePanel pic;
    private uitravel.Components.startRating.StarRating rating;
    private uitravel.Components.RoundedPanel roundedPanel1;
    private uitravel.Components.RoundedPanel roundedPanel2;
    private javax.swing.JLabel ttxLabl1;
    private javax.swing.JLabel txtAdultNum;
    private javax.swing.JLabel txtAdultNum1;
    private javax.swing.JLabel txtBookingID;
    private javax.swing.JLabel txtChildrenNum;
    private javax.swing.JLabel txtChildrenNum1;
    private javax.swing.JLabel txtCost;
    private javax.swing.JLabel txtInfantNum;
    private javax.swing.JLabel txtInfantNum1;
    private javax.swing.JTextArea txtName2;
    private javax.swing.JLabel txtState;
    private javax.swing.JLabel txtTickets;
    private javax.swing.JLabel txtTime;
    // End of variables declaration//GEN-END:variables
}
