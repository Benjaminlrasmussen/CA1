/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverTest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        given().pathParam("id", 0).
                when().get("/api/person/complete/{id}").
                then().statusCode(200).
                body("id", hasItems(0));
    }

}
