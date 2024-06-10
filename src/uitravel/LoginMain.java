package uitravel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.raven.glasspanepopup.GlassPanePopup;
import uitravel.Components.PanelCover;
import uitravel.Components.PanelLoginAndRegister;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import uitravel.Components.Loading;

/**
 *
 * @author ACER
 */
public class LoginMain extends javax.swing.JFrame {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private JLabel exit;
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginAndRegister loginAndRegister;
    private boolean isLogin = false;
    private final double addSize = 20;
    private final double coverSize = 40;
    private final double loginSize = 60;
    public void setLogin(boolean t){
        this.isLogin = t;
        init();

    }
    public LoginMain() {
        initComponents();
     
    }
    private void init(){
        GlassPanePopup.install(this);
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        cover.setLogin(isLogin);
        loginAndRegister = new PanelLoginAndRegister();
        loginAndRegister.setLogin(isLogin);
        bg.setLayout(layout);
        System.out.println(isLogin);
        exit = new JLabel();
        bg.add(exit,"dock north");
        exit.addMouseListener( new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                UserMain um = new UserMain();
                um.setVisible(true);
                dispose();
            }   
        });
        if(isLogin){
            bg.add(cover,"width " + coverSize + "%, pos al 0 n 100%");
            bg.add(loginAndRegister,"width " + loginSize + "%, pos 0al 0 n 100%");
            exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Arrow - Left - blue.png")));

        }
        else{
            bg.add(cover,"width " + coverSize + "%, pos 0al 0 n 100%");
            bg.add(loginAndRegister,"width " + loginSize + "%, pos 1al 0 n 100%");
            exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Arrow - Left.png")));
        }
        bg.moveToFront(cover);
        bg.moveToFront(exit);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float faction){
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if(faction<=0.5f){
                    size +=faction*addSize;
                }
                else{
                    size +=addSize - faction*addSize;
                }
                if(isLogin){
                    
                    fractionCover = 1f-faction;
                    fractionLogin = faction;
                    if(faction>=0.5f){
                        cover.registerRight(fractionCover * 100);
                    }
                    else{
                         cover.loginRight(fractionLogin * 100);
                        }
                    exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Arrow - Left.png")));

                }
                else{
                    fractionCover = faction;
                    fractionLogin = 1-faction;
                     if(faction<=0.5f){
                        cover.registerLeft(faction * 100);
                    }
                    else{
                        cover.loginLeft((1f - faction) * 100);
                        }
                    exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Arrow - Left - blue.png")));

                }
                if(faction>=0.5f){
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.parseDouble(df.format(fractionCover));
                fractionLogin = Double.parseDouble(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }
            @Override
              public void end(){
                  isLogin=!isLogin;
              }
        };
        Animator ani = new Animator(1000,target);
        ani.setAcceleration(0.5f);
        ani.setDeceleration(0.5f);
        ani.setResolution(0);
        cover.addEvent((ActionEvent e) -> {
            if(!ani.isRunning()){
                ani.start();                
            }
        });
        cover.login(isLogin);
        loginAndRegister.addEvent((ActionEvent e) -> {
            
            GlassPanePopup.showPopup(new Loading());
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Thực hiện tác vụ nặng ở đây
                UserMain um = new UserMain();
                um.setUID(loginAndRegister.getUID());
                um.setIsLogged(true);
                
                // Hiển thị kết quả sau khi tác vụ hoàn thành
                SwingUtilities.invokeLater(() -> {
                    um.setVisible(true);
                });
                return null;
            }

            @Override
            protected void done() {
                // Ẩn màn hình loading sau khi tác vụ hoàn thành
                GlassPanePopup.closePopupAll();
                dispose();

            }
        };
        worker.execute();
        });

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1400, 800));
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
