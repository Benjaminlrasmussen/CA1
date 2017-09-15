/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverTest;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServerIntegrationTest {

    public ServerIntegrationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        RestAssured.basePath = "/CA1";

        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void test(){
//        String uri = "hettp://localhost:8080/CA1/api/person/hobby/1";
//        //RestAssured.given().
//    }
    @Test
    public void serverIsRunning() {
        // given().get("http://localhost:8080/CA1/api/person/complete").then().statusCode(200);
        given().
                when().get().
                then().statusCode(200);

    }

    @Test
    public void getPerson() {
        given().pathParam("id", 1).
                when().get("/api/person/complete/{id}").
                then().statusCode(200).
                body("id", hasItems(1));
    }
    
    @Test
    public void getCompany(){
        given().pathParam("id", 9988998).when().get("/api/company/complete/{id}").then().statusCode(200).body("id", hasItems(3));
    }
    
    @Test
    public void getPeopleByHobby(){
        given().pathParam("hobby", "ged").when().get("/api/person/hobby/{hobby}").then().statusCode(200).body("firstname", hasItems("Satan"));
    }
    @Test
    public void getPersonByPhone(){
        given().pathParam("phonenumber", 66666666)
                .when().get("/api/person/phonenumber/{phonenumber}")    //SingleResult !!!!!!!!!!!!
                .then().statusCode(200).body("firstname", hasItems("Satan"));
    }
    
    @Test
    public void getCompanyByEmployee(){
        given().pathParam("employees", 700).when().get("/api/company/employees/{employees}")
                .then().statusCode(200).body("name",hasItems("Google"));
    }
    @Test
    public void getByZipcode(){
        given().when().get("/api/zipcodes").then().statusCode(200).body("city", hasItems("Graested"));
    }
    @Test
    public void testPut(){
        
        
        
//        List<Phone> phones = new ArrayList();
//        phones.add(new Phone(22232, "first number"));
//        phones.add(new Phone(121, "second number"));
//        Address add = new Address("1. street", new CityInfo(3113, "oddense"), "mangler et n");
//        List<Hobby> hobbies = new ArrayList();
//        hobbies.add(new Hobby("ridning", "at ride"));
//        Person putPerson = new Person(404, "roflmaololzomg@hotmail.com", phones, add, "zinao", "zonus", hobbies);
//        
//        
//        given()
//        .contentType(ContentType.JSON)
//        .body(putPerson)
//        .when().put("/api/person")
//        .as(Person.class);
//        
        
        
        //assertNotNull(newPerson.getId());
        
        
        
    }
  
 

}
