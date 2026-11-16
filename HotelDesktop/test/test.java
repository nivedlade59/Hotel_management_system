/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nived
 */
import com.google.gson.Gson;

public class test {
    public static void main(String[] args) {
        Gson gson = new Gson();

        String json = gson.toJson("Gson is working!");

        System.out.println(json);
    }
}
