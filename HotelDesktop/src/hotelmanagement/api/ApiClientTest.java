/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hotelmanagement.api;

public class ApiClientTest {

    public static void main(String[] args) {
        try {
            String response = ApiClient.get("/hello");

            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}