package io.github.rafaelpino.api.resource;

import io.github.rafaelpino.application.dto.CreditCardDTO;
import io.github.rafaelpino.domain.services.CreditCardService;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreditCardResourceTest {
    @TestHTTPResource("/api/creditcards")
    URL apiURL;
    @Inject
    private CreditCardService creditCardService;
    CreditCardDTO creditCard;
    @BeforeEach
    @Transactional
    public void setUP(){
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCardNumber(45612);
        creditCardDTO.setCardHolderName("Test");
        creditCardDTO.setSecurityCode(147);
        creditCardService.createCard(creditCardDTO);
        creditCard = creditCardDTO;
    }

    @Test
    @DisplayName("should list all credit cards")
    @Order(1)
    public void listAllCreditCardsTest(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .statusCode(200)
                .body("size()", Matchers.is(1));
    }

    @Test
    @DisplayName("should create credit card successfully ")
    @Order(2)
    public void createCreditCardTest(){
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(creditCard)
                        .when()
                        .post(apiURL)
                        .then()
                        .extract()
                        .response();
        assertEquals(201, response.statusCode());
    }

}