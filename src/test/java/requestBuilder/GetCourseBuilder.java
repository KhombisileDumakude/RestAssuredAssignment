package requestBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static commons.Paths.BASE_URL;
import static io.restassured.RestAssured.given;
import static requestBuilder.AdminRequestBuilder.adminToken;

public class GetCourseBuilder {

    public static Response getPublishedCourse(){
        String apiPath = "APIDEV/courses?level=beginner&search=automation";


        return given()
                .baseUri(BASE_URL)
                .basePath(apiPath)
                .header("Authorization", "Bearer " + adminToken)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract().response();


    }
}
