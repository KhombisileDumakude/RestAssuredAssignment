package requestBuilder;

import io.restassured.response.Response;

import static commons.Paths.BASE_URL;
import static io.restassured.RestAssured.given;

public class AdminRequestBuilder {

    public static String adminToken;
    public static Response approveUser(){
        String apiPath = "/admin/user/" +UserRequestBuilder.registeredUserId + "/approve";
        //String apiPath = "/admin/users/{userID}/approve";

        return given()
                .baseUri(BASE_URL)
                .basePath(apiPath)
                .header("Authorization", "Bearer " + adminToken)
                //another way to user parameters on the api string above
                //.pathParams("userID", UserRequestBuilder.registeredUserId)
                .when()
                .put()
                .then()
                .extract().response();


    }

    public static Response adminLogin(String email, String password){
        Response response = UserRequestBuilder.loginUser(email, password);
        adminToken = response.jsonPath().getString("data.token");
        return response;
    }

}
