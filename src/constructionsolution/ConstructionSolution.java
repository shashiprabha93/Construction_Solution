/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constructionsolution;

import application.view.login.Login;
import javax.swing.UIManager;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author Shashiprabha
 */
public class ConstructionSolution {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewHibernateUtil newHibernateUtil = new NewHibernateUtil();
        
         try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());   
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    printStackTrace(ex);
                }
                
        
        Login login = new Login();
        login.setVisible(true);
    }

}
