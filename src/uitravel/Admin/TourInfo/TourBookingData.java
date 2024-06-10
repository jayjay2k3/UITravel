/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Admin.TourInfo;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.raven.glasspanepopup.GlassPanePopup;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.RandomStringUtils;
import uitravel.Admin.component.cell.TableActionCellRender;
import uitravel.AdminMain;

/**
 *
 * @author ACER
 */
public class TourBookingData extends javax.swing.JPanel {
    Firestore firestore = FirestoreClient.getFirestore();

    private String tourID;
    public void setTourID(String tourID){
        this.tourID = tourID;
        txtID.setText(tourID);
        retrieveTourInfo();
    }
    public TourBookingData() {
        initComponents();
        init();
    }
    private void init(){
        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<5;i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        chooseTime1.setVisible(false);
        chooseTime1.AddTour((ActionEvent e) -> {
                DefaultTableModel  model =  (DefaultTableModel)table.getModel();
                Object []row = new Object[]{RandomStringUtils.randomAlphanumeric(10),chooseTime1.getDate(), String.valueOf(chooseTime1.getNumber()), "0","Chưa bắt đầu",""};
                System.out.println(Arrays.toString(row));
                UploadNewTourInfo(row);
                model.addRow( row);

                chooseTime1.setVisible(false);
            });
         
    }
    
    private void retrieveTourInfo() {
        DocumentReference docRef = firestore.collection("admin").document("AllTours").collection("TourInfo").document(tourID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                // Retrieve fields from the document
                txtName.setText(document.getString("TourName")); 
               
                List<String> tourImagesBase64 = (List<String>) document.get("TourImages");

                // Convert Base64 encoded images back to ImageIcon
                headerPic.setbackgroundImage(convertBase64ToImageIcon(tourImagesBase64));
                CollectionReference collection = firestore.collection("history");
                DocumentReference newHistoryDocRef = collection.document(tourID);
                CollectionReference historyCollection = newHistoryDocRef.collection("History");
                ApiFuture<QuerySnapshot> querySnapshot = historyCollection.get();
                try {
                    for (DocumentSnapshot document1 : querySnapshot.get().getDocuments()) {
                        if (document1.exists()) {
                            DefaultTableModel  model =  (DefaultTableModel)table.getModel();
                            Object []row = new Object[]{document1.getId(),document1.getString("TourDateStart"),document1.getString("TourNumberAttendants")+"/"+document1.getString("TourNumber"), document1.getString("TourRevenue"),document1.getString("TourState"),""};
                            model.addRow( row);
                            
                        }
                        else {
                            
                        }
                    }       
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(TourBookingData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        catch (HeadlessException | InterruptedException | ExecutionException e) {
        }
    }
    private void UploadNewTourInfo(Object []row) {
        CollectionReference collection = firestore.collection("history");
        DocumentReference newHistoryDocRef = collection.document(tourID);
        CollectionReference subCollectionRef = newHistoryDocRef.collection("History");
        DocumentReference newDocRef = subCollectionRef.document((String) row[0]);

        // DocumentReference document = firestore.collection("admin").document("AllTours").collection("TourInfo").document(tourID).collection(";

        Map<String, Object> tourData = new HashMap<>();

        tourData.put("TourDateStart",row[1] );
        tourData.put("TourNumber", row[2]);
        tourData.put("TourNumberAttendants", "0");
        tourData.put("TourRevenue", row[3]);
        tourData.put("TourState", row[4]);

        newDocRef.set(tourData);
        JOptionPane.showMessageDialog(null,
               "Tạo chuyến đi mới thành công!",
               "Thông báo!",
               JOptionPane.INFORMATION_MESSAGE);
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bbg = new uitravel.Components.RoundedPanel();
        bg = new javax.swing.JLayeredPane();
        headerPic = new uitravel.Components.ImagePanel();
        txtName = new javax.swing.JTextArea();
        lblEdit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();
        roundedPanel1 = new uitravel.Components.RoundedPanel();
        lblAdd = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        chooseTime1 = new uitravel.Admin.component.ChooseTime();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1100, 738));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        bbg.setBackground(new java.awt.Color(255, 255, 255));
        bbg.setBorderColor(new java.awt.Color(204, 204, 204));
        bbg.setPreferredSize(new java.awt.Dimension(1100, 700));
        bbg.setWithBorder(true);
        bbg.setLayout(new javax.swing.BoxLayout(bbg, javax.swing.BoxLayout.LINE_AXIS));

        bg.setPreferredSize(new java.awt.Dimension(1100, 700));

        headerPic.setbackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/resources/BigBeach_GettyImages-874980426-ezgif.com-webp-to-png-converter.png"))); // NOI18N
        headerPic.setisTransparent(false);
        headerPic.setRadius(30);
        headerPic.setwithBlack(true);

        txtName.setEditable(false);
        txtName.setColumns(20);
        txtName.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.setLineWrap(true);
        txtName.setRows(5);
        txtName.setText("Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Bo");
        txtName.setWrapStyleWord(true);
        txtName.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtName.setEnabled(false);
        txtName.setOpaque(false);
        txtName.setPreferredSize(new java.awt.Dimension(955, 179));

        lblEdit.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        lblEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Compose.png"))); // NOI18N
        lblEdit.setText("Chỉnh sửa");
        lblEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEditMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblEditMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID:");

        txtID.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        txtID.setForeground(new java.awt.Color(255, 255, 255));
        txtID.setText("21521857");

        headerPic.setLayer(txtName, javax.swing.JLayeredPane.PALETTE_LAYER);
        headerPic.setLayer(lblEdit, javax.swing.JLayeredPane.PALETTE_LAYER);
        headerPic.setLayer(jLabel2, javax.swing.JLayeredPane.PALETTE_LAYER);
        headerPic.setLayer(txtID, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout headerPicLayout = new javax.swing.GroupLayout(headerPic);
        headerPic.setLayout(headerPicLayout);
        headerPicLayout.setHorizontalGroup(
            headerPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPicLayout.createSequentialGroup()
                .addGroup(headerPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(headerPicLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(headerPicLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEdit)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        headerPicLayout.setVerticalGroup(
            headerPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPicLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(headerPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID)
                    .addComponent(lblEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(524, Short.MAX_VALUE))
        );

        lblBack.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        lblBack.setForeground(new java.awt.Color(204, 255, 255));
        lblBack.setText("Quay lại trang chủ");

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblAdd.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        lblAdd.setForeground(new java.awt.Color(0, 204, 0));
        lblAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Add.png"))); // NOI18N
        lblAdd.setText("Thêm chuyến đi");
        lblAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAddMousePressed(evt);
            }
        });

        table.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ngày đi", "Số lượng khách", "Doanh thu", "Tình trạng", " "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(40);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAdd))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblAdd)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        bg.setLayer(headerPic, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(lblBack, javax.swing.JLayeredPane.PALETTE_LAYER);
        bg.setLayer(roundedPanel1, javax.swing.JLayeredPane.PALETTE_LAYER);
        bg.setLayer(chooseTime1, javax.swing.JLayeredPane.DRAG_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(lblBack, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(913, Short.MAX_VALUE)))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(10, 10, 10)))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(0, 350, Short.MAX_VALUE)
                    .addComponent(chooseTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 350, Short.MAX_VALUE)))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(headerPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(lblBack)
                    .addContainerGap(678, Short.MAX_VALUE)))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                    .addContainerGap(275, Short.MAX_VALUE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)))
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(chooseTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        bbg.add(bg);

        add(bbg);
    }// </editor-fold>//GEN-END:initComponents

    private void lblAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddMousePressed
       chooseTime1.setVisible(true);
    }//GEN-LAST:event_lblAddMousePressed

    private void lblEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditMouseEntered
        lblEdit.setForeground(new Color(200,200,200));
    }//GEN-LAST:event_lblEditMouseEntered

    private void lblEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditMouseExited
        lblEdit.setForeground(new Color(255,255,255));

    }//GEN-LAST:event_lblEditMouseExited

    private void lblEditMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditMousePressed
        callAddATour(this);
    }//GEN-LAST:event_lblEditMousePressed
    private void callAddATour(Component component) {
        JFrame frame = getFrame(component);
        if (frame != null) {
            ((AdminMain) frame).EditTour(tourID);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private uitravel.Components.RoundedPanel bbg;
    private javax.swing.JLayeredPane bg;
    private uitravel.Admin.component.ChooseTime chooseTime1;
    private uitravel.Components.ImagePanel headerPic;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblEdit;
    private uitravel.Components.RoundedPanel roundedPanel1;
    private javax.swing.JTable table;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextArea txtName;
    // End of variables declaration//GEN-END:variables
}
