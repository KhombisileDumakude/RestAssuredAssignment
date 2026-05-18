package requestBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payLoadBuilder.UserPayload;
import static commons.Paths.BASE_URL;
import static io.restassured.RestAssured.given;
import static requestBuilder.AdminRequestBuilder.adminToken;

public class TestimonialBuilder {

    public static String testimonialId; // store globally

    public static Response createTestimonial(String title, String content, Integer rating, String isPublic) {
        String apiPath = "/APIDEV/testimonials";
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(apiPath)
                .header("Authorization", "Bearer " + adminToken)
                .contentType(ContentType.JSON)
                .body(UserPayload.createTestimonialPayload(title, content, rating, isPublic))
                .when()
                .post()
                .then()
                .extract().response();

        // Capture testimonialId from response
        testimonialId = response.jsonPath().getString("data.Id");

        return response;
    }

    public static Response updateTestimonial(String title, String content, Integer rating) {
        String apiPath = "/APIDEV/testimonials/" + testimonialId;
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(apiPath)
                .header("Authorization", "Bearer " + adminToken)
                .contentType(ContentType.JSON)
                .body(UserPayload.updateTestimonialPayload(title, content, rating))
                .when()
                .put()   // use PUT for update
                .then()
                .extract().response();

        return response;
    }

    public static Response deleteTestimonial() {
        String apiPath = "/APIDEV/testimonials/" + testimonialId;
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(apiPath)
                .header("Authorization", "Bearer " + adminToken)
                .accept(ContentType.JSON)
                .when()
                .delete()   // use DELETE for removal
                .then()
                .extract().response();

        return response;
    }
}