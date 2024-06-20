/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Admin;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import static com.google.common.collect.ComparisonChain.start;
import com.google.firebase.cloud.FirestoreClient;
import com.raven.glasspanepopup.GlassPanePopup;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import uitravel.Admin.TourInfo.ADShortTourInfo;
import uitravel.Admin.TourInfo.TourBookingData;
import uitravel.AdminMain;
import uitravel.Components.Loading;
import uitravel.UserSearch;

/**
 *
 * @author ACER
 */
public class saoke extends javax.swing.JPanel {

    Firestore firestore = FirestoreClient.getFirestore();
    Double total =0.0;
    String temp = null;

    public saoke() {
        initComponents();
        SwingWorker<Void, Object[]> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                retrieveTourInfowithDate("all");
                return null;
            }
            @Override
            protected void process(List<Object[]> chunks) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                for (Object[] row : chunks) {
                    model.addRow(row);
                }
            }
            @Override
            protected void done() {
                // Ẩn màn hình loading sau khi tác vụ hoàn thành
            }
        };
        worker.execute();
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
        txtChooseDate = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setBorderColor(new java.awt.Color(153, 255, 255));
        roundedPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        roundedPanel1.setWithBorder(true);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Thống kê doanh thu");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("Lọc theo thời gian:");

        txtChooseDate.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChooseDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "1 tháng", "3 tháng", "2 tháng", "6 tháng", "1 năm", "" }));
        txtChooseDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChooseDateActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Tour", "Tên Tour", "Số hành khách", "Tổng doanh thu"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("Tổng:");

        txtTotal.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotal.setText("0");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtChooseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal)
                .addGap(105, 105, 105))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(291, 291, 291))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtChooseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTotal))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        add(roundedPanel1);
    }// </editor-fold>//GEN-END:initComponents
    private void retrieveTourInfowithDate(String date) {
                 System.out.println("Đang lấy data");

        try {
            CollectionReference collection = firestore.collection("history");
            ApiFuture<QuerySnapshot> querySnapshot = collection.get();
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                if (document.exists()) {
                        CollectionReference subCollection = document.getReference().collection("History");

                        // Asynchronously retrieve all documents from the subcollection
                        ApiFuture<QuerySnapshot> subFuture = subCollection.get();
                        List<QueryDocumentSnapshot> subDocuments = subFuture.get().getDocuments();
                        String id = document.getId();
                        String Name = firestore.collection("admin").document("AllTours").collection("TourInfo").document(id).get().get().getString("TourName");       
                        int numberOpen= 0;
                        int numberAttendants = 0;
                        Double allCosts = 0.0;
                        for(DocumentSnapshot childDoc : subDocuments){
                            if(childDoc.exists()){
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                Boolean check = false;
                                 if(date.equals("all")){
                                    numberOpen +=Integer.valueOf(childDoc.getString("TourNumberAttendants"));
                                    numberAttendants+=Integer.valueOf(childDoc.getString("TourNumber"));
                                    allCosts+=Double.valueOf(childDoc.getString("TourRevenue"));
                                 }
                                 else{
                                      try {
                                    int countMonth = Integer.parseInt(date);

                                    Date tourDate = dateFormat.parse(childDoc.getString("TourDateStart"));
                                    LocalDate startDateTour = tourDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                    LocalDate currentDate = LocalDate.now();
                                    Period period = Period.between(startDateTour, currentDate);
                                    int month = period.getMonths() + period.getYears()*12;
                                    if(countMonth>=month)
                                        check = true;
                                } catch (ParseException ex) {
                                    Logger.getLogger(saoke.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    if(check){
                                        numberOpen +=Integer.valueOf(childDoc.getString("TourNumberAttendants"));
                                        numberAttendants+=Integer.valueOf(childDoc.getString("TourNumber"));
                                        allCosts+=Double.valueOf(childDoc.getString("TourRevenue"));
                                    }
                                 }
                               
                            }
                        }
                        total+=allCosts;
                        Object []row = new Object[]{id,Name,String.valueOf(numberOpen) + "/" + String.valueOf(numberAttendants),allCosts};
                        SwingUtilities.invokeLater(() -> {
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.addRow(row);
                            txtTotal.setText(String.valueOf(total));
                        });

                    
                }
                
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(AdminMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void txtChooseDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChooseDateActionPerformed
        switch ((String) txtChooseDate.getSelectedItem()){
            case "1 tháng" -> temp ="1";
            case "2 tháng" -> temp ="2";
            case "3 tháng" -> temp ="3";
            case "6 tháng" -> temp ="6";
            case "1 năm" -> temp ="12";
            default-> temp="all";
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        System.out.println(temp);
        txtTotal.setText("0");
        SwingWorker<Void, Object[]> worker2 = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                retrieveTourInfowithDate(temp);
                return null;
            }
            @Override
            protected void process(List<Object[]> chunks) {
                for (Object[] row : chunks) {
                    model.addRow(row);
                }
            }
            @Override
            protected void done() {
                // Ẩn màn hình loading sau khi tác vụ hoàn thành
            }
        };
        worker2.execute();
    }//GEN-LAST:event_txtChooseDateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private uitravel.Components.RoundedPanel roundedPanel1;
    private javax.swing.JTable table;
    private javax.swing.JComboBox<String> txtChooseDate;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
