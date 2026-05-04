package basicTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTests {
    String BaseURL = "https://www.ndosiautomation.co.za/APIDEV";
    String registeredUserId;
    String adminToken;
    @Test (priority = 1)
    public void userRegistrationTest(){

        String registerUserPath = "/register";
        String userRegistrationPayload = "{\n" +
                "  \"firstName\": \"Khombi\",\n" +
                "  \"lastName\": \"API tester\",\n" +
                "  \"email\": \"Ndosi41111@test.co.za\",\n" +
                "  \"password\": \"12345678!\",\n" +
                "  \"confirmPassword\": \"12345678!\",\n" +
                "  \"groupId\": \"e07f20cf-cd6c-422d-92b1-55c15bef439f\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath(registerUserPath)
                .header("Content-Type","application/json")
                .body(userRegistrationPayload)
                .log().all()
                .post()
                .then().extract().response();

        int responseStatusCode = response.getStatusCode();
        System.out.println("Status code: " + responseStatusCode);
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(responseStatusCode, 201, "Expected status code 201");

        registeredUserId = response.jsonPath().getString("data.id");

    }


    @Test (priority = 2)
    public void adminLoginTest(){

        String loginPath = "/login";
        String adminLoginPayload = "{\n" +
                "  \"email\": \"admin@gmail.com\",\n" +
                "  \"password\": \"@12345678\"\n" +
                "}";


        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath(loginPath)
                .header("Content-Type","application/json")
                .body(adminLoginPayload)
                .log().all()
                .post()
                .then().extract().response();

        int responseStatusCode = response.getStatusCode();
        System.out.println("Status code: " + responseStatusCode);
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(responseStatusCode, 200, "Expected status code 200");

        adminToken = response.jsonPath().getString("data.token");

    }

    @Test (priority = 3)
    public void approveUser(){
        String approveUserPath = "/admin/users/" +registeredUserId+"/approve";

        Response response = RestAssured.given()
                .baseUri(BaseURL)
                .basePath(approveUserPath)
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + adminToken)
                .log().all()
                .put()
                .then().extract().response();

        System.out.println("Response Body: " + response.getBody().asString());
    }

//    // update user to be admin user
//    public static Response updateUserRoleResponse(String role) {
//        String apiPath = "/APIDEV/admin/users/"+registeredUserId+"/role";
//        return RestAssured.given()
//                .baseUri(baseURL)
//                .basePath(apiPath)
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer " + authToken)
//                .body(approveRolePayload(role))
//                .log().all()
//                .put()
//                .then().extract().response();
//    }
}
