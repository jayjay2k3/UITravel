/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.User.UserInfo;

import FireBase.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.raven.datechooser.DateChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import uitravel.UserMain;

/**
 *
 * @author ACER
 */
public class AccountInfo extends javax.swing.JPanel {

    private MouseListener eventChangePass;
    private String oldPass;
    private String uid;

    private DateChooser selectDate = new DateChooser();
    public AccountInfo() {
        initComponents();
        init();
    }
    public void setUID(String uid) throws IOException{
        this.uid = uid;
        loadDataFromFireStore(uid);
    }
    public void init(){
        selectDate.setTextField(txtChangeDOB);
    }
 private void loadDataFromFireStore(String uid) throws IOException{
        try {
            FirebaseInitializer.initialize();
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference docRef  = db.collection("user").document(uid);
            
            ApiFuture<DocumentSnapshot> future = docRef.get();
            // Block on response
            DocumentSnapshot document;  
            document = future.get();
           // System.out.println((document.getData()));
            // System.out.println(document.getString("FullName"));

            if(document.exists()){
                txtChangeName.setText(document.getString("FullName"));
                txtChangeEmail.setText(document.getString("Email"));
                txtChangeNum.setText(document.getString("PhoneNumber"));
                try{
                    txtChangeDOB.setText(document.getString("DOB"));
                    int index =GenderIndex(document.getString("Gender"));
                    txtChangeGender.setSelectedIndex(index);

                }catch(Exception e)
                    {
                    txtChangeDOB.setText("");
                    txtChangeGender.setSelectedIndex(2);
                    }
                }
            
        }
        catch (ExecutionException | InterruptedException ex) {
            Logger.getLogger(UserMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private int GenderIndex(String t){
        switch(t){
            case "Name" -> {
                return 0;
            }
            case "Nữ" -> {
                return 1;
            }
            case "Khác" -> {
                return 2;
            }
        }
        return 2;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtName = new javax.swing.JLabel();
        roundedPanel1 = new uitravel.Components.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        txtChangeName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtChangeEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtChangeNum = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtChangeDOB = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtChangeGender = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(850, 1000));

        txtName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtName.setText("Thông tin tài khoản");

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setBorderColor(new java.awt.Color(204, 204, 204));
        roundedPanel1.setWithBorder(true);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Họ và tên:");

        txtChangeName.setEditable(false);
        txtChangeName.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChangeName.setText("jTextField1");
        txtChangeName.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtChangeName.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Thay đổi");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Email:");

        txtChangeEmail.setEditable(false);
        txtChangeEmail.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChangeEmail.setText("jTextField1");
        txtChangeEmail.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtChangeEmail.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Số điện thoại:");

        txtChangeNum.setEditable(false);
        txtChangeNum.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChangeNum.setText("jTextField1");
        txtChangeNum.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtChangeNum.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("Thay đổi");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Ngày sinh:");
        jLabel9.setToolTipText("");

        txtChangeDOB.setEditable(false);
        txtChangeDOB.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChangeDOB.setText("jTextField1");
        txtChangeDOB.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtChangeDOB.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 204));
        jLabel10.setText("Thay đổi");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Giới tính:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 204));
        jLabel14.setText("Thay đổi");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel14MousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Mật khẩu:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 204));
        jLabel16.setText("Thay đổi");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel16MousePressed(evt);
            }
        });

        txtChangeGender.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChangeGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác" }));
        txtChangeGender.setEnabled(false);
        txtChangeGender.setPreferredSize(new java.awt.Dimension(450, 26));

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtChangeEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 204, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(txtChangeGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(txtChangeDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(txtChangeNum, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(txtChangeName, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)))
                        .addGap(57, 57, 57))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel16)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChangeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtChangeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChangeNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChangeDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtChangeGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtName)
                .addGap(18, 18, 18)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        if("Thay đổi".equals(jLabel2.getText())){
            jLabel2.setText("Lưu");
            txtChangeName.setEditable(true);
            txtChangeName.setEnabled(true);
        }
        else{
            jLabel2.setText("Thay đổi");
            txtChangeName.setEditable(false);
            txtChangeName.setEnabled(false);
            updateFireBase(txtChangeName.getText(),"FullName");

        }
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
         if("Thay đổi".equals(jLabel8.getText())){
            jLabel8.setText("Lưu");
            txtChangeNum.setEditable(true);
            txtChangeNum.setEnabled(true);
        }
        else{
            jLabel8.setText("Thay đổi");
            txtChangeNum.setEditable(false);
            txtChangeNum.setEnabled(false);
            updateFireBase(txtChangeNum.getText(),"PhoneNumber");
           
        
        }
    }//GEN-LAST:event_jLabel8MousePressed
    private void updateFireBase(String data,String field){
                Firestore db = FirestoreClient.getFirestore();
                Map<String, Object> UserData = new HashMap<>();
                UserData.put(field, data);
                DocumentReference docRef = db.collection("user").document(uid);
                ApiFuture<WriteResult> writeResult = docRef.update(UserData);
                JOptionPane.showMessageDialog(null,
                    "Thay đổi thông tin thành công!",
                    "Thông báo!",
                    JOptionPane.INFORMATION_MESSAGE);
    }
    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
         if("Thay đổi".equals(jLabel10.getText())){
            jLabel10.setText("Lưu");
            txtChangeDOB.setEditable(true);
            txtChangeDOB.setEnabled(true);
        }
        else{
            jLabel10.setText("Thay đổi");
            txtChangeDOB.setEditable(false);
            txtChangeDOB.setEnabled(false);
            updateFireBase(txtChangeDOB.getText(),"DOB");
        }
    }//GEN-LAST:event_jLabel10MousePressed

    private void jLabel16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MousePressed
        eventChangePass.mousePressed(evt);
    }//GEN-LAST:event_jLabel16MousePressed

    private void jLabel14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MousePressed
        if("Thay đổi".equals(jLabel14.getText())){
            jLabel14.setText("Lưu");
            txtChangeGender.setEnabled(true);
        }
        else{
            jLabel14.setText("Thay đổi");
            txtChangeGender.setEnabled(false);
            updateFireBase((String) txtChangeGender.getSelectedItem(),"Gender");
        }
    }//GEN-LAST:event_jLabel14MousePressed

    public void addChangePass(MouseListener event) {
        this.eventChangePass = event;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private uitravel.Components.RoundedPanel roundedPanel1;
    private javax.swing.JTextField txtChangeDOB;
    private javax.swing.JTextField txtChangeEmail;
    private javax.swing.JComboBox<String> txtChangeGender;
    private javax.swing.JTextField txtChangeName;
    private javax.swing.JTextField txtChangeNum;
    private javax.swing.JLabel txtName;
    // End of variables declaration//GEN-END:variables
}
