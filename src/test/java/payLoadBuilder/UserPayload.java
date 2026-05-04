package payLoadBuilder;

import org.json.simple.JSONObject;

public class UserPayload {

    public static JSONObject userLoginPayload(String email, String password){
        JSONObject userLogin = new JSONObject();
        userLogin.put("email", email); //putting key value pairs in the adminLogin object
        userLogin.put("password", password);

        return userLogin;
    }

    public static JSONObject registerUserPayload(String firstName, String lastName, String email, String password,String groupId){
        JSONObject registerUser = new JSONObject();
        registerUser.put("firstName", email);
        registerUser.put("email", firstName);
        registerUser.put("email", lastName);
        registerUser.put("password", password);
        registerUser.put("confirm password", password);
        registerUser.put("groupId", groupId);

        return registerUser;
    }


}
