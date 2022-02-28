package ApiPetStoreOrder;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static BaseUrl.BaseUrl.baseUrl;
import static Body.RequestBody.requestBodyForNegativeCase;
import static Header.Header.*;
import static Resources.Resources.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NegativeCases {

    //400 Bad Input Negative Case for PlacingAnOrderForAPet
    @Test(priority = 3)
    public void BadInputForPostApi()
    {
        baseURI = baseUrl;
        String responseBody = given().log().all()
                .header(contentTypeHeader, jsonHeaderValue)
                .header(acceptHeader, jsonHeaderValue)
                .body(requestBodyForNegativeCase).contentType(ContentType.JSON)
                .when().post(resourceForOrderingAPet)

                .then().log().all().assertThat()
                .statusCode(400)
                .body("code", equalTo(400))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("bad input"))
                .header(responseHeader, responseHeaderValue)
                .extract().response().asString();
        System.out.println(responseBody);
    }

    //404 Order Not Found Negative Case for FindPurchaseOrderById
    @Test(priority = 4)
    public void OrderNotFoundForGetApi()
    {
        baseURI = baseUrl;
        String getResponse = given().log().all()
                .header(acceptHeader, jsonHeaderValue)
                .when().get(resourceForNotFoundOrderId)

                .then().log().all().assertThat()
                .statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("Order not found"))
                .header(responseHeader, responseHeaderValue)
                .extract().response().asString();

        System.out.println(getResponse);
    }

    //404 Order Not Found Negative Case for DeletePurchaseOrderById
    @Test(priority = 5)
    public void OrderNotFoundForDeleteApi()
    {
        baseURI = baseUrl;
        String getResponse = given().log().all()
                .header(acceptHeader, jsonHeaderValue)
                .when().get(resourceForNotFoundOrderId)

                .then().log().all().assertThat()
                .statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("Order not found"))
                .header(responseHeader, responseHeaderValue)
                .extract().response().asString();

        System.out.println(getResponse);
    }

}
