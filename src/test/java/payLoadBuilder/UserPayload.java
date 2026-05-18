package payLoadBuilder;

import org.json.simple.JSONObject;

public class UserPayload {

    public static JSONObject userLoginPayload(String email, String password){
        JSONObject userLogin = new JSONObject();
        userLogin.put("email", email); //putting key value pairs in the userLogin object
        userLogin.put("password", password);

        return userLogin;
    }

    public static JSONObject registerUserPayload(String firstName, String lastName, String email, String password,String groupId){
        JSONObject registerUser = new JSONObject();
        registerUser.put("firstName", firstName);
        registerUser.put("lastName", lastName);
        registerUser.put("email", email);
        registerUser.put("password", password);
        registerUser.put("confirmPassword", password);
        registerUser.put("groupId", groupId);

        return registerUser;
    }

    public static JSONObject createTestimonialPayload(String title, String content, Integer rating, String isPublic){
        JSONObject createTestimonial = new JSONObject();
        createTestimonial.put("title", title);
        createTestimonial.put("content",content);
        createTestimonial.put("rating", rating);
        createTestimonial.put("isPublic", isPublic);

        return createTestimonial;
    }

    public static JSONObject updateTestimonialPayload(String title, String content, Integer rating){
        JSONObject updateTestimonial = new JSONObject();
        updateTestimonial.put("title", title);
        updateTestimonial.put("content",content);
        updateTestimonial.put("rating", rating);

        return updateTestimonial;
    }




}
