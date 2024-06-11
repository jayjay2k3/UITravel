/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Admin;

import FireBase.FirebaseInitializer;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class AddAPlace extends javax.swing.JPanel {

    private ImageIcon img;
    public AddAPlace() {
        initComponents();
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnSubmit = new uitravel.Components.MyButton();
        jLabel9 = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Chọn hình ảnh");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Đặt tên nơi đến");

        txtName.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Xác nhận");
        btnSubmit.setBorderColor(new java.awt.Color(255, 102, 0));
        btnSubmit.setColor(new java.awt.Color(255, 102, 0));
        btnSubmit.setColorClick(new java.awt.Color(255, 102, 0));
        btnSubmit.setColorOver(new java.awt.Color(255, 102, 0));
        btnSubmit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSubmit.setRadius(20);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thêm nơi đến mới");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(roundedPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAdd))))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnAdd))
                .addGap(18, 18, 18)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        add(roundedPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        if(img==null){
            JOptionPane.showMessageDialog(null,
                "Vui lòng chọn hình ảnh!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        } else if(txtName.getText().equals("")){
             JOptionPane.showMessageDialog(null,
                "Vui lòng nhập nơi đến!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        }else{
             uploadImage(img,txtName.getText());
             this.hide();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        img = chooseAImage();
        if(img==null){
            JOptionPane.showMessageDialog(null,
                "Vui lòng chọn hình ảnh!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        } 
    }//GEN-LAST:event_btnAddActionPerformed
    private ImageIcon chooseAImage() {

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
    public void uploadImage(ImageIcon imageIcon,String place ) {
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
        userData.put("Background", imageDataString);
        DocumentReference docRef = firestore.collection("Place").document(place);
        docRef.set(userData);
        JOptionPane.showMessageDialog(null,
                "Thêm nơi đến mới thành công!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Lỗi khi tải lên ảnh!",
                    "Thông báo!",
                    JOptionPane.ERROR_MESSAGE);
        }
    } 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private uitravel.Components.MyButton btnSubmit;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private uitravel.Components.RoundedPanel roundedPanel1;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}