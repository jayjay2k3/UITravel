/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uitravel.Admin;

import FireBase.FirebaseInitializer;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.raven.datechooser.DateChooser;
import com.raven.glasspanepopup.GlassPanePopup;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import net.miginfocom.swing.MigLayout;
import uitravel.AdminMain;
import uitravel.Components.SettingActivities;
import uitravel.Components.AddActivities;

/**
 *
 * @author ACER
 */
public class SettingTour extends javax.swing.JFrame {

    private List<ImageIcon> images =null;
    private String tourID;
    private int currentImageIndex;
    DefaultListModel<String> listModel = new DefaultListModel<>();
    private Map<String, String> AllPlaces ;
    private int numberHour = 0;
    private int numberDays = 0;
    private List<Map<String,Object>> Acctivities = new ArrayList<>();

    Firestore firestore ;

    public SettingTour() throws IOException {
        initComponents();
        FirebaseInitializer.initialize();
        firestore = FirestoreClient.getFirestore();
        init();

    }
    private Map<String, String> getTourData(){
        Map<String, String>  placeData = new HashMap<>();
        try {
            CollectionReference userCollection = firestore.collection("Place");
            ApiFuture<QuerySnapshot> querySnapshot = userCollection.get();
            // Process the documents
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                String documentId = document.getId();
                String background = document.getString("Background");
                placeData.put(documentId, background);
                System.out.println(background);
            }
        } catch (ExecutionException | InterruptedException e) {
        }
        return placeData;
    }
    public void setAllPlaces(Map<String, String> placeData){
        this.AllPlaces = placeData;
    }
    private void init(){
        pnlAcc.setLayout(new MigLayout("fillx"));
        AddItem1.setVisible(false);
        AddItem.setVisible(true);
        AllPlaces = getTourData();
        GlassPanePopup.install(this);
        txtTourID.setText(tourID);
        showIMG.setVisible(true);
        listItem.setModel(listModel);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        model.addElement("Chọn nơi đến");
        
        for (Map.Entry<String, String> entry : AllPlaces.entrySet()) {
             model.addElement(entry.getKey());
           
        }
        txtPlace.setModel(model);
          ((AbstractDocument) txtPrice.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newText.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
      //  addCollectionListener();
    }
  
      
    
    public void setTourID(String id){
        this.tourID = id;
        retrieveTourInfo();

    }
    private  void retrieveTourInfo() {
        DocumentReference docRef = firestore.collection("admin").document("AllTours").collection("TourInfo").document(tourID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;

        try {
            document = future.get();
            if (document.exists()) {
                Map<String, Object> tourData = document.getData();
                if (tourData != null) {
                    txtTourID.setText(tourID);
                    String place = (String) tourData.get("Place");
                    txtPlace.setSelectedItem(place);
                    txtTourName.setText((String) tourData.get("TourName"));
                    txtDescription.setText((String) tourData.get("TourDescription"));
                    txtChooseTime.setText((String) tourData.get("TourTime"));
                    txtPrice.setText((String) tourData.get("TourPrice"));
                    String tourRating = (String) tourData.get("TourRating");
                    String tourLength = (String) tourData.get("TourLength");
                    
                    Acctivities = (List<Map<String, Object>>) tourData.get("TourActivities");
                    int index = 0;
                    for (Map<String, Object> tourActivity : Acctivities){
                        
                        String temp =(String) tourActivity.get("Length");
                        SettingActivities t2 = new SettingActivities();
                        t2.setAccName((String) tourActivity.get("Name"));
                        t2.setDescription((String) tourActivity.get("Description"));
                        t2.setDuriation(temp);
                        int currentIndex = index;
                        t2.addDeleteListener(e -> {
                            deleteActivity( currentIndex); // Xóa hoạt động khi phần tử được nhấn chuột
                            pnlAcc.remove(t2); // Xóa phần tử giao diện
                            pnlAcc.revalidate();
                            pnlAcc.repaint();
                        });
                        if("Giờ".equals(temp.split(" ")[1])){
                            numberHour += Integer.valueOf(temp.split(" ")[0]);
                            if(numberHour>=24){
                                numberDays+=numberHour/24;
                                numberHour = numberHour%24;
                            }
                        }
                        else{
                            numberDays += Integer.valueOf(temp.split(" ")[0]);
                        }
                        index++;
                        pnlAcc.add(t2,"wrap, w 95%");
                    }
                    
                    
                    List<String> tourIncludes = (List<String>) tourData.get("TourIncludes");
                    listModel.addAll(tourIncludes);
                    listItem.setModel(listModel);
                    
                    List<String> tourImages = (List<String>) tourData.get("TourImages");
                    currentImageIndex=0;
                    images = convertBase64ToImageIcon(tourImages);
                    showIMG.setVisible(true);
                    showIMG.setbackgroundImage(images.get(currentImageIndex));
                    showIMG.repaint();
                    btnPrev.setEnabled(false);
                    if(images.isEmpty()||images.size()==1){
                        btnNext.setEnabled(false);
                    }
                    else{
                        btnNext.setEnabled(true);
                    }
                    // Use the retrieved data (for demonstration purposes)

                    System.out.println("TourRating: " + tourRating);
                    System.out.println("TourLength: " + tourLength);
                    System.out.println("TourIncludes: " + tourIncludes);
                    System.out.println("TourImages: " + tourImages);
                }
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            JOptionPane.showMessageDialog(null,
                    "Error retrieving tour data. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteActivity( int indexToDelete) {
    
            if (indexToDelete >= 0 && indexToDelete < Acctivities.size()) {
                // Xóa hoạt động tại chỉ số đã cho
                Acctivities.remove(indexToDelete);

                // Cập nhật lại danh sách hoạt động trong Firestore
               
            } else {
                System.out.println("Invalid activity index.");
            }
      
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker = new com.raven.swing.TimePicker();
        main = new javax.swing.JScrollPane();
        bg = new javax.swing.JLayeredPane();
        header = new javax.swing.JPanel();
        user = new javax.swing.JPanel();
        txtEmail = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        customLineComponent1 = new uitravel.Components.CustomLineComponent();
        user3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTourID = new javax.swing.JLabel();
        txtTourID1 = new javax.swing.JLabel();
        AddItem = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTourName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItem = new javax.swing.JList<>();
        txtAddItem = new javax.swing.JTextField();
        btnAdd = new uitravel.Components.MyButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtPlace = new javax.swing.JComboBox<>();
        btnAddPlace = new javax.swing.JButton();
        btnNextStep = new uitravel.Components.MyButton();
        AddItem1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        showIMG = new uitravel.Components.ImagePanel();
        btnChooseImg = new uitravel.Components.MyButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        txtChooseTime = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSetTime = new uitravel.Components.MyButton();
        btnAdd1 = new uitravel.Components.MyButton();
        jLabel11 = new javax.swing.JLabel();
        btnSubmit = new uitravel.Components.MyButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        pnlAcc = new javax.swing.JPanel();

        timePicker.setDisplayText(txtChooseTime);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        bg.setPreferredSize(new java.awt.Dimension(1400, 800));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setPreferredSize(new java.awt.Dimension(1400, 91));

        user.setBackground(new java.awt.Color(255, 255, 255));
        user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userMousePressed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtEmail.setText("21521857@gm.uit.edu.vn");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Xin chào");

        javax.swing.GroupLayout userLayout = new javax.swing.GroupLayout(user);
        user.setLayout(userLayout);
        userLayout.setHorizontalGroup(
            userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtEmail)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        userLayout.setVerticalGroup(
            userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(txtEmail)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        customLineComponent1.setLineColor(new java.awt.Color(102, 153, 255));

        user3.setBackground(new java.awt.Color(255, 255, 255));
        user3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                user3MousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Quay về trang chủ");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout user3Layout = new javax.swing.GroupLayout(user3);
        user3.setLayout(user3Layout);
        user3Layout.setHorizontalGroup(
            user3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        user3Layout.setVerticalGroup(
            user3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18))
        );

        txtTourID.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTourID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTourID.setText("21521857");

        txtTourID1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTourID1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTourID1.setText("ID:");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(user3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 461, Short.MAX_VALUE)
                .addComponent(txtTourID1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTourID)
                .addGap(130, 130, 130))
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(customLineComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, 1399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(user3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTourID)
                    .addComponent(txtTourID1))
                .addGap(34, 34, 34))
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                    .addContainerGap(90, Short.MAX_VALUE)
                    .addComponent(customLineComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Tên chuyến đi:");

        txtTourName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Mô tả:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Bao gồm:");

        listItem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(listItem);

        txtAddItem.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btnAdd.setText("Thêm");
        btnAdd.setBorderColor(new java.awt.Color(0, 0, 204));
        btnAdd.setColorClick(new java.awt.Color(153, 255, 255));
        btnAdd.setColorOver(new java.awt.Color(220, 255, 255));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Chọn nơi đến/ thành phố");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDescription);

        txtPlace.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btnAddPlace.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnAddPlace.setText("+");
        btnAddPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPlaceActionPerformed(evt);
            }
        });

        btnNextStep.setForeground(new java.awt.Color(255, 255, 255));
        btnNextStep.setText("Tiếp tục");
        btnNextStep.setBorderColor(new java.awt.Color(255, 102, 0));
        btnNextStep.setColor(new java.awt.Color(255, 102, 0));
        btnNextStep.setColorClick(new java.awt.Color(255, 51, 0));
        btnNextStep.setColorOver(new java.awt.Color(255, 153, 0));
        btnNextStep.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNextStep.setRadius(10);
        btnNextStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextStepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddItemLayout = new javax.swing.GroupLayout(AddItem);
        AddItem.setLayout(AddItemLayout);
        AddItemLayout.setHorizontalGroup(
            AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddItemLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(AddItemLayout.createSequentialGroup()
                        .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(AddItemLayout.createSequentialGroup()
                                .addGap(899, 899, 899)
                                .addComponent(btnNextStep, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddItemLayout.createSequentialGroup()
                                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(AddItemLayout.createSequentialGroup()
                                            .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTourName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(93, 93, 93))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddItemLayout.createSequentialGroup()
                                                .addComponent(txtPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnAddPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(AddItemLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(85, 85, 85)))
                                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(AddItemLayout.createSequentialGroup()
                                        .addComponent(txtAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        AddItemLayout.setVerticalGroup(
            AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddItemLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AddItemLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddItemLayout.createSequentialGroup()
                        .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(AddItemLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(txtTourName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(97, 97, 97)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddPlace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextStep, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Giá với một hành khách (vnd):");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Hình ảnh:");

        txtPrice.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Các hoạt động trong chuyến đi:");

        showIMG.setisTransparent(false);

        javax.swing.GroupLayout showIMGLayout = new javax.swing.GroupLayout(showIMG);
        showIMG.setLayout(showIMGLayout);
        showIMGLayout.setHorizontalGroup(
            showIMGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );
        showIMGLayout.setVerticalGroup(
            showIMGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        btnChooseImg.setText("Chọn");
        btnChooseImg.setBorderColor(new java.awt.Color(0, 0, 204));
        btnChooseImg.setColorClick(new java.awt.Color(153, 255, 255));
        btnChooseImg.setColorOver(new java.awt.Color(220, 255, 255));
        btnChooseImg.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnChooseImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImgActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setText("<");
        btnPrev.setEnabled(false);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        txtChooseTime.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChooseTime.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Thời gian bắt đầu:");

        btnSetTime.setText("Chọn");
        btnSetTime.setBorderColor(new java.awt.Color(0, 0, 204));
        btnSetTime.setColorClick(new java.awt.Color(153, 255, 255));
        btnSetTime.setColorOver(new java.awt.Color(220, 255, 255));
        btnSetTime.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSetTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetTimeActionPerformed(evt);
            }
        });

        btnAdd1.setText("Thêm");
        btnAdd1.setBorderColor(new java.awt.Color(0, 0, 204));
        btnAdd1.setColorClick(new java.awt.Color(153, 255, 255));
        btnAdd1.setColorOver(new java.awt.Color(220, 255, 255));
        btnAdd1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 255));
        jLabel11.setText("Quay lại bước trước");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });

        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Xác nhận");
        btnSubmit.setBorderColor(new java.awt.Color(255, 102, 0));
        btnSubmit.setColor(new java.awt.Color(255, 102, 0));
        btnSubmit.setColorClick(new java.awt.Color(255, 51, 0));
        btnSubmit.setColorOver(new java.awt.Color(255, 153, 0));
        btnSubmit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSubmit.setRadius(10);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(470, 209));

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

        javax.swing.GroupLayout AddItem1Layout = new javax.swing.GroupLayout(AddItem1);
        AddItem1.setLayout(AddItem1Layout);
        AddItem1Layout.setHorizontalGroup(
            AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddItem1Layout.createSequentialGroup()
                .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(AddItem1Layout.createSequentialGroup()
                            .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(AddItem1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel9))
                                .addGroup(AddItem1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnChooseImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(AddItem1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(showIMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(AddItem1Layout.createSequentialGroup()
                                            .addComponent(btnPrev)
                                            .addGap(11, 11, 11)
                                            .addComponent(btnNext)))))
                            .addGap(93, 93, 93)
                            .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6)
                                .addGroup(AddItem1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(AddItem1Layout.createSequentialGroup()
                                    .addComponent(txtChooseTime, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnSetTime, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(AddItem1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        AddItem1Layout.setVerticalGroup(
            AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddItem1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addGap(36, 36, 36)
                .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChooseTime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSetTime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AddItem1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(btnChooseImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(showIMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddItem1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddItem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnPrev))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bg.setLayer(header, javax.swing.JLayeredPane.PALETTE_LAYER);
        bg.setLayer(AddItem, javax.swing.JLayeredPane.PALETTE_LAYER);
        bg.setLayer(AddItem1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(AddItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addComponent(AddItem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(16, 16, 16)))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayout.createSequentialGroup()
                    .addGap(93, 93, 93)
                    .addComponent(AddItem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(10, 10, 10)))
        );

        main.setViewportView(bg);

        getContentPane().add(main);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMousePressed

    }//GEN-LAST:event_userMousePressed

    private void user3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_user3MousePressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        listModel.addElement(txtAddItem.getText());
        txtAddItem.setText("");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddPlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPlaceActionPerformed
        GlassPanePopup.showPopup(new AddAPlace());
    }//GEN-LAST:event_btnAddPlaceActionPerformed

    private void btnNextStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextStepActionPerformed
        AddItem1.setVisible(true);
        AddItem.setVisible(false);
    }//GEN-LAST:event_btnNextStepActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void btnChooseImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImgActionPerformed
        chooseImages();
    }//GEN-LAST:event_btnChooseImgActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentImageIndex < images.size() - 1) {
            currentImageIndex++;
            showIMG.setbackgroundImage(images.get(currentImageIndex));
            showIMG.repaint();
            showIMG.revalidate();
            btnPrev.setEnabled(true);
        }
        if (currentImageIndex == images.size() - 1) {
            btnNext.setEnabled(false);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        if (currentImageIndex > 0) {
            currentImageIndex--;
            showIMG.setbackgroundImage(images.get(currentImageIndex));
            showIMG.repaint();
            showIMG.revalidate();
            btnNext.setEnabled(true);

        }
        if(currentImageIndex==0){
            btnPrev.setEnabled(false);
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnSetTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetTimeActionPerformed
        timePicker.showPopup(this, 900, 270);
    }//GEN-LAST:event_btnSetTimeActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        AddActivities addAcctivities = new AddActivities();
        GlassPanePopup.showPopup(addAcctivities);
        addAcctivities.addEvent((ActionEvent e) -> {
            Map<String,Object> t = new HashMap<>();
            String temp = addAcctivities.getDuration();
           
            
            SettingActivities t2 = new SettingActivities();
            t2.setAccName(addAcctivities.getAccName());
            t2.setDescription(addAcctivities.getDescription());
            t2.setDuriation(temp);
            pnlAcc.add(t2,"wrap, w 95%");
            
            t.put("Name",addAcctivities.getAccName());
            t.put("Description",addAcctivities.getDescription());
            t.put("Length",temp);
            Acctivities.add(t);
            
            if("Giờ".equals(temp.split(" ")[1])){
                numberHour += Integer.valueOf(temp.split(" ")[0]);
                if(numberHour>=24){
                    numberDays+=numberHour/24;
                    numberHour = numberHour%24;
                }
            }
            else{
                numberDays += Integer.valueOf(temp.split(" ")[0]);
            }
            
            GlassPanePopup.closePopupAll();

        });
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        AddItem1.setVisible(false);
        AddItem.setVisible(true);
    }//GEN-LAST:event_jLabel11MousePressed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        if(txtPlace.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,
                "Vui lòng chọn nơi đến!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if(txtTourName.getText().equals("")){
            JOptionPane.showMessageDialog(null,
                "Tên chuyến đi không được để trống!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if(txtDescription.getText().equals("")){
            JOptionPane.showMessageDialog(null,
                "Vui lòng thêm mô tả cho chuyến đi!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if(listItem.getModel().getSize()==0){
            JOptionPane.showMessageDialog(null,
                "Vui lòng thêm danh sách các tiện ích đi kèm của chuyến đi!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if(Acctivities.isEmpty()){
            JOptionPane.showMessageDialog(null,
                "Vui lòng tạo các hoạt động cho chuyến đi!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if("".equals(txtChooseTime.getText())){
            JOptionPane.showMessageDialog(null,
                "Vui lòng chọn thời gian bắt đầu của chuyến đi!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if("".equals(txtPrice.getText())){
            JOptionPane.showMessageDialog(null,
                "Vui lòng chọn giá cho chuyến đi!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if(images==null){
            JOptionPane.showMessageDialog(null,
                "Vui lòng chọn hình ảnh của chuyến đi!",
                "Thông báo!",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            UploadNewTourInfo();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        AdminMain am = new  AdminMain();
        am.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel4MousePressed
    private void UploadNewTourInfo() {
    firestore = FirestoreClient.getFirestore();
    CollectionReference collection = firestore.collection("admin").document("AllTours").collection("TourInfo");

    // Prepare the tour data to be uploaded
    Map<String, Object> tourData = new HashMap<>();
    tourData.put("Place", txtPlace.getSelectedItem());
    tourData.put("TourName", txtTourName.getText());
    tourData.put("TourDescription", txtDescription.getText());
    tourData.put("TourTime", txtChooseTime.getText());
    tourData.put("TourPrice", txtPrice.getText());
    tourData.put("TourRating", "0");
    tourData.put("TourActivities", Acctivities);

    // Format the tour length
    String temp = "";
    if (numberDays != 0) {
        temp = temp + String.valueOf(numberDays) + " ngày ";
    }
    if (numberHour != 0) {
        temp = temp + String.valueOf(numberHour) + " giờ ";
    }
    tourData.put("TourLength", temp);

    // Retrieve the items from the list model and add them to the tour data
    ListModel<String> model = listItem.getModel();
    List<String> itemList = new ArrayList<>();
    for (int i = 0; i < model.getSize(); i++) {
        itemList.add(model.getElementAt(i));
    }
    tourData.put("TourIncludes", itemList);

    // Iterate through the images and convert them to Base64
    List<String> imageList = new ArrayList<>();
        for (ImageIcon imageIcon : images) {
            ByteArrayOutputStream baos = null;
            try {
                BufferedImage bufferedImage = new BufferedImage(
                        imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                imageIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
                baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", baos);
                byte[] imageData = baos.toByteArray();
                String imageDataString = Base64.getEncoder().encodeToString(imageData);
                imageList.add(imageDataString);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error uploading image: " + imageIcon.getDescription() + ". Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                if (baos != null) {
                    try {
                        baos.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        tourData.put("TourImages", imageList);

        // Log the data being uploaded for debugging purposes
            System.out.println("Uploading tour data: " + tourData);
            System.out.println("Uploading tour data: " + tourData.get("TourLength"));

        collection.document(tourID).set(tourData);
        JOptionPane.showMessageDialog(null,
                    "Thêm chuyến đi thành công!.",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        GlassPanePopup.closePopupAll();
        AdminMain am = new  AdminMain();
        am.setVisible(true);
        dispose();
    }
    
    /**
     *
     */
   
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

   
        public static boolean isInteger(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }        
    public void uploadImages(List<ImageIcon> imageIcons){
       try {
        FirebaseInitializer.initialize();
        Firestore firestore = FirestoreClient.getFirestore();
        Map<String, Object> userData = new HashMap<>();
        
        int index = 0;
        for (ImageIcon imageIcon : imageIcons) {
            BufferedImage bufferedImage = new BufferedImage(
                    imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            imageIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] imageData = baos.toByteArray();

            // Convert byte array to Base64 string
            String imageDataString = Base64.getEncoder().encodeToString(imageData);
            userData.put("Image" + index, imageDataString);
            index++;
        }
        
        DocumentReference docRef = firestore.collection("Place").document();
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
    
    private void chooseImages() {
       
       JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose image files");
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            images.clear();
            currentImageIndex = 0;
            for (File file : selectedFiles) {
                ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                images.add(imageIcon);
            }
            showIMG.setVisible(true);
            showIMG.setbackgroundImage(images.get(currentImageIndex));
            showIMG.repaint();
            btnPrev.setEnabled(false);
            if(images.isEmpty()){
                btnNext.setEnabled(false);
            }
            else{
                btnNext.setEnabled(true);
            }
        }
    }
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
            java.util.logging.Logger.getLogger(SettingTour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingTour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingTour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingTour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SettingTour().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(SettingTour.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddItem;
    private javax.swing.JPanel AddItem1;
    private javax.swing.JLayeredPane bg;
    private uitravel.Components.MyButton btnAdd;
    private uitravel.Components.MyButton btnAdd1;
    private javax.swing.JButton btnAddPlace;
    private uitravel.Components.MyButton btnChooseImg;
    private javax.swing.JButton btnNext;
    private uitravel.Components.MyButton btnNextStep;
    private javax.swing.JButton btnPrev;
    private uitravel.Components.MyButton btnSetTime;
    private uitravel.Components.MyButton btnSubmit;
    private uitravel.Components.CustomLineComponent customLineComponent1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listItem;
    private javax.swing.JScrollPane main;
    private javax.swing.JPanel pnlAcc;
    private uitravel.Components.ImagePanel showIMG;
    private com.raven.swing.TimePicker timePicker;
    private javax.swing.JTextField txtAddItem;
    private javax.swing.JTextField txtChooseTime;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JComboBox<String> txtPlace;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JLabel txtTourID;
    private javax.swing.JLabel txtTourID1;
    private javax.swing.JTextField txtTourName;
    private javax.swing.JPanel user;
    private javax.swing.JPanel user3;
    // End of variables declaration//GEN-END:variables


}
