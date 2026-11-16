/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotelmanagement;

/**
 *
 * @author Nived
 */

/*import hotelmanagement.db.DBConnection;
import java.sql.Connection;
*/
import hotelmanagement.ui.LoginPageUI;

public class HotelManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("Connected to MySQL Successfully!");

            try {
                con.close();
                System.out.println("Connection Closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Connection Failed!");
        }*/
        
        java.awt.EventQueue.invokeLater(() -> {
        new LoginPageUI().setVisible(true);
        });
        
    }
    
}
