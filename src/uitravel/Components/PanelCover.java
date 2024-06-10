package uitravel.Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelCover extends javax.swing.JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private MyButton myButton1;
    private boolean isLogin;
    public void setLogin(boolean t){
        this.isLogin =t;
        init();
    }
    public PanelCover() {
        initComponents();
        setOpaque(false);

    }
    private void init() {
        setOpaque(false);
        layout = new MigLayout("wrap,fill","[center]","push[]25[]25[]push");
        setLayout(layout);
        myButton1 = new MyButton();
        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setBorderColor(new java.awt.Color(255, 255, 255));
        myButton1.setColor(new java.awt.Color(0, 51, 153));
        myButton1.setColorClick(new java.awt.Color(51, 204, 255));
        myButton1.setColorOver(new java.awt.Color(0, 51, 204));
        myButton1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        myButton1.setPreferredSize(new java.awt.Dimension(100, 23));
        myButton1.setRadius(45);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    event.actionPerformed(evt);
            }
        });
      if(isLogin){
          
            title = new JLabel("Bạn chưa có tài khoản?");
            title.setFont(new Font("Times new roman", 1, 30));
            title.setForeground(new Color(255,255,255));
            add(title);
            description = new JLabel("Chỉ với vài bước đơn giản, bạn có thể trở thành thành viên của chúng tôi");
            description.setForeground(new Color(255,255,255));
            description.setFont(new Font("Times new roman", 0, 16));
            add(description);
            
          
            myButton1.setText("Đăng ký");
            add(myButton1, "w 40%, h 50");
        }
        else{
            title = new JLabel("Bạn đã có tài khoản?");
            title.setFont(new Font("Times new roman", 1, 30));
            title.setForeground(new Color(0,51,153));
            add(title);
            description = new JLabel("Bạn có thể đăng nhập tại đây");
            description.setForeground(new Color(0,51,153));
            description.setFont(new Font("Times new roman", 0, 16));
            add(description);
            myButton1.setText("Đăng nhập");
            add(myButton1, "w 40%, h 50");
        }
     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

  
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, new Color(0,51,153), 0, getHeight(), new Color(0,51,183));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public void registerLeft(double v) {
        v = Double.parseDouble(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
    }

    public void registerRight(double v) {
        v = Double.parseDouble(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
    }

    public void loginLeft(double v) {
        v = Double.parseDouble(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginRight(double v) {
        v = Double.parseDouble(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
    }
  private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        event.actionPerformed(evt);
       /*
         isLogin=!isLogin;
        if(isLogin){
            myButton1.setText("Đăng ký");
            title.setText("Bạn chưa có tài khoản?");
            description.setText("Chỉ với vài bước đơn giản, bạn có thể trở thành thành viên của chúng tôi");

        } else {
            myButton1.setText("Đăng nhập");
            title.setText("Bạn đã có tài khoản?");
            description.setText("Bạn có thể đăng nhập tại đây");
        }
        */
    }    
    public void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                myButton1.setText("Đăng ký");
                title.setText("Bạn chưa có tài khoản?");
                description.setText("Chỉ với vài bước đơn giản, bạn có thể trở thành thành viên của chúng tôi");

            } else {
                myButton1.setText("Đăng nhập");
                title.setText("Bạn đã có tài khoản?");
                description.setText("Bạn có thể đăng nhập tại đây");

            }
            this.isLogin = login;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
