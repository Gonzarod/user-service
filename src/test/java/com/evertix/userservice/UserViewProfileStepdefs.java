package com.evertix.userservice;

import com.evertix.userservice.UserServiceApplication;
import com.evertix.userservice.entities.User;
import com.evertix.userservice.service.UserService;
import com.evertix.userservice.util.RestPageImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@CucumberContextConfiguration
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
public class UserViewProfileStepdefs {


    private URL base;
    private String username;
    @Autowired
    protected TestRestTemplate template;

    private ResponseEntity<User> userResponse;
    private ResponseEntity<RestPageImpl<User>> listResponseEntity;

    @Before
    public void setUp() throws MalformedURLException {
        this.base=new URL( "http://tutofast-user-service.eastus.azurecontainer.io:8085/api/");
    }

    @Given("Student with a username {string}")
    public void studentWithAUsername(String arg0) {
        this.username=arg0;

    }


    @Then("response status is {int}")
    public void responseStatusIs(int arg0) {
        Assert.assertEquals(userResponse.getStatusCodeValue(),200,userResponse.getStatusCodeValue());
    }

    @And("all student data is retrieved")
    public void allStudentDataIsRetrieved() {
        Assert.assertEquals("Name is:"+userResponse.getBody().getName(),"Jesus",userResponse.getBody().getName());
        Assert.assertEquals("LastName is:"+userResponse.getBody().getLastName(),"Duran",userResponse.getBody().getLastName());
        Assert.assertEquals("DNI is:"+userResponse.getBody().getDni(),"77332215",userResponse.getBody().getDni());
    }

    @Given("Teacher with a username {string}")
    public void teacherWithAUsername(String arg0) {
        this.username=arg0;
    }

    @And("all teacher data is retrieved")
    public void allTeacherDataIsRetrieved() {
        Assert.assertEquals("Name is:"+userResponse.getBody().getName(),"Albert",userResponse.getBody().getName());
        Assert.assertEquals("LastName is:"+userResponse.getBody().getLastName(),"Mayta",userResponse.getBody().getLastName());
        Assert.assertEquals("DNI is:"+userResponse.getBody().getDni(),"77332216",userResponse.getBody().getDni());
    }

    @Given("Admin with username {string}")
    public void adminWithUsername(String arg0) {
        this.username=arg0;
    }

    @When("goes to user option and call to api is made")
    public void goesToUserOptionAndCallToApiIsMade() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ParameterizedTypeReference<RestPageImpl<User>> responseType = new ParameterizedTypeReference<RestPageImpl<User>>() { };
        System.out.println("Wokinkg:::::::::::::::::::::::::::::::::::::::::::");
        this.listResponseEntity = template.exchange(base+"/users/page", HttpMethod.GET,request, responseType);
    }

    @And("all users are listed")
    public void allUsersAreListed() {
        Assert.assertEquals("*************Size is "+listResponseEntity.getBody().getTotalElements(),2,listResponseEntity.getBody().getTotalElements());
    }

    @Then("response status of request is {int}")
    public void responseStatusOfRequestIs(int arg0) {
        Assert.assertEquals(listResponseEntity.getStatusCodeValue(),200,listResponseEntity.getStatusCodeValue());
    }

    @When("go to profile option and call to api is made")
    public void goToProfileOptionAndCallToApiIsMade() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        this.userResponse = template.exchange(base+"/users/username/"+username, HttpMethod.GET,request,User.class);
    }
}
