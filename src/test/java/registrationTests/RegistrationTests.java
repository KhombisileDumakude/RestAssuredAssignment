package registrationTests;

import com.github.javafaker.Faker;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestBuilder.AdminRequestBuilder;
import requestBuilder.UserRequestBuilder;

import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationTests {

    static String firstName;
    static String lastName;
    static String email;
    static String password;
    static String groupId;
    static String adminEmail;
    static String adminPassword;
    static String title;
    static String content;
    static String isPublic;
    static Integer rating;



    static Faker faker = new Faker();

    @BeforeClass
    public static void setUpData(){
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = "Group2" + faker.internet().emailAddress();
        password = "12345678!";
        groupId = "e07f20cf-cd6c-422d-92b1-55c15bef439f";
        adminEmail = "admin@gmail.com";
        adminPassword = "@12345678";
        title = "My Testimonial";
        content = "Ndosi Automation is lekker.";
        isPublic = "true";
        rating = 5;





    }
    @Test(priority = 1)
    public void userRegistrationTest(){
        Response response = UserRequestBuilder.registerUser(firstName, lastName, email, password, groupId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 201);
    }
    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void adminLoginTest(){
        Response response = AdminRequestBuilder.adminLogin();
        response.then().log().all();


        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 3)
    public void userApprovalTest(){
        requestBuilder.AdminRequestBuilder.approveUser()
                .then().log().all()
                .assertThat()
                //the below 2 are other ways of asserting
                .statusCode(200)
                .body("success", equalTo(true));
    }
    @Test(priority = 4)
    public void userLoginTest(){
        requestBuilder.UserRequestBuilder.loginUser(email, password)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

    }

    @Test(priority = 5)
    public void createTestimonialTest(){
        requestBuilder.TestimonialBuilder.createTestimonial(title, content, rating,isPublic)
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .body("success", equalTo(true));

    }

    @Test(priority = 6)
    public void updateTestimonialTest(){
        requestBuilder.TestimonialBuilder.updateTestimonial(title, content, rating)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

    }

    @Test(priority = 7)
    public void deleteTestimonialTest(){
        requestBuilder.TestimonialBuilder.deleteTestimonial()
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

    }

    @Test(priority = 8)
    public void getCourseTest(){
        requestBuilder.GetCourseBuilder.getPublishedCourse()
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

    }


}
