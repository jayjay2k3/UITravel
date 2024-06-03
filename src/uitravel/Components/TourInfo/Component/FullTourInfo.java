/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Components.TourInfo.Component;

import com.raven.datechooser.DateChooser;
import java.util.ArrayList;
import java.util.List;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author ACER
 */
public class FullTourInfo extends javax.swing.JPanel {

    List<Comment> tourComments;
    List<TourImage> tourImages;
    private DateChooser selectDate;

    public FullTourInfo() {
        initComponents();
        init();
        loadIMG();
        loadCMT();
    }
    private void init(){
        imgCover.setLayout(new MigLayout(" fillx, insets 0","[]30[]","[]"));
        cmtCover.setLayout(new MigLayout(" wrap, fill, insets 10","[]","[]10[]"));
        selectDate = new DateChooser();
        selectDate.setTextField(txtChooseTime);
        selectDate.setBetweenCharacter(" đến ");
        selectDate.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
    }
    private void loadIMG(){
        tourImages = new ArrayList<>();
        for(int i = 0;i<10;i++){
            TourImage temp = new TourImage();
            imgCover.add(temp,"width 490!, height 483!");
            tourImages.add(temp);
        }
    }
     private void loadCMT(){
        tourComments = new ArrayList<>();
        for(int i = 0;i<10;i++){
            Comment temp = new Comment();
            cmtCover.add(temp);
            tourComments.add(temp);
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
        txtInclude = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextArea();
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
        txtScore = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        txtScore1 = new javax.swing.JLabel();
        txtScore2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1300, 750));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        main.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        main.setOpaque(false);

        txtNumberComments.setBackground(new java.awt.Color(255, 255, 255));
        txtNumberComments.setAutoscrolls(true);
        txtNumberComments.setOpaque(true);
        txtNumberComments.setPreferredSize(new java.awt.Dimension(1431, 2200));

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

        txtInfo.setColumns(20);
        txtInfo.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtInfo.setLineWrap(true);
        txtInfo.setRows(5);
        txtInfo.setText("Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour Hoi An Basket Boat Tour ");
        txtInfo.setWrapStyleWord(true);
        txtInfo.setPreferredSize(new java.awt.Dimension(800, 104));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setText("Bao gồm:");

        txtInclude.setColumns(20);
        txtInclude.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtInclude.setLineWrap(true);
        txtInclude.setRows(5);
        txtInclude.setText("Ticket\nBottled water\nBasket boat\nVietnamese hat (Non La )\nAll Fees and Taxes\nLife jacket\nTour Guide\nGuide\nGuide\n");
        txtInclude.setWrapStyleWord(true);
        txtInclude.setPreferredSize(new java.awt.Dimension(800, 144));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setText("Thông tin hành trình");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setText("Dừng tại: Hoi An\nVào cửa miễn phí\nArriving in Hoi An ancient town, you cannot miss visiting the seven – hectare water coconut woods with the unique ecosystem of water coconut trees. This place is a relic of the film revolutionary base during the war between US army and Hoi An people . Here , you will be enthusiastically welcomed by the local people, they will lead you to visit the creeks in the water coconut woods by basket boats, you will be intructed to fish crabs and you will see the performances of rowing basket boats by the local rower.\n\nInclusion: Entrance ticket\n\n- Visiting the historical and cultural relic of Cam Thanh Nipa palm Site and enjoying the eco logical scenery\n\n- Enjoying fishing net throw and others activities\n\n- Free pick up your hotel ( only 1 way)\n\n");
        txtDescription.setWrapStyleWord(true);
        txtDescription.setPreferredSize(new java.awt.Dimension(764, 224));

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
            .addGap(0, 798, Short.MAX_VALUE)
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
        txtCost.setText("Số lượng vé:");

        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setText("Bước tiếp");
        myButton1.setBorderColor(new java.awt.Color(0, 0, 255));
        myButton1.setColor(new java.awt.Color(0, 0, 255));
        myButton1.setColorClick(new java.awt.Color(0, 200, 255));
        myButton1.setColorOver(new java.awt.Color(0, 150, 255));
        myButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        myButton1.setRadius(10);

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
                .addContainerGap(29, Short.MAX_VALUE))
        );

        txtChooseTime.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChooseTime.setText("jTextField1");
        txtChooseTime.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Vé và giá");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Chọn ngày:");

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
                                .addComponent(txtChooseTime, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(18, 18, 18)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
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

        txtNumberComments.setLayer(txtName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(txtInfo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(txtInclude, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtNumberComments.setLayer(txtDescription, javax.swing.JLayeredPane.DEFAULT_LAYER);
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

        javax.swing.GroupLayout txtNumberCommentsLayout = new javax.swing.GroupLayout(txtNumberComments);
        txtNumberComments.setLayout(txtNumberCommentsLayout);
        txtNumberCommentsLayout.setHorizontalGroup(
            txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtInclude, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                                                        .addComponent(jLabel5)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtTime))))))
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bookingCover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtScore)
                            .addComponent(txtScore1))
                        .addGap(143, 143, 143)
                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtScore2))))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        txtNumberCommentsLayout.setVerticalGroup(
            txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtInclude, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTime))
                        .addGap(20, 20, 20)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(txtNumberCommentsLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(bookingCover, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtScore1)
                    .addComponent(txtScore2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(txtNumberCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtScore)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(476, 476, 476))
        );

        main.setViewportView(txtNumberComments);

        add(main);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookingCover;
    private uitravel.User.UserInfo.Component.IncreaseButton btnAdult;
    private uitravel.User.UserInfo.Component.IncreaseButton btnChild;
    private uitravel.User.UserInfo.Component.IncreaseButton btnInfant;
    private javax.swing.JLayeredPane cmtCover;
    private javax.swing.JLayeredPane imgCover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane main;
    private uitravel.Components.MyButton myButton1;
    private uitravel.Components.RoundedPanel roundedPanel1;
    private javax.swing.JLabel txtAdultPrice;
    private javax.swing.JLabel txtAdultPrice1;
    private javax.swing.JLabel txtChildrenPrice;
    private javax.swing.JTextField txtChooseTime;
    private javax.swing.JLabel txtCost;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextArea txtInclude;
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
