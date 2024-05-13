package uitravel.Components;

import uitravel.Components.MyPassword;
import uitravel.Components.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    private boolean isLogin;
    public void setLogin(boolean t){
        this.isLogin = t;
        if(t){
            login.setVisible(true);
            register.setVisible(false);
        }
        else{
             login.setVisible(false);
         register.setVisible(true);
        }

    }
    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
       // login.setVisible(false);
        //register.setVisible(true);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]15[]25[]push"));
        JLabel label = new JLabel("Tạo tài khoản");
        label.setFont(new Font("Times new romans", 1, 30));
        label.setForeground(new Color(15, 104, 242));
        register.add(label);
        
        TextField txtName = new uitravel.Components.TextField();
        txtName.setLabelText("Họ và tên:");
        register.add(txtName,"w 60%");
        
                
        TextField txtEmail = new uitravel.Components.TextField();
        txtEmail.setLabelText("Email:");
        register.add(txtEmail,"w 60%");
                
        TextField txtPhone = new uitravel.Components.TextField();
        txtPhone.setLabelText("Số điện thoại:");
        register.add(txtPhone,"w 60%");
                
        TextField txtAccount = new uitravel.Components.TextField();
        txtAccount.setLabelText("Tên tài khoản:");
        register.add(txtAccount,"w 60%");
                
        TextField txtPass = new uitravel.Components.TextField();
        txtPass.setLabelText("Mật khẩu:");
        register.add(txtPass,"w 60%");
                
        TextField txtPassConfirm = new uitravel.Components.TextField();
        txtPassConfirm.setLabelText("Xác nhận mật khẩu:");
        register.add(txtPassConfirm,"w 60%");
                
        MyButton btn = new MyButton();
        btn.setPreferredSize(new java.awt.Dimension(192, 23));
        btn.setRadius(60); 
        btn.setText("Đăng ký!");
        btn.setBorderColor(new java.awt.Color(0, 51, 153));
        btn.setColor(new java.awt.Color(0, 51, 153));
        btn.setColorClick(new java.awt.Color(0, 102, 255));
        btn.setColorOver(new java.awt.Color(0, 51, 204));
        btn.setForeground(new java.awt.Color(255, 255, 255));
        register.add(btn,"w 25%,h 50");
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]20[]10[]10[]25[]push"));
        JLabel label = new JLabel("Đăng nhập");
        label.setFont(new Font("Times new romans", 1, 30));
        label.setForeground(new Color(15, 104, 242));
        login.add(label);
        
        MyTextField txtAccount = new uitravel.Components.MyTextField();
        txtAccount.setText("Tên đăng nhập");
        txtAccount.setPreferredSize(new java.awt.Dimension(80, 30));
        txtAccount.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/user.png")));
        login.add(txtAccount,"w 60%");
        
        txtAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAccountFocusGained(evt);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAccountFocusLost(evt);
            }
            private void txtAccountFocusGained(FocusEvent evt) {
                     if("Tên đăng nhập".equals(txtAccount.getText())){
                       txtAccount.setText("");
                   }
                       }

               private void txtAccountFocusLost(FocusEvent evt) {
                     if("".equals(txtAccount.getText())){
                       txtAccount.setText("Tên đăng nhập");
                   }
               }
        });
     
        MyPassword txtPass = new uitravel.Components.MyPassword();
        txtPass.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/password-148-16.png")));
        login.add(txtPass,"w 60%");
        
        JCheckBox savePass = new JCheckBox();
        savePass.setText("Lưu mật khẩu");
        login.add(savePass,"align left, cell 0 3");
        JCheckBox showPass = new JCheckBox();
        showPass.setText("Hiện mật khẩu");
        login.add(showPass,"align right,gapleft push, cell 0 3");

        savePass.addActionListener((ActionEvent e) -> {
            if(savePass.isSelected()){
                
            }else{
                
            }
        });
         showPass.addActionListener((ActionEvent e) -> {
             if(showPass.isSelected()){
                 txtPass.setEchoChar((char)0);
                 
             }else{
                 txtPass.setEchoChar('*');
                 
             }
             txtPass.requestFocus();
        });
        MyButton btn = new MyButton();
        btn.setPreferredSize(new java.awt.Dimension(192, 23));
        btn.setRadius(60);       
        btn.setText("Đăng nhập");
        btn.setBorderColor(new java.awt.Color(0, 51, 153));
        btn.setColor(new java.awt.Color(0, 51, 153));
        btn.setColorClick(new java.awt.Color(0, 102, 255));
        btn.setColorOver(new java.awt.Color(0, 51, 204));
        btn.setForeground(new java.awt.Color(255, 255, 255));
        login.add(btn,"w 25%,h 50");
        
        btn.addActionListener((ActionEvent e) -> {
            if("Tên đăng nhập".equals(txtAccount.getText())||"".equals(txtAccount.getText()))
            {
                JOptionPane.showMessageDialog(null,
                        "Không được để tên đăng nhập trống!",
                        "Thông báo!",
                        
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                if(txtPass.getPassword().equals("")){
                    JOptionPane.showMessageDialog(null,
                            "Không được để mật khẩu trống!",
                            "Thông báo!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    System.out.println("OK");
                }
                
            }
        });

    }
   
    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        register = new javax.swing.JPanel();
        login = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
