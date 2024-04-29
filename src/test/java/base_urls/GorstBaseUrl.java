package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class GorstBaseUrl {
    // We avoid repetitive tasks when sending requests with the RequestSpecification object.
    // For example: repetitive tasks such as base URL and headers.

    protected RequestSpecification spec;//This is null in this line, setUp() method should run before using this.

    @BeforeMethod //Runs before each @Test
    public void setUp() {
        https://reqres.in/api/users
        spec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v1")
                .setContentType(ContentType.JSON)
                .build();
    }
}
