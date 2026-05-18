package requestBuilder;

import io.restassured.response.Response;
import static commons.Paths.BASE_URL;
import static io.restassured.RestAssured.given;

public class AdminRequestBuilder {

    public static String adminToken;
    public static Response approveUser(){
        String apiPath = "APIDEV/admin/users/" +UserRequestBuilder.registeredUserId + "/approve";
        //String apiPath = "APIDEV/admin/users/{userID}/approve";

        System.out.println("Admin token: " + adminToken);
        System.out.println("Api path: " + apiPath);

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

    public static Response adminLogin(){
        Response response = UserRequestBuilder.loginUser("admin@gmail.com", "@12345678");
        adminToken = response.jsonPath().getString("data.token");
        return response;
    }

}
