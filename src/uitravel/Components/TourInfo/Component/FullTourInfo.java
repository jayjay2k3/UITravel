/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Components.TourInfo.Component;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.DateSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import uitravel.Components.Activity;
import uitravel.Components.SettingActivities;
import uitravel.User.MainUI.Chat.CmtLeft;
import uitravel.User.MainUI.Chat.CmtRight;
import uitravel.User.UserInfo.Component.HistoryFullInfo;
import uitravel.UserMain;

/**
 *
 * @author ACER
 */
public class FullTourInfo extends javax.swing.JPanel {
    private ActionListener event;

    List<Comment> tourComments;
    Map<String,String> childTourID;

    List<TourImage> tourImages;
    private DateChooser selectDate;
    private String tourID;
    Firestore firestore = FirestoreClient.getFirestore();
    DefaultListModel<String> listModel = new DefaultListModel<>();
    private double price;

    public FullTourInfo() {
        this.childTourID = new HashMap<>();
        initComponents();
        init();
        
    }
    public void setTourID(String tourID){
        this.tourID = tourID;
        loadDataFromFireStore();
        loadCMT();
    }
    public void setPrice(String prices){
        this.price= Double.parseDouble(prices);
        Locale vn = new Locale("vi","VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vn);

        txtAdultPrice.setText(currencyFormatter.format(price));
        txtChildrenPrice.setText(currencyFormatter.format(price*0.7));
        txtInfantPrice.setText(currencyFormatter.format(0));

        
    }
    public int[] getTicketData(){
        int[] ticketData = new int[4];
        ticketData[0] = btnAdult.getNumber() + btnChild.getNumber()  +btnInfant.getNumber();
        ticketData[1] = btnAdult.getNumber();
        ticketData[2] = btnChild.getNumber();
        ticketData[3] = btnInfant.getNumber();
        return ticketData;
    }
    public double getPrices(){
        return  price*btnAdult.getNumber()+ 0.7*price*btnChild.getNumber();

    }
    private void LoadDate(){
        try {
            childTourID.clear();
            List<LocalDate> selectableDates = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            CollectionReference collection = firestore.collection("history");
            DocumentReference newHistoryDocRef = collection.document(tourID);
            CollectionReference subCollectionRef = newHistoryDocRef.collection("History");
            ApiFuture<QuerySnapshot> querySnapshot = subCollectionRef.get();
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                if (document.exists()) {
                    childTourID.put( document.getString("TourDateStart"),document.getId());
                    int count = Integer.parseInt(document.getString("TourNumberAttendants"));
                    int total = Integer.parseInt(document.getString("TourNumber"));
                    if(count<total)
                        selectableDates.add(LocalDate.parse(document.getString("TourDateStart"), formatter));
                }
                selectDate.setDateSelectable(new DateSelectable() {
                @Override
                public boolean isDateSelectable(Date date) {
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return !localDate.isBefore(LocalDate.now())&&selectableDates.contains(localDate);
                }
               
            });
                ZoneId defaultZoneId = ZoneId.systemDefault();
                selectDate.setSelectedDate(Date.from(selectableDates.get(0).atStartOfDay(defaultZoneId).toInstant()));
                selectDate.setTextField(txtChooseTime);
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(FullTourInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getChildID(){
        Set<String> set = childTourID.keySet();
        for (String key : set) {
            if(key.equals(txtChooseTime.getText()))
                return  childTourID.get(key);
        }
        return null;
    }
     private void loadDataFromFireStore(){
        try {
            pnlAcc.setLayout(new MigLayout("fillx"));
            DocumentReference docRef = firestore.collection("admin").document("AllTours").collection("TourInfo").document(tourID);
            // Block on response
            ApiFuture<DocumentSnapshot> future = docRef.get();
            // Block on response
            DocumentSnapshot document;  
            document = future.get();
            if(document.exists()){
                LoadDate();
                txtName.setText(document.getString("TourName"));
                List<String> tourIncludes = (List<String>) document.get("TourIncludes");
                tourIncludes.forEach(item -> {
                    listModel.addElement(item);
                });
                
                List<Map<String, Object>>Acctivities = (List<Map<String, Object>>) document.get("TourActivities");
                    int index = 0;
                    for (Map<String, Object> tourActivity : Acctivities){
                        System.out.println(tourActivity);
                        String temp =(String) tourActivity.get("Length");
                        Activity t2 = new Activity();
                        t2.setAccName((String) tourActivity.get("Name"));
                        t2.setDescription((String) tourActivity.get("Description"));
                        t2.setDuriation(temp);
                        pnlAcc.add(t2,"wrap, w 95%");
                    }
                    
                List<String> images = (List<String>) document.get("TourImages");
                List<ImageIcon> imageIcons =  new ArrayList<>();
                imageIcons = convertBase64ToImageIcon(images);
                loadIMG(imageIcons);
                btnTime.setText(document.getString("TourTime"));
                this.setPrice((String) document.get("TourPrice"));
                listItem.setModel(listModel);
                txtTime.setText(document.getString("TourLength"));
                txtInfo.setText(document.getString("TourDescription"));
                
               
            docRef.addSnapshotListener((snapshot, e) -> {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                  
                } else {
                    System.out.print("Current data: null");
                }
                });
            }
        }
        catch (ExecutionException | InterruptedException ex) {
            Logger.getLogger(UserMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
      private List<ImageIcon> convertBase64ToImageIcon(List<String> base64Images) {
        List<ImageIcon> imageIcons = new ArrayList<>();
        for (String base64Image : base64Images) {
            try {
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                BufferedImage bufferedImage = ImageIO.read(bais);
                ImageIcon imageIcon = new ImageIcon(bufferedImage);
                imageIcons.add(imageIcon);
            } catch (IOException e) {
            }
        }
        return imageIcons;
    }   
    private void init(){
        imgCover.setLayout(new MigLayout(" fillx, insets 0","[]30[]","[]"));
        cmtCover.setLayout(new MigLayout(" wrap, fill, insets 10","[]","[]10[]"));
        selectDate = new DateChooser();
        selectDate.setBetweenCharacter(" đến ");
        selectDate.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        selectDate.setDateSelectable(new DateSelectable() {
            @Override
            public boolean isDateSelectable(Date date) {
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                return false;
            }
        });
        btnAdult.addEvent((ActionEvent e) -> {
            calculateTheCost();
        });
        btnChild.addEvent((ActionEvent e) -> {
            calculateTheCost();
        });
         btnInfant.addEvent((ActionEvent e) -> {
            calculateTheCost();
        });
    }
    private void loadIMG( List<ImageIcon> img){
        tourImages = new ArrayList<>();
        for(int i = 0;i<img.size();i++){
            TourImage temp = new TourImage();
            temp.setBackgroundIcon(img.get(i));
            imgCover.add(temp,"width 490!, height 483!");
            tourImages.add(temp);
        }
    }
    
 private void loadCMT(){
        try {
            DocumentReference tourInfoDoc = firestore.collection("admin").document("AllTours").collection("TourInfo").document(tourID);
            ApiFuture<DocumentSnapshot> future = tourInfoDoc.get();
            DocumentSnapshot document = null;
            document = future.get();
            if (document.exists()) {

                List<Map<String, Object>> Cmts = (List<Map<String, Object>>)document.get("Comments");
                if(Cmts!=null){
                    for (Map<String, Object> cmt : Cmts){
                            CmtLeft t = new CmtLeft();
                            t.setAttitude((String) cmt.get("Attitude"));
                            t.setText((String) cmt.get("Content"));
                            t.setUserName((String) cmt.get("UserName"));
                            cmtCover.add(t,"wrap, w ::80%");
                    }
               
                cmtCover.repaint();
                cmtCover.revalidate();
            }  else{
                    Cmts =  new ArrayList<>();
                }
                Map<String,String> Rating = (Map<String,String>) document.get("TourRating");
                txtScore.setText(Rating.get("Rate"));
                
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(HistoryFullInfo.class.getName()).log(Level.SEVERE, null, ex);
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

        main = new javax.swing.JScrollPane();
        txtNumberComments = new javax.swing.JLayeredPane();
        txtName = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        imgCover = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtInfo = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTime = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cmtCover = new javax.swing.JLayeredPane();
        bookingCover = new javax.swing.JPanel();
        roundedPanel1 = new uitravel.Components.RoundedPanel();
        txtName1 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtAdultPrice = new javax.swing.JLabel();
        txtChildrenPrice = new javax.swing.JLabel();
        txtInfantPrice = new javax.swing.JLabel();
        txtAdultPrice1 = new javax.swing.JLabel();
        txtCost = new javax.swing.JLabel();
        myButton1 = new uitravel.Components.MyButton();
        btnAdult = new uitravel.User.UserInfo.Component.IncreaseButton();
        btnChild = new uitravel.User.UserInfo.Component.IncreaseButton();
        btnInfant = new uitravel.User.UserInfo.Component.IncreaseButton();
        txtChooseTime = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnTime = new uitravel.Components.MyButton();
        jLabel14 = new javax.swing.JLabel();
        txtScore = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        txtScore1 = new javax.swing.JLabel();
        txtScore2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listItem = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        pnlAcc = new javax.swing.JPanel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1300, 750));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        main.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        main.setOpaque(false);

        txtNumberComments.setBackground(new java.awt.Color(255, 255, 255));
        txtNumberComments.setAutoscrolls(true);
        txtNumberComments.setOpaque(true);
        txtNumberComments.setPreferredSize(new java.awt.Dimension(1300, 2200));

        txtName.setEditable(false);
        txtName.setColumns(20);
        txtName.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        txtName.setLineWrap(true);
        txtName.setRows(5);
        txtName.setText("Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour ");
        txtName.setWrapStyleWord(true);
        txtName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtName.setEnabled(false);
        txtName.setOpaque(false);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1025, 402));

        imgCover.setBackground(new java.awt.Color(255, 255, 255));
        imgCover.setOpaque(true);

        javax.swing.GroupLayout imgCoverLayout = new javax.swing.GroupLayout(imgCover);
        imgCover.setLayout(imgCoverLayout);
        imgCoverLayout.setHorizontalGroup(
            imgCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1077, Short.MAX_VALUE)
        );
        imgCoverLayout.setVerticalGroup(
            imgCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(imgCover);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setText("Mô tả chuyến đi:");

        txtInfo.setEditable(false);
        txtInfo.setColumns(20);
        txtInfo.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtInfo.setLineWrap(true);
        txtInfo.setRows(5);
        txtInfo.setText("ádadasdas\nadasdas\nádasdas");
        txtInfo.setWrapStyleWord(true);
        txtInfo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtInfo.setEnabled(false);
        txtInfo.setMaximumSize(new java.awt.Dimension(691, 104));
        txtInfo.setOpaque(false);
        txtInfo.setPreferredSize(new java.awt.Dimension(691, 104));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setText("Bao gồm:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setText("Thông tin hành trình");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Clock.png"))); // NOI18N
        jLabel5.setText("Thời gian : ");

        txtTime.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTime.setText("1 giờ");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel6.setText("Đánh giá của khách");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout cmtCoverLayout = new javax.swing.GroupLayout(cmtCover);
        cmtCover.setLayout(cmtCoverLayout);
        cmtCoverLayout.setHorizontalGroup(
            cmtCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1016, Short.MAX_VALUE)
        );
        cmtCoverLayout.setVerticalGroup(
            cmtCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(cmtCover);

        bookingCover.setBackground(new java.awt.Color(255, 255, 255));
        bookingCover.setOpaque(false);

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setBorderColor(new java.awt.Color(0, 51, 204));
        roundedPanel1.setWithBorder(true);

        txtName1.setColumns(20);
        txtName1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtName1.setLineWrap(true);
        txtName1.setRows(5);
        txtName1.setText("Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour ");
        txtName1.setWrapStyleWord(true);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel9.setText("Số lượng vé:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel10.setText("Người lớn ");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel11.setText("Trẻ em (2-17 tuổi)");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel12.setText("Trẻ sơ sinh (0-1 tuổi)");

        txtAdultPrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txtAdultPrice.setText("Người lớn ");

        txtChildrenPrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txtChildrenPrice.setText("Người lớn ");

        txtInfantPrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txtInfantPrice.setText("0 đ");

        txtAdultPrice1.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txtAdultPrice1.setText("Tổng cộng");

        txtCost.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setText("Bước tiếp");
        myButton1.setBorderColor(new java.awt.Color(0, 0, 255));
        myButton1.setColor(new java.awt.Color(0, 0, 255));
        myButton1.setColorClick(new java.awt.Color(0, 200, 255));
        myButton1.setColorOver(new java.awt.Color(0, 150, 255));
        myButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        myButton1.setRadius(10);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        btnAdult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAdultMousePressed(evt);
            }
        });

        btnChild.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnChildMousePressed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAdultPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtChildrenPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(roundedPanel1Layout.createSequentialGroup()
                            .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAdultPrice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundedPanel1Layout.createSequentialGroup()
                            .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtInfantPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnInfant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addGap(21, 21, 21)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAdultPrice))
                            .addComponent(btnAdult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChildrenPrice))
                    .addComponent(btnChild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInfantPrice))
                    .addComponent(btnInfant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(txtAdultPrice1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCost))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        txtChooseTime.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChooseTime.setText("Đã hết chỗ");
        txtChooseTime.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Vé và giá");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Chọn ngày:");

        btnTime.setForeground(new java.awt.Color(0, 0, 255));
        btnTime.setBorderColor(new java.awt.Color(0, 0, 255));
        btnTime.setColorClick(new java.awt.Color(255, 255, 255));
        btnTime.setColorOver(new java.awt.Color(204, 255, 255));
        btnTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnTime.setRadius(35);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Giờ xuất phát:");

        javax.swing.GroupLayout bookingCoverLayout = new javax.swing.GroupLayout(bookingCover);
        bookingCover.setLayout(bookingCoverLayout);
        bookingCoverLayout.setHorizontalGroup(
            bookingCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingCoverLayout.createSequentialGroup()
                .addGroup(bookingCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookingCoverLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bookingCoverLayout.createSequentialGroup()
                        .addGroup(bookingCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bookingCoverLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel8))
                            .addGroup(bookingCoverLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7))
                            .addGroup(bookingCoverLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(bookingCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtChooseTime, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(btnTime, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        bookingCoverLayout.setVerticalGroup(
            bookingCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookingCoverLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtChooseTime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(btnTime, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtScore.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtScore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Star1.png"))); // NOI18N
        txtScore.setText("4.4");
        txtScore.setIconTextGap(10);

        jProgressBar1.setBackground(new java.awt.Color(234, 234, 234));
        jProgressBar1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(255, 102, 0));
        jProgressBar1.setValue(30);
        jProgressBar1.setStringPainted(true);

        txtScore1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtScore1.setText("Điểm số");
        txtScore1.setIconTextGap(10);

        txtScore2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtScore2.setText("Đánh giá tích cực:");
        txtScore2.setIconTextGap(10);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 2, 20)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("(20 đánh giá)");

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setMaximumSize(new java.awt.Dimension(691, 200));

        listItem.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        listItem.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jScrollPane3.setViewportView(listItem);

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

        txtNumberComments.setLayer(txtName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(txtInfo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(txtTime, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(bookingCover, javax.swing.JLayeredPane.POPUP_LAYER);
        txtNumberComments.setLayer(txtScore, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(txtScore1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(txtScore2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout txtNumberCommentsLayout = new javax.swing.GroupLayout(txtNumberComments);
        txtNumberComments.setLayout(txtNumberCommentsLayout);
        txtNumberCommentsLayout.setHorizontalGroup(
            txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(313, 313, 313))
            .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTime))
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtScore)
                                            .addComponent(txtScore1))
                                        .addGap(112, 112, 112)
                                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtScore2))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bookingCover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))))
        );
        txtNumberCommentsLayout.setVerticalGroup(
            txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtTime))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtNumberCommentsLayout.createSequentialGroup()
                                .addComponent(txtScore1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtScore))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtNumberCommentsLayout.createSequentialGroup()
                                .addComponent(txtScore2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(bookingCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        main.setViewportView(txtNumberComments);

        add(main);
    }// </editor-fold>//GEN-END:initComponents

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        if(btnAdult.getNumber()==0&&btnChild.getNumber()==0&&btnInfant.getNumber()==0&&txtChooseTime.getText().equals("Đã hết chỗ")){
            
        }
        else{
            event.actionPerformed(evt);
        }
    }//GEN-LAST:event_myButton1ActionPerformed

    private void btnAdultMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdultMousePressed
    }//GEN-LAST:event_btnAdultMousePressed

    private void btnChildMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChildMousePressed
 
    }//GEN-LAST:event_btnChildMousePressed
    private void calculateTheCost() {
        Locale vn = new Locale("vi","VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vn);
        double total = price*btnAdult.getNumber()+ 0.7*price*btnChild.getNumber();
        txtCost.setText(currencyFormatter.format(total));
    }
    public void addEvent(ActionListener event) {
        this.event = event;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookingCover;
    private uitravel.User.UserInfo.Component.IncreaseButton btnAdult;
    private uitravel.User.UserInfo.Component.IncreaseButton btnChild;
    private uitravel.User.UserInfo.Component.IncreaseButton btnInfant;
    private uitravel.Components.MyButton btnTime;
    private javax.swing.JLayeredPane cmtCover;
    private javax.swing.JLayeredPane imgCover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listItem;
    private javax.swing.JScrollPane main;
    private uitravel.Components.MyButton myButton1;
    private javax.swing.JPanel pnlAcc;
    private uitravel.Components.RoundedPanel roundedPanel1;
    private javax.swing.JLabel txtAdultPrice;
    private javax.swing.JLabel txtAdultPrice1;
    private javax.swing.JLabel txtChildrenPrice;
    private javax.swing.JTextField txtChooseTime;
    private javax.swing.JLabel txtCost;
    private javax.swing.JLabel txtInfantPrice;
    private javax.swing.JTextArea txtInfo;
    private javax.swing.JTextArea txtName;
    private javax.swing.JTextArea txtName1;
    private javax.swing.JLayeredPane txtNumberComments;
    private javax.swing.JLabel txtScore;
    private javax.swing.JLabel txtScore1;
    private javax.swing.JLabel txtScore2;
    private javax.swing.JLabel txtTime;
    // End of variables declaration//GEN-END:variables


}
