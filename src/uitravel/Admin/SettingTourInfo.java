/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uitravel.Admin;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.raven.datechooser.DateChooser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class SettingTourInfo extends javax.swing.JFrame {

    private List<ImageIcon> images;
    private int currentImageIndex;
    DefaultListModel<String> listModel = new DefaultListModel<>();

    public SettingTourInfo() {
        initComponents();
        init();
    }

    private void init(){
      showIMG.setVisible(false);
      listItem.setModel(listModel);
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
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItem = new javax.swing.JList<>();
        txtAddItem = new javax.swing.JTextField();
        btnAdd = new uitravel.Components.MyButton();
        jLabel6 = new javax.swing.JLabel();
        txtChooseTime = new javax.swing.JTextField();
        btnSetTime = new uitravel.Components.MyButton();
        jLabel7 = new javax.swing.JLabel();
        txtPlace = new javax.swing.JTextField();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnSetTime1 = new uitravel.Components.MyButton();
        jLabel8 = new javax.swing.JLabel();
        btnChooseImg = new uitravel.Components.MyButton();
        showIMG = new uitravel.Components.ImagePanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();

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
                .addContainerGap(20, Short.MAX_VALUE)
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
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
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

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Mô tả");

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

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Thời gian:");

        txtChooseTime.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtChooseTime.setText("jTextField1");
        txtChooseTime.setOpaque(true);

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

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Chọn thành phố");

        txtPlace.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnPrev.setText("<");
        btnPrev.setEnabled(false);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnSetTime1.setForeground(new java.awt.Color(255, 255, 255));
        btnSetTime1.setText("Xác nhận");
        btnSetTime1.setBorderColor(new java.awt.Color(255, 102, 0));
        btnSetTime1.setColor(new java.awt.Color(255, 102, 0));
        btnSetTime1.setColorClick(new java.awt.Color(255, 51, 0));
        btnSetTime1.setColorOver(new java.awt.Color(255, 153, 0));
        btnSetTime1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSetTime1.setRadius(10);
        btnSetTime1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetTime1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Hình ảnh:");

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

        showIMG.setisTransparent(false);

        javax.swing.GroupLayout showIMGLayout = new javax.swing.GroupLayout(showIMG);
        showIMG.setLayout(showIMGLayout);
        showIMGLayout.setHorizontalGroup(
            showIMGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        showIMGLayout.setVerticalGroup(
            showIMGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDescription);

        javax.swing.GroupLayout AddItemLayout = new javax.swing.GroupLayout(AddItem);
        AddItem.setLayout(AddItemLayout);
        AddItemLayout.setHorizontalGroup(
            AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddItemLayout.createSequentialGroup()
                        .addComponent(txtAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1)
                    .addComponent(txtPlace)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddItemLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AddItemLayout.createSequentialGroup()
                                .addComponent(txtChooseTime, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSetTime, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddItemLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(btnChooseImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(showIMG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(AddItemLayout.createSequentialGroup()
                                .addComponent(btnPrev)
                                .addGap(18, 18, 18)
                                .addComponent(btnNext))))
                    .addGroup(AddItemLayout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(btnSetTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddItemLayout.setVerticalGroup(
            AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddItemLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChooseTime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSetTime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(btnChooseImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AddItemLayout.createSequentialGroup()
                        .addComponent(showIMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPrev)
                            .addComponent(btnNext))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSetTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddItemLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(205, 205, 205))
        );

        bg.setLayer(header, javax.swing.JLayeredPane.PALETTE_LAYER);
        bg.setLayer(AddItem, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnSetTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetTimeActionPerformed
        timePicker.showPopup(this, 900, 270);
    }//GEN-LAST:event_btnSetTimeActionPerformed

    private void btnChooseImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImgActionPerformed
        chooseImages();
    }//GEN-LAST:event_btnChooseImgActionPerformed

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

    private void btnSetTime1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetTime1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSetTime1ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        listModel.addElement(txtAddItem.getText());
        txtAddItem.setText("");
    }//GEN-LAST:event_btnAddActionPerformed
    private void chooseImages() {
        images = new ArrayList<>();

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
            java.util.logging.Logger.getLogger(SettingTourInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingTourInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingTourInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingTourInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SettingTourInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddItem;
    private javax.swing.JLayeredPane bg;
    private uitravel.Components.MyButton btnAdd;
    private uitravel.Components.MyButton btnChooseImg;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private uitravel.Components.MyButton btnSetTime;
    private uitravel.Components.MyButton btnSetTime1;
    private uitravel.Components.CustomLineComponent customLineComponent1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> listItem;
    private javax.swing.JScrollPane main;
    private uitravel.Components.ImagePanel showIMG;
    private com.raven.swing.TimePicker timePicker;
    private javax.swing.JTextField txtAddItem;
    private javax.swing.JTextField txtChooseTime;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JTextField txtPlace;
    private javax.swing.JLabel txtTourID;
    private javax.swing.JLabel txtTourID1;
    private javax.swing.JPanel user;
    private javax.swing.JPanel user3;
    // End of variables declaration//GEN-END:variables
}
