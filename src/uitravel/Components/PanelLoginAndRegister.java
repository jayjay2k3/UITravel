package uitravel.Components;

import FireBase.FirebaseInitializer;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import uitravel.Components.MyPassword;
import uitravel.Components.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.json.JSONException;
import org.json.JSONObject;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    private boolean isLogin;
    private ActionListener Loging;
    private String uid;

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
        //register = new JPanel();
        register.removeAll();
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]15[]25[]push"));
        JLabel label = new JLabel("Tạo tài khoản");
        label.setFont(new Font("Times new roman", 1, 30));
        label.setForeground(new Color(15, 104, 242));
        register.add(label);
        
        TextField txtName = new uitravel.Components.TextField();
        txtName.setLabelText("Họ và tên:");
        txtName.setFont(new Font("Times new roman", 0, 14));

        register.add(txtName,"w 60%");
        
                
        TextField txtEmail = new uitravel.Components.TextField();
        txtEmail.setLabelText("Email:");
        txtEmail.setFont(new Font("Times new roman", 0, 14));
        register.add(txtEmail,"w 60%");
                
        TextField txtPhone = new uitravel.Components.TextField();
        txtPhone.setLabelText("Số điện thoại:");
        txtPhone.setFont(new Font("Times new roman", 0, 14));

        register.add(txtPhone,"w 60%");
                
       
        PasswordField txtPass = new uitravel.Components.PasswordField();
        txtPass.setLabelText("Mật khẩu:");
        txtPass.setFont(new Font("Times new roman", 0, 14));

        txtPass.setShowAndHide(true);
        register.add(txtPass,"w 60%");
                
        PasswordField txtPassConfirm = new uitravel.Components.PasswordField();
        txtPassConfirm.setLabelText("Xác nhận mật khẩu:");
        txtPassConfirm.setFont(new Font("Times new roman", 0, 14));
        txtPassConfirm.setShowAndHide(true);
        register.add(txtPassConfirm,"w 60%");
                
        MyButton btn = new MyButton();
        btn.setPreferredSize(new java.awt.Dimension(192, 23));
        btn.setRadius(60); 
        btn.setText("Đăng ký");
        btn.setFont(new Font("Times new roman", 1, 16));

        btn.setBorderColor(new java.awt.Color(0, 51, 153));
        btn.setColor(new java.awt.Color(0, 51, 153));
        btn.setColorClick(new java.awt.Color(0, 102, 255));
        btn.setColorOver(new java.awt.Color(0, 51, 204));
        btn.setForeground(new java.awt.Color(255, 255, 255));
        
        //Đăng ký user
        btn.addActionListener((ActionEvent e) -> {
          if("".equals(txtName.getText())){
               JOptionPane.showMessageDialog(null,
                        "Vui lòng nhập họ và tên!",
                        "Thông báo!",
                        
                        JOptionPane.INFORMATION_MESSAGE);
          }
          else if("".equals(txtEmail.getText())){
               JOptionPane.showMessageDialog(null,
                        "Không được để Email nhập trống!",
                        "Thông báo!",
                        
                        JOptionPane.INFORMATION_MESSAGE);
          }
          else if("".equals(txtPhone.getText())){
               JOptionPane.showMessageDialog(null,
                        "Không được để số điện thoại trống!",
                        "Thông báo!",
                        
                        JOptionPane.INFORMATION_MESSAGE);
          }
          else if("".equals(txtPass.getText())){
               JOptionPane.showMessageDialog(null,
                        "Không được để mật khẩu trống!",
                        "Thông báo!",
                        
                        JOptionPane.INFORMATION_MESSAGE);
          }
          else if("".equals(txtPassConfirm.getText())){
               JOptionPane.showMessageDialog(null,
                        "Vui lòng xác nhận mật khẩu!",
                        "Thông báo!",
                        
                        JOptionPane.INFORMATION_MESSAGE);
          } else if(!txtPassConfirm.getText().equals(txtPass.getText())){
               JOptionPane.showMessageDialog(null,
                        "Mật khẩu xác nhận không trùng khớp!",
                        "Thông báo!",
                        
                        JOptionPane.INFORMATION_MESSAGE);
          }else{
              try {
                  FirebaseInitializer.initialize();
              } catch (IOException ex) {
                  Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
              }
              //Đăng ký Authentication FireBase
                String uid = registerUser(txtEmail.getText(),txtPass.getText());
               // Lưu data đăng ký vào Firestore
                Firestore db = FirestoreClient.getFirestore();
                Map<String, Object> UserData = new HashMap<>();
                UserData.put("FullName", txtName.getText());
                UserData.put("Email", txtEmail.getText());
                UserData.put("PhoneNumber", txtPhone.getText());
                db.collection("user").document(uid).set(UserData);
                initRegister();
                JOptionPane.showMessageDialog(null,
                    "Đăng ký thành công, vui lòng đang nhập lại!",
                    "Thông báo!",
                    JOptionPane.INFORMATION_MESSAGE);
          }
        });
        register.add(btn,"w 25%,h 50");
    }
        
        public String registerUser(String email, String password) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password);

        try {
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return userRecord.getUid();
        } catch (FirebaseAuthException e) {
            return null;
        }
    }
        public String  loginUser(String email, String password) {
        try {
            String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + "AIzaSyCGYnqZl2CkCfgJZXj8M_O_CFPOoy2Mdi0";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonInput = new JSONObject();
            jsonInput.put("email", email);
            jsonInput.put("password", password);
            jsonInput.put("returnSecureToken", true);

            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                os.write(jsonInput.toString().getBytes());
                os.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                String idToken = jsonResponse.getString("idToken");
                uid = jsonResponse.getString("localId"); // UID of the user
                // You can use idToken for authenticated requests to your Firebase backend
                return "Đăng nhập thành công";

            } else {
                StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            JSONObject errorResponse = new JSONObject(response.toString());
            String errorMessage = errorResponse.getJSONObject("error").getString("message");
            System.out.println("Login failed1: " + response.toString());

            System.out.println("Login failed2: " +errorMessage);
            return switch (errorMessage) {
                case "INVALID_EMAIL" -> "Email không đúng định dạng";
                case "EMAIL_NOT_FOUND" -> "Email không tồn tại";
                case "INVALID_PASSWORD" -> "Mật khẩu không đúng";
                case "USER_DISABLED" -> "Tài khoản đã bị vô hiệu hóa";
                case "INVALID_LOGIN_CREDENTIALS" -> "Thông tin đăng nhập không chính xác";
                case "TOO_MANY_ATTEMPTS_TRY_LATER : Access to this account has been temporarily disabled due to many failed login attempts. You can immediately restore it by resetting your password or you can try again later." -> "Nhập sai thông tin đăng nhập quá nhiều lần, tài khoản sẽ tạm thời bị vô hiệu hóa.";
                default -> "Đăng nhập thất bại: " + errorMessage;
            };
            }
        } catch (IOException | JSONException e) {
            return "Đăng nhập thất bại: " + e.getMessage();

        }
    }
    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]20[]10[]10[]25[]push"));
        JLabel label = new JLabel("Đăng nhập");
        label.setFont(new Font("Times new roman", Font.BOLD, 30));
        label.setForeground(new Color(15, 104, 242));
        login.add(label);
        
        TextField txtAccount = new uitravel.Components.TextField();
        txtAccount.setLabelText("Email:");
        txtAccount.setFont(new Font("Times new roman", Font.PLAIN, 14));
        login.add(txtAccount,"w 60%");

        PasswordField txtPass = new uitravel.Components.PasswordField();
        txtPass.setLabelText("Mật khẩu:");
        txtPass.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtPass.setShowAndHide(true);
        login.add(txtPass,"w 60%");
        
        JLabel forgetPass = new JLabel();
        forgetPass.setText("Quên mật khẩu?");
        forgetPass.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        login.add(forgetPass,"align left, cell 0 3");
       forgetPass.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
               forgetPassMousePressed(evt);
            }

            private void forgetPassMousePressed(MouseEvent evt) {
                
            }
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
        btn.setFont(new Font("Times new roman", 1, 16));

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
                    String loginResult  =loginUser(txtAccount.getText(),txtPass.getText());
                     if (loginResult.equals("Đăng nhập thành công")) {
                        JOptionPane.showMessageDialog(null, loginResult);
                        System.out.println(uid);
                        Loging.actionPerformed(e);
                    } else {
                        JOptionPane.showMessageDialog(null, loginResult, "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                
            }
        });

    }
   public String getUID(){
       return uid;
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
    public void addEvent(ActionListener event) {
        this.Loging = event;
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
