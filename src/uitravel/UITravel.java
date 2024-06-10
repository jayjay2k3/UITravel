/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package uitravel;

import FireBase.FirebaseInitializer;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.io.IOException;



/**
 *
 * @author Jay
 */
public class UITravel {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        FlatIntelliJLaf.setup();
        FirebaseInitializer.initialize();

        UserMain um = new UserMain();
        um.setVisible(true);
    }
    }
    

