package ApiPetStoreOrder;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static BaseUrl.BaseUrl.baseUrl;
import static Body.RequestBody.requestBodyForPositiveCase;
import static Header.Header.*;
import static Resources.Resources.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PositiveCases {

    public static String responseId = "";

    @Test(priority = 0)
    public void PlaceAnOrderForAPet()
    {
        baseURI = baseUrl;
        String responseBody = given().log().all()
                .header(contentTypeHeader, jsonHeaderValue)
                .header(acceptHeader, jsonHeaderValue)
                .body(requestBodyForPositiveCase).contentType(ContentType.JSON)
                .when().post(resourceForOrderingAPet)

                .then().log().all().assertThat()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("petId", equalTo(10))
                .body("quantity", equalTo(1))
                .body("shipDate", equalTo("2022-02-28T14:41:55.030+0000"))
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true))
                .header(responseHeader, responseHeaderValue)
                .extract().response().asString();

        JsonPath js = new JsonPath(responseBody);
        responseId = js.getString("id");
    }

    @Test(priority = 1)
    public void FindPurchaseOrderById()
    {
        baseURI = baseUrl;
        String getResponse = given().log().all()
                .header(acceptHeader, jsonHeaderValue)
                .when().get(resourceForFindingPurchaseOrderId + responseId)

                .then().log().all().assertThat()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("petId", equalTo(10))
                .body("quantity", equalTo(1))
                .body("shipDate", equalTo("2022-02-28T14:41:55.030+0000"))
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true))
                .header(responseHeader, responseHeaderValue)
                .extract().response().asString();

        System.out.println(getResponse);
    }

    @Test(priority = 2)
    public void DeletePurchaseOrderById()
    {
        baseURI = baseUrl;
        String deleteResponse = given().log().all()
                .header(acceptHeader, jsonHeaderValue)
                .when().delete(resourceForDeletingPurchaseOrderId + responseId)

                .then().log().all().assertThat()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("4"))
                .header(responseHeader, responseHeaderValue)
                .extract().response().asString();

        System.out.println(deleteResponse);
    }

}
