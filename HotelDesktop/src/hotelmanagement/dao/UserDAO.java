
package hotelmanagement.dao;

import hotelmanagement.api.ApiClient;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {

    public boolean validateLogin(String username, String password)
            throws IOException, InterruptedException {

        Map<String, String> loginData = new HashMap<>();
        loginData.put("username", username);
        loginData.put("password", password);

        String response = ApiClient.post(
                "/api/users/login", loginData);

        return response.contains("Login successful");
    }
}