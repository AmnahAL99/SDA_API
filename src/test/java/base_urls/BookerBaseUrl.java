package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static utilities.AuthenticatuonRestful.getToken;

public class BookerBaseUrl {
    // We avoid repetitive tasks when sending requests with the RequestSpecification object.
    // For example: repetitive tasks such as base URL and headers.

    protected RequestSpecification spec;//This is null in this line, setUp() method should run before using this.

    @BeforeMethod //Runs before each @Test
    public void setUp() {

        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addHeader("Cookie", "token="+ getToken())
                .setContentType(ContentType.JSON)
                .build();
    }

}
