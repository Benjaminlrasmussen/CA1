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

        RestAssured.basePath = "/CA1/";

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
        given().pathParam("id", 0).
                when().get("/api/person/complete/{id}").
                then().statusCode(200).
                body("id", hasItems(0));
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
    @Test
    public void testGet(){
        //GET
        Person gottenPerson = given()
        .contentType(ContentType.JSON)
        .when().get("/api/person/complete/0").as(Person.class); 
        assertNotNull(gottenPerson.getId());
        assertEquals("Satan", gottenPerson.getFirstName());
    }

}
